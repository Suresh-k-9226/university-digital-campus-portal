
import hashlib
import os
from pathlib import Path

from dotenv import load_dotenv
from langchain_chroma import Chroma
from langchain_google_genai import GoogleGenerativeAIEmbeddings

if __package__:
    from .aem_documents import load_aem_documents
else:
    from aem_documents import load_aem_documents


load_dotenv()

PROJECT_ROOT = Path(__file__).resolve().parents[2]
CHROMA_DIRECTORY = PROJECT_ROOT / "chroma_db"

COLLECTION_NAME = "udcp_knowledge"


class UniversityEmbeddings(GoogleGenerativeAIEmbeddings):
    """Use appropriate Gemini task types for retrieval."""

    def embed_documents(self, texts, **kwargs):
        return super().embed_documents(
            texts,
            task_type="RETRIEVAL_DOCUMENT",
            **kwargs,
        )

    def embed_query(self, text, **kwargs):
        return super().embed_query(
            text,
            task_type="RETRIEVAL_QUERY",
            **kwargs,
        )


def get_embeddings():
    api_key = os.getenv("GOOGLE_API_KEY")

    if not api_key or api_key == "your_gemini_api_key_here":
        raise ValueError(
            "GOOGLE_API_KEY is missing. Configure it in your .env file."
        )

    return UniversityEmbeddings(
        model="gemini-embedding-001",
        google_api_key=api_key,
    )


def get_vector_store():
    """Connect to the existing ChromaDB collection."""

    return Chroma(
        collection_name=COLLECTION_NAME,
        embedding_function=get_embeddings(),
        persist_directory=str(CHROMA_DIRECTORY),
    )


def create_document_id(path):
    """Generate a deterministic ID from an AEM content path."""

    return "udcp_" + hashlib.sha256(
        path.encode("utf-8")
    ).hexdigest()


def create_content_hash(content):
    """Generate a hash to detect changes in document content."""

    return hashlib.sha256(
        content.encode("utf-8")
    ).hexdigest()


def validate_documents(documents):
    """Validate the complete AEM snapshot before modifying ChromaDB."""

    if not documents:
        raise ValueError(
            "AEM returned no documents. Existing ChromaDB data was preserved."
        )

    paths = []

    for document in documents:
        path = document.metadata.get("path")

        if not isinstance(path, str) or not path.strip():
            raise ValueError(
                "An AEM document has a missing or invalid path. "
                "Synchronization aborted."
            )

        paths.append(path)

    if len(paths) != len(set(paths)):
        raise ValueError(
            "Duplicate AEM document paths detected. "
            "Synchronization aborted."
        )


def sync_vector_store():
    """
    Synchronize the current AEM snapshot with ChromaDB.

    - Add new documents.
    - Update changed documents.
    - Skip unchanged documents.
    - Delete documents no longer returned by AEM.

    ChromaDB deletions happen only after successful AEM fetching,
    validation, and document upserts.
    """

    print("Fetching university content from AEM...")

    # Fetch all three collections before making database changes.
    documents = load_aem_documents()

    # Validate the complete snapshot before proceeding.
    validate_documents(documents)

    print(f"Successfully retrieved {len(documents)} AEM documents.")

    vector_store = get_vector_store()

    collection = vector_store._collection

    # Read existing records, including their original content.
    existing_data = collection.get(
        include=["metadatas", "documents"]
    )

    existing_ids = existing_data["ids"]
    existing_metadatas = existing_data["metadatas"]
    existing_contents = existing_data["documents"]

    existing_by_path = {}

    for record_id, metadata, content in zip(
        existing_ids,
        existing_metadatas,
        existing_contents,
    ):
        path = metadata.get("path") if metadata else None

        if path:
            if path in existing_by_path:
                raise ValueError(
                    f"Duplicate ChromaDB records found for AEM path: {path}. "
                    "Synchronization aborted."
                )

            existing_by_path[path] = {
                "id": record_id,
                "content": content,
                "metadata": metadata,
            }

    documents_to_upsert = []
    desired_ids = set()

    added_count = 0
    updated_count = 0
    unchanged_count = 0

    print("\nChecking for new and modified content...")

    for document in documents:
        path = document.metadata["path"]

        desired_id = create_document_id(path)
        content_hash = create_content_hash(document.page_content)

        desired_ids.add(desired_id)

        # Store the content hash for future change detection.
        document.metadata["content_hash"] = content_hash

        existing = existing_by_path.get(path)

        if (
            existing
            and existing["id"] == desired_id
            and existing["content"] == document.page_content
            and existing["metadata"].get("content_hash") == content_hash
        ):
            unchanged_count += 1
            continue

        documents_to_upsert.append((desired_id, document))

        if existing:
            updated_count += 1
        else:
            added_count += 1

    # Upsert only new or modified documents.
    # Existing records remain intact until all upserts succeed.
    if documents_to_upsert:
        print(
            f"\nGenerating embeddings for "
            f"{len(documents_to_upsert)} documents..."
        )

        vector_store.add_documents(
            documents=[
                document for _, document in documents_to_upsert
            ],
            ids=[
                document_id for document_id, _ in documents_to_upsert
            ],
        )

    # Delete records that are no longer present in the AEM snapshot.
    stale_ids = [
        record_id
        for record_id in existing_ids
        if record_id not in desired_ids
    ]

    if stale_ids:
        collection.delete(ids=stale_ids)

    print("\n" + "=" * 55)
    print("UNIVERSITY KNOWLEDGE BASE SYNCHRONIZATION")
    print("=" * 55)
    print(f"AEM documents retrieved : {len(documents)}")
    print(f"New documents added     : {added_count}")
    print(f"Documents updated       : {updated_count}")
    print(f"Unchanged documents     : {unchanged_count}")
    print(f"Stale documents deleted : {len(stale_ids)}")
    print(f"Total ChromaDB records  : {collection.count()}")
    print(f"Database location       : {CHROMA_DIRECTORY}")
    print("=" * 55)

    return vector_store


def create_vector_store():
    """Backward-compatible entry point for initial creation."""

    return sync_vector_store()


if __name__ == "__main__":
    sync_vector_store()
