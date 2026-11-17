
from langchain_chroma import Chroma

from vector_store import (
    CHROMA_DIRECTORY,
    COLLECTION_NAME,
    get_embeddings,
)


def test_retrieval(question):
    embeddings = get_embeddings()

    vector_store = Chroma(
        collection_name=COLLECTION_NAME,
        embedding_function=embeddings,
        persist_directory=str(CHROMA_DIRECTORY),
    )

    results = vector_store.similarity_search_with_relevance_scores(
        question,
        k=4,
    )

    print(f"\nQuestion: {question}")
    print("=" * 75)

    if not results:
        print("No documents retrieved.")
        return

    for index, (document, score) in enumerate(results, start=1):
        print(f"\nResult {index}")
        print(f"Relevance Score: {score:.4f}")
        print(f"Type: {document.metadata.get('source_type')}")
        print(f"Title: {document.metadata.get('title')}")
        print(f"Content:\n{document.page_content}")
        print("-" * 75)


if __name__ == "__main__":

    test_questions = [
        "Who specializes in artificial intelligence and machine learning?",
        "What courses are available at the university?",
        "What is the university admission fee?",
        "Who is the principal of the university?",
    ]

    for question in test_questions:
        test_retrieval(question)
