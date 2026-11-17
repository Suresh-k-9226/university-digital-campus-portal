# University of Velen – AI Chatbot

An AI-powered university information chatbot built using **FastAPI, LangChain, Google Gemini, and ChromaDB**. The chatbot implements a Retrieval-Augmented Generation (RAG) architecture to provide context-aware answers based on University of Velen information.

It retrieves relevant university documents from a vector database and uses Google Gemini to generate accurate, context-grounded responses with supporting source information.

## Features

- **Retrieval-Augmented Generation (RAG):** Retrieves relevant university information before generating answers.
- **Google Gemini Integration:** Uses Gemini 3.1 Flash-Lite for response generation.
- **ChromaDB Vector Database:** Stores and retrieves document embeddings.
- **LangChain Integration:** Manages LLM interactions, embeddings, and vector retrieval.
- **FastAPI Backend:** Provides REST API endpoints for chatbot functionality.
- **Source Validation:** Returns supporting document information with generated answers.
- **Hallucination Control:** Instructs the model to answer only from supplied university documents.
- **Greeting Recognition:** Handles common greetings and identity questions without invoking the LLM.
- **Structured Responses:** Uses Pydantic to validate generated responses.
- **CORS Configuration:** Allows communication with the local React development server.

## Technology Stack

| Technology | Purpose |
|---|---|
| Python 3.14+ | Backend development |
| FastAPI | REST API development |
| LangChain | LLM orchestration and retrieval |
| Google Gemini 3.1 Flash-Lite | AI response generation |
| ChromaDB | Vector database |
| LangChain Chroma | Vector store integration |
| LangChain Google GenAI | Gemini and embedding integration |
| LangChain Text Splitters | Document chunking |
| Pydantic | Request and response validation |
| Uvicorn | ASGI application server |
| Python-dotenv | Environment variable management |
| Requests | HTTP communication |
| uv | Python dependency management |

## Project Structure

```text
ai-chatbot/
│
├── src/
│   └── ai_chatbot/
│       ├── __init__.py
│       ├── main.py
│       ├── aem_client.py
│       ├── aem_documents.py
│       ├── inspect_aem.py
│       ├── rag_service.py
│       ├── test_retrieval.py
│       └── vector_store.py
│
├── .env
├── .gitignore
├── .python-version
├── pyproject.toml
├── requirements.txt
├── uv.lock
└── README.md
```

### File Descriptions

| File | Description |
|---|---|
| `main.py` | Initializes the FastAPI application and exposes chatbot API endpoints. |
| `aem_client.py` | Handles communication with Adobe Experience Manager. |
| `aem_documents.py` | Handles AEM document retrieval and preparation. |
| `inspect_aem.py` | Provides utilities for inspecting AEM content. |
| `rag_service.py` | Implements RAG processing, document retrieval, Gemini response generation, and source validation. |
| `test_retrieval.py` | Contains document retrieval testing utilities. |
| `vector_store.py` | Manages ChromaDB, embedding configuration, and vector storage. |

## Architecture and RAG Workflow

The chatbot follows a Retrieval-Augmented Generation architecture.

### Workflow

1. **User Query:** Receives a university-related question through the React frontend.
2. **FastAPI Request:** Processes the question through the `/api/chat` endpoint.
3. **Query Preprocessing:** Recognizes greetings and chatbot identity questions without calling the LLM.
4. **Document Retrieval:** Performs similarity search in ChromaDB to retrieve the top four relevant documents.
5. **Context Preparation:** Combines retrieved document content and metadata into a context.
6. **Prompt Construction:** Creates a university-specific prompt with instructions to prevent unsupported answers.
7. **LLM Response Generation:** Sends the prompt and retrieved context to Google Gemini 3.1 Flash-Lite.
8. **Response Validation:** Uses Pydantic structured output parsing to validate the generated response.
9. **Source Validation:** Validates document indices and extracts supporting source metadata.
10. **Response Delivery:** Returns the generated answer and supporting sources through FastAPI.

### Key RAG Capabilities

- Context-grounded responses based on retrieved university documents.
- Similarity-based retrieval using ChromaDB.
- Structured LLM output using Pydantic.
- Source-index validation to identify supporting documents.
- Fallback responses when information is unavailable.
- Cached vector-store and Gemini clients using `lru_cache`.
- Separate handling for greetings and chatbot identity questions.

## API Endpoints

The FastAPI application exposes the following endpoints:

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Checks whether the API is running. |
| GET | `/health` | Returns chatbot health status. |
| POST | `/api/chat` | Accepts a question and returns an AI-generated response. |

### Example Chat Request

**Endpoint:** `POST /api/chat`

Request body:

```json
{
  "question": "What courses are offered by the University of Velen?"
}
```

### Example Response

```json
{
  "answer": "The answer based on the university knowledge base.",
  "sources": [
    {
      "type": "course",
      "title": "Course Information",
      "path": "/content/example"
    }
  ]
}
```

The actual response content and source metadata depend on the retrieved university documents.

## Prerequisites

Before running the application, make sure you have:

- Python 3.14 or later.
- uv package manager.
- Google Gemini API key.
- Access to the required University of Velen AEM content sources for document ingestion.

## Installation and Setup

### 1. Navigate to the Project Directory

```powershell
cd ai-chatbot
```

### 2. Install Dependencies

The project uses uv for dependency management.

```powershell
uv sync
```

Alternatively, using pip:

```powershell
python -m venv .venv
```

Activate the virtual environment on Windows:

```powershell
.venv\Scripts\Activate.ps1
```

Install dependencies:

```powershell
pip install -r requirements.txt
```

### 3. Configure Environment Variables

Create a `.env` file in the project root.

Add the required environment variables used by the application.

Example:

```env
GOOGLE_API_KEY=your_google_gemini_api_key
```

Ensure that the environment variable names match those referenced in your Python source code.

**Security:** Never commit your `.env` file or expose API credentials in a public repository.

### 4. Run the FastAPI Application

From the `ai-chatbot` directory, execute:

```powershell
uv run uvicorn ai_chatbot.main:app --reload --port 8000 --app-dir src
```

The API will be available at:

`http://127.0.0.1:8000`

### 5. Access API Documentation

FastAPI automatically generates interactive API documentation.

- **Swagger UI:** http://127.0.0.1:8000/docs
- **ReDoc:** http://127.0.0.1:8000/redoc

Use Swagger UI to test the available API endpoints.

## Testing the API

### Health Check

Send a GET request to:

```text
http://127.0.0.1:8000/health
```

Expected response:

```json
{
  "status": "healthy",
  "service": "UDCP AI Chatbot"
}
```

### Chat Endpoint

Send a POST request to:

```text
http://127.0.0.1:8000/api/chat
```

Example request:

```json
{
  "question": "Who specializes in artificial intelligence and machine learning?"
}
```

The chatbot retrieves relevant university documents and generates a response based on the available information.

## Dependency Management

The project uses uv to manage Python dependencies.

| File | Purpose |
|---|---|
| `pyproject.toml` | Defines project metadata and dependencies. |
| `uv.lock` | Locks dependency versions for reproducible installations. |
| `requirements.txt` | Provides a pip-compatible dependency export. |

Synchronize dependencies:

```powershell
uv sync
```

Regenerate the requirements file:

```powershell
uv export --format requirements-txt --no-dev --output-file requirements.txt
```

## Security and Git Configuration

The following local files and directories are excluded from version control through `.gitignore`:

- `.env`
- `.venv/`
- `__pycache__/`
- `*.pyc`
- `chroma_db/`

API credentials and local environment files should never be committed to GitHub.

## Future Enhancements

- Conversation history and contextual memory.
- Advanced retrieval and reranking strategies.
- Improved document ingestion pipelines.
- Response evaluation and quality monitoring.
- Enhanced logging and error monitoring.
- Additional knowledge-source integrations.
- Improved chatbot personalization.

## Author

**Suresh Kandregula**

University of Velen – UDCP Capstone Project