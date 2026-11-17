
import re
from functools import lru_cache

from pydantic import BaseModel, Field

from langchain_chroma import Chroma
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.output_parsers import PydanticOutputParser

if __package__:
    from .vector_store import (
        CHROMA_DIRECTORY,
        COLLECTION_NAME,
        get_embeddings,
    )
else:
    from vector_store import (
        CHROMA_DIRECTORY,
        COLLECTION_NAME,
        get_embeddings,
    )


FALLBACK_MESSAGE = (
    "I couldn't find that information in the University of Velen knowledge base."
)

ERROR_MESSAGE = (
    "I'm unable to process your question right now. "
    "Please try again."
)


class RAGResponse(BaseModel):
    supported: bool = Field(
        description="True only when the supplied university documents directly support an answer."
    )

    answer: str = Field(
        description="A clear answer based only on the supplied university documents."
    )

    source_indices: list[int] = Field(
        description="One-based indices of documents that directly support the answer."
    )


# Reuse the ChromaDB connection and embedding client.
@lru_cache(maxsize=1)
def get_vector_store():
    return Chroma(
        collection_name=COLLECTION_NAME,
        embedding_function=get_embeddings(),
        persist_directory=str(CHROMA_DIRECTORY),
    )


# Reuse the Gemini model client.
@lru_cache(maxsize=1)
def get_llm():
    return ChatGoogleGenerativeAI(
        model="gemini-3.1-flash-lite",
        temperature=0,
    )


# Create the response parser only once.
PARSER = PydanticOutputParser(
    pydantic_object=RAGResponse
)


def normalize_question(question):
    return re.sub(
        r"[^\w\s]",
        "",
        question.lower(),
    ).strip()


def get_greeting_response(question):
    normalized_question = normalize_question(question)

    greetings = {
        "hi",
        "hello",
        "hey",
        "good morning",
        "good afternoon",
        "good evening",
        "good night",
        "howdy",
        "greetings",
    }

    if normalized_question in greetings:
        return {
            "answer": (
                "Hello! Welcome to the University of Velen! "
                "I'm your AI Campus Assistant. "
                "I can help you with university information, campus facilities, "
                "courses, faculty, admissions, placements, student services, "
                "and upcoming events. "
                "What would you like to know?"
            ),
            "sources": [],
        }

    return None


def get_identity_response(question):
    normalized_question = normalize_question(question)

    identity_responses = {
        "what is your name": (
            "I'm the University of Velen AI Campus Assistant! "
            "I'm here to help you with university information, courses, "
            "faculty, admissions and events."
        ),
        "who are you": (
            "I'm the University of Velen AI Campus Assistant, "
            "your virtual guide to university information."
        ),
        "what can you do": (
            "I can help you explore the University of Velen, including "
            "university information, campus facilities, courses, faculty, "
            "admissions, placements, student services and upcoming events."
        ),
        "are you an ai assistant": (
            "Yes! I'm the AI-powered Campus Assistant for the University of Velen."
        ),
    }

    answer = identity_responses.get(normalized_question)

    if answer:
        return {
            "answer": answer,
            "sources": [],
        }

    return None


def get_document_text(documents):
    context_parts = []

    for index, document in enumerate(documents, start=1):
        context_parts.append(
            f"""
DOCUMENT INDEX: {index}
SOURCE TYPE: {document.metadata.get("source_type", "unknown")}
SOURCE TITLE: {document.metadata.get("title", "Unknown")}
CONTENT:
{document.page_content}
""".strip()
        )

    return "\n\n---\n\n".join(context_parts)


def ask_university_assistant(question):

    if not question or not question.strip():
        raise ValueError("Please enter a question.")

    question = question.strip()

    # 1. Handle greetings without calling Gemini.
    greeting_response = get_greeting_response(question)

    if greeting_response:
        return greeting_response

    # 2. Handle identity questions without calling Gemini.
    identity_response = get_identity_response(question)

    if identity_response:
        return identity_response

    # 3. Retrieve relevant documents.
    vector_store = get_vector_store()

    documents = vector_store.similarity_search(
        question,
        k=4,
    )

    if not documents:
        return {
            "answer": FALLBACK_MESSAGE,
            "sources": [],
        }

    # 4. Prepare retrieved university context.
    context = get_document_text(documents)

    # 5. Prepare the prompt.
    prompt = f"""
You are the AI-powered campus assistant for the University of Velen.

Answer the user's question using ONLY the supplied university documents.

STRICT RULES:

1. Never invent university information.
2. If the documents do not directly support the answer,
   set supported to false.
3. Do not confuse course fees with university admission fees.
4. Do not assume that a professor is the principal.
5. Do not use general knowledge to fill missing information.
6. Ignore irrelevant retrieved documents.
7. If unsupported:
   - Set answer to exactly: "{FALLBACK_MESSAGE}"
   - Set source_indices to an empty list.
8. If supported:
   - Give a concise, friendly answer.
   - Include only documents that directly support the answer.
9. Document indices are one-based.

UNIVERSITY DOCUMENTS:

{context}

USER QUESTION:

{question}

{PARSER.get_format_instructions()}
"""

    # 6. Generate the response using the cached Gemini client.
    try:
        response = get_llm().invoke(prompt)

        response_text = response.content

        if isinstance(response_text, list):
            response_text = " ".join(
                item.get("text", "") if isinstance(item, dict)
                else str(item)
                for item in response_text
            )

        structured_response = PARSER.parse(
            str(response_text)
        )

    except Exception as error:
        print(
            "RAG response processing failed:",
            type(error).__name__,
        )

        return {
            "answer": ERROR_MESSAGE,
            "sources": [],
        }

    # 7. Reject unsupported answers.
    if not structured_response.supported:
        return {
            "answer": FALLBACK_MESSAGE,
            "sources": [],
        }

    # 8. Validate source indices.
    valid_indices = sorted(
        {
            index
            for index in structured_response.source_indices
            if 1 <= index <= len(documents)
        }
    )

    if not valid_indices:
        return {
            "answer": FALLBACK_MESSAGE,
            "sources": [],
        }

    # 9. Return only relevant source information.
    sources = []

    for index in valid_indices:
        document = documents[index - 1]

        sources.append(
            {
                "type": document.metadata.get("source_type"),
                "title": document.metadata.get("title"),
                "path": document.metadata.get("path"),
            }
        )

    return {
        "answer": structured_response.answer,
        "sources": sources,
    }


if __name__ == "__main__":

    test_questions = [
        "hi",
        "good morning",
        "what is your name",
        "Who specializes in artificial intelligence and machine learning?",
        "What courses are available at the university?",
        "What is the university admission fee?",
        "Who is the principal of the university?",
    ]

    for question in test_questions:

        result = ask_university_assistant(question)

        print("\nQUESTION:", question)
        print("ANSWER:", result["answer"])
        print("SOURCES:", result["sources"])
