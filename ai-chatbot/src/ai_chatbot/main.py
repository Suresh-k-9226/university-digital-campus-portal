
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field

from ai_chatbot.rag_service import ask_university_assistant


app = FastAPI(
    title="University of Velen AI Assistant",
    description="RAG-powered university information chatbot",
    version="1.0.0",
)

# Allow the local React development server to communicate with FastAPI.
app.add_middleware(
    CORSMiddleware,
    allow_origins=[
        "http://localhost:5173",
        "http://127.0.0.1:5173",
    ],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


class ChatRequest(BaseModel):
    question: str = Field(
        min_length=1,
        max_length=1000,
        description="Question about the University of Velen",
    )


@app.get("/")
def home():
    return {
        "message": "University of Velen AI Assistant API",
        "status": "running",
    }


@app.get("/health")
def health_check():
    return {
        "status": "healthy",
        "service": "UDCP AI Chatbot",
    }


@app.post("/api/chat")
def chat(request: ChatRequest):
    try:
        return ask_university_assistant(request.question)

    except ValueError as error:
        raise HTTPException(
            status_code=400,
            detail=str(error),
        )

    except Exception:
        raise HTTPException(
            status_code=500,
            detail="The university assistant encountered an error. Please try again.",
        )
