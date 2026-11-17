
import { useEffect, useRef, useState } from 'react'
import ReactMarkdown from 'react-markdown'

const API_URL = 'http://127.0.0.1:8000/api/chat'
const TYPING_SPEED = 15
const CHARACTERS_PER_TICK = 2

function AIChatbot() {
  const [isOpen, setIsOpen] = useState(false)
  const [question, setQuestion] = useState('')
  const [loading, setLoading] = useState(false)
  const [typingMessageId, setTypingMessageId] = useState(null)

  const [messages, setMessages] = useState([
    {
      id: 'welcome',
      role: 'assistant',
      content:
        "Hello! Welcome to the University of Velen! I'm your AI Campus Assistant. I can help you with university information, campus facilities, courses, faculty, admissions, placements, student services and upcoming events. What would you like to know?",
      displayedContent:
        "Hello! Welcome to the University of Velen! I'm your AI Campus Assistant. I can help you with university information, campus facilities, courses, faculty, admissions, placements, student services and upcoming events. What would you like to know?",
      sources: [],
    },
  ])

  const messagesEndRef = useRef(null)
  const typingPositionRef = useRef(0)
  const typingTextRef = useRef('')

  // Automatically scroll when messages or typing content change.
  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({
      behavior: 'smooth',
    })
  }, [messages, loading, isOpen])

  // Display the assistant response progressively.
  useEffect(() => {
    if (!typingMessageId) return

    typingPositionRef.current = 0

    const interval = setInterval(() => {
      typingPositionRef.current = Math.min(
        typingPositionRef.current + CHARACTERS_PER_TICK,
        typingTextRef.current.length
      )

      const currentText = typingTextRef.current.slice(
        0,
        typingPositionRef.current
      )

      setMessages((previous) =>
        previous.map((message) =>
          message.id === typingMessageId
            ? {
                ...message,
                displayedContent: currentText,
              }
            : message
        )
      )

      if (typingPositionRef.current >= typingTextRef.current.length) {
        clearInterval(interval)

        setMessages((previous) =>
          previous.map((message) =>
            message.id === typingMessageId
              ? { ...message, isTyping: false }
              : message
          )
        )

        setTypingMessageId(null)
      }
    }, TYPING_SPEED)

    return () => clearInterval(interval)
  }, [typingMessageId])

  async function handleSubmit(event) {
    event.preventDefault()

    const userQuestion = question.trim()

    if (!userQuestion || loading || typingMessageId) return

    setMessages((previous) => [
      ...previous,
      {
        id: `user-${Date.now()}`,
        role: 'user',
        content: userQuestion,
        sources: [],
      },
    ])

    setQuestion('')
    setLoading(true)

    try {
      const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/json',
        },
        body: JSON.stringify({
          question: userQuestion,
        }),
      })

      const data = await response.json()

      if (!response.ok) {
        throw new Error(
          data.detail || 'Unable to get a response. Please try again.'
        )
      }

      const answer = data.answer || 'No response received.'
      const assistantMessageId = `assistant-${Date.now()}`

      typingTextRef.current = answer
      typingPositionRef.current = 0

      setMessages((previous) => [
        ...previous,
        {
          id: assistantMessageId,
          role: 'assistant',
          content: answer,
          displayedContent: '',
          sources: Array.isArray(data.sources) ? data.sources : [],
          isTyping: true,
        },
      ])

      setTypingMessageId(assistantMessageId)
    } catch (error) {
      setMessages((previous) => [
        ...previous,
        {
          id: `error-${Date.now()}`,
          role: 'assistant',
          content:
            error.message ||
            'Unable to connect to the University AI Assistant. Please try again.',
          displayedContent:
            error.message ||
            'Unable to connect to the University AI Assistant. Please try again.',
          sources: [],
          isError: true,
        },
      ])
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="ai-chatbot">
      {isOpen && (
        <section
          className="ai-chat-window"
          aria-label="University AI Campus Assistant"
        >
          <header className="ai-chat-header">
            <div className="ai-chat-brand">
              <div className="ai-chat-avatar">V</div>

              <div>
                <h3>University AI Assistant</h3>
                <span>Campus Information Support</span>
              </div>
            </div>

            <button
              type="button"
              className="ai-chat-close"
              onClick={() => setIsOpen(false)}
              aria-label="Close chatbot"
            >
              ×
            </button>
          </header>

          <div className="ai-chat-messages" aria-live="polite">
            {messages.map((message) => {
              const uniqueSources = [
                ...new Map(
                  (message.sources || [])
                    .filter((source) => source.title || source.path)
                    .map((source) => [
                      source.path || source.title,
                      source,
                    ])
                ).values(),
              ]

              const displayedContent =
                message.displayedContent ?? message.content

              return (
                <div
                  key={message.id}
                  className={`ai-message-row ${
                    message.role === 'user'
                      ? 'user-message'
                      : 'assistant-message'
                  }`}
                >
                  <div
                    className={`ai-message ${
                      message.isError ? 'ai-message-error' : ''
                    }`}
                  >
                    <ReactMarkdown>
                      {displayedContent.replace(/\\\*/g, '*')}
                    </ReactMarkdown>

                    {message.isTyping && (
                      <span className="ai-cursor">▍</span>
                    )}

                    {!message.isTyping && uniqueSources.length > 0 && (
                      <div className="ai-message-sources">
                        <div className="ai-sources-heading">
                          <span className="ai-sources-icon">▤</span>
                          <strong>University sources</strong>
                          <span className="ai-sources-count">
                            {uniqueSources.length}
                          </span>
                        </div>

                        <div className="ai-sources-list">
                          {uniqueSources.map((source, sourceIndex) => (
                            <div
                              className="ai-source-card"
                              key={source.path || source.title || sourceIndex}
                            >
                              <div className="ai-source-icon">
                                {source.type === 'course'
                                  ? '▣'
                                  : source.type === 'faculty'
                                    ? '♙'
                                    : source.type === 'event'
                                      ? '▦'
                                      : '▤'}
                              </div>

                              <div className="ai-source-details">
                                <span className="ai-source-title">
                                  {source.title || 'University content'}
                                </span>

                                <small className="ai-source-category">
                                  {{
                                    university: 'University Information',
                                    course: 'Course',
                                    faculty: 'Faculty',
                                    event: 'Event',
                                  }[source.type] || 'University content'}
                                </small>
                              </div>
                            </div>
                          ))}
                        </div>
                      </div>
                    )}
                  </div>
                </div>
              )
            })}

            {loading && (
              <div className="ai-message-row assistant-message">
                <div className="ai-message ai-typing">
                  Thinking
                  <span>.</span>
                  <span>.</span>
                  <span>.</span>
                </div>
              </div>
            )}

            <div ref={messagesEndRef} />
          </div>

          <form className="ai-chat-form" onSubmit={handleSubmit}>
            <input
              type="text"
              value={question}
              onChange={(event) => setQuestion(event.target.value)}
              placeholder="Ask anything about the university..."
              aria-label="Ask the University AI Assistant"
              maxLength={1000}
              disabled={loading || Boolean(typingMessageId)}
            />

            <button
              type="submit"
              disabled={
                loading ||
                Boolean(typingMessageId) ||
                !question.trim()
              }
              aria-label="Send message"
            >
              ➤
            </button>
          </form>

          <div className="ai-chat-disclaimer">
            Powered by AEM Content Fragments and Gemini AI
          </div>
        </section>
      )}

      <button
        type="button"
        className="ai-chat-launcher"
        onClick={() => setIsOpen((previous) => !previous)}
        aria-label={isOpen ? 'Close AI assistant' : 'Open AI assistant'}
        aria-expanded={isOpen}
      >
        {isOpen ? '×' : '✦'}
      </button>
    </div>
  )
}

export default AIChatbot
