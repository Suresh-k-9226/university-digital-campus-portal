# University of Velen – React Frontend

A modern, responsive university website frontend built using **React 19 and Vite**, integrated with **Adobe Experience Manager (AEM) Headless GraphQL** for dynamic content delivery and a **FastAPI-powered AI chatbot** for conversational university assistance.

The application provides an interactive university experience with dynamically retrieved courses, faculty profiles, upcoming events, admissions information, and AI-powered campus assistance.

## Features

### 1. Dynamic University Website

- Modern University of Velen homepage.
- Responsive navigation and reusable website sections.
- Hero section with campus imagery and calls to action.
- University highlights and admissions information.
- Dedicated faculty, courses, and events sections.
- Consistent layout with reusable React components.

### 2. AEM Headless GraphQL Integration

- Retrieves university content from Adobe Experience Manager using GraphQL.
- Uses persisted GraphQL queries to fetch content.
- Integrates AEM content into reusable React components.
- Separates GraphQL communication from UI components.
- Supports dynamic content rendering.

### 3. Course Management and Filtering

- Displays dynamically retrieved university courses.
- Supports filtering courses by school.
- Uses reusable course cards and filtering components.
- Handles missing and optional course information.

### 4. Faculty and Events

- Dynamically displays faculty profiles.
- Presents upcoming university events.
- Uses reusable cards and section components.
- Handles incomplete content gracefully.

### 5. Robust Content Handling

- Loading states while retrieving AEM content.
- Error states when content retrieval fails.
- Empty states when no content is available.
- Safe handling of missing images, links, and optional fields.
- Content formatting utilities for consistent rendering.

### 6. AI-Powered Campus Assistant

- Integrated chatbot interface.
- Communicates with the FastAPI backend.
- Uses a RAG architecture to answer university-related questions.
- Displays AI-generated responses.
- Supports Markdown-formatted chatbot responses.

### 7. User Experience

- Responsive website layout.
- Reusable React components.
- Smooth section navigation.
- Back-to-top functionality.
- Dedicated registration page.

## Technology Stack

| Technology | Purpose |
|---|---|
| React 19 | Frontend UI development |
| Vite 8 | Development server and build tooling |
| JavaScript (ES Modules) | Application logic |
| React Markdown | Rendering Markdown chatbot responses |
| Adobe Experience Manager | Headless CMS |
| GraphQL | Content retrieval from AEM |
| FastAPI | AI chatbot backend integration |
| ESLint | Code quality and linting |
| CSS3 | Styling and responsive layouts |
| npm | Dependency management |

## Project Structure

```text
react-frontend/
│
├── public/
│   ├── images/
│   │   └── campus-hero.png
│   ├── favicon.svg
│   └── icons.svg
│
├── src/
│   ├── assets/
│   │   ├── hero.png
│   │   ├── react.svg
│   │   └── vite.svg
│   │
│   ├── components/
│   │   ├── AdmissionsSection.jsx
│   │   ├── AIChatbot.jsx
│   │   ├── BackToTop.jsx
│   │   ├── CollectionState.jsx
│   │   ├── CourseCard.jsx
│   │   ├── CourseFilter.jsx
│   │   ├── CourseSection.jsx
│   │   ├── EventCard.jsx
│   │   ├── EventsSection.jsx
│   │   ├── FacultyCard.jsx
│   │   ├── FacultySection.jsx
│   │   ├── Header.jsx
│   │   ├── HeroSection.jsx
│   │   ├── HighlightsSection.jsx
│   │   ├── RegistrationPage.jsx
│   │   ├── SectionHeading.jsx
│   │   └── SiteFooter.jsx
│   │
│   ├── hooks/
│   │   └── useAemCollection.js
│   │
│   ├── services/
│   │   └── aemGraphql.js
│   │
│   ├── utils/
│   │   └── contentFormatters.js
│   │
│   ├── App.css
│   ├── App.jsx
│   ├── index.css
│   └── main.jsx
│
├── index.html
├── package.json
├── package-lock.json
├── vite.config.js
├── eslint.config.js
└── README.md
```

## Component Overview

| Component / File | Responsibility |
|---|---|
| `App.jsx` | Main application component and website composition. |
| `Header.jsx` | University navigation and header. |
| `HeroSection.jsx` | Homepage hero section. |
| `HighlightsSection.jsx` | University highlights. |
| `AdmissionsSection.jsx` | Admissions information and calls to action. |
| `CourseSection.jsx` | Course listing and presentation. |
| `CourseCard.jsx` | Individual course information. |
| `CourseFilter.jsx` | School-based course filtering. |
| `FacultySection.jsx` | Faculty listing. |
| `FacultyCard.jsx` | Individual faculty information. |
| `EventsSection.jsx` | Upcoming university events. |
| `EventCard.jsx` | Individual event information. |
| `AIChatbot.jsx` | Chatbot interface and backend communication. |
| `RegistrationPage.jsx` | Registration interface. |
| `SiteFooter.jsx` | Website footer. |
| `BackToTop.jsx` | Back-to-top navigation functionality. |
| `CollectionState.jsx` | Collection loading, error, and empty states. |
| `useAemCollection.js` | Custom hook for managing AEM content collections. |
| `aemGraphql.js` | AEM GraphQL communication and persisted query integration. |
| `contentFormatters.js` | Content formatting and safe data handling utilities. |

## AEM Headless GraphQL Integration

The React application retrieves content from Adobe Experience Manager through GraphQL persisted queries.

### Content Collections

The application uses the following persisted queries:

| Persisted Query | Purpose |
|---|---|
| `udcp/all-courses` | Retrieves university course information. |
| `udcp/all-faculty` | Retrieves faculty profiles. |
| `udcp/all-events` | Retrieves upcoming university events. |

### Integration Flow

1. React components request the required content.
2. The custom `useAemCollection` hook manages the collection state.
3. The `aemGraphql.js` service communicates with AEM GraphQL.
4. AEM executes the persisted query and returns content.
5. The application formats and validates the returned data.
6. Reusable React components render the content.

### Content Handling

The frontend includes handling for:

- Loading states.
- API and GraphQL errors.
- Empty content collections.
- Missing images.
- Missing links.
- Optional fields.
- Incomplete content.

These safeguards help prevent incomplete CMS content from breaking the user interface.

## AI Chatbot Integration

The React frontend includes an AI-powered campus assistant connected to the separate FastAPI chatbot backend.

### Integration Flow

1. The user enters a question in the chatbot interface.
2. React sends the question to the FastAPI `/api/chat` endpoint.
3. FastAPI invokes the RAG service.
4. The RAG service retrieves relevant university documents from ChromaDB.
5. Google Gemini generates a context-grounded response.
6. FastAPI returns the answer and supporting source information.
7. React displays the response in the chatbot interface.

### Backend API

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/` | API status |
| GET | `/health` | Backend health check |
| POST | `/api/chat` | Chatbot question and answer |

The chatbot backend is maintained separately in the `ai-chatbot` directory.

## Prerequisites

Make sure the following tools are installed:

- Node.js (compatible with Vite 8).
- npm.
- Access to the configured AEM GraphQL endpoint.
- A running FastAPI chatbot backend for AI chatbot functionality.

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Suresh-k-9226/university-digital-campus-portal.git
cd udcp/react-frontend
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Configure Environment Variables

Configure the AEM GraphQL endpoint and FastAPI chatbot backend URL according to the environment variable names used in your source code.

For example:

```env
VITE_AEM_GRAPHQL_ENDPOINT=your_aem_graphql_endpoint
VITE_CHATBOT_API_URL=http://127.0.0.1:8000
```

**Important:** These are illustrative variable names. Use the exact names referenced in your application code. Do not expose credentials or sensitive tokens in frontend environment variables, because Vite exposes `VITE_` variables to browser code.

### 4. Start the Development Server

```bash
npm run dev
```

The Vite development server will display the local URL in the terminal, typically:

`http://localhost:5173`

### 5. Build for Production

```bash
npm run build
```

The production-ready files are generated in the `dist/` directory.

### 6. Preview the Production Build

```bash
npm run preview
```

## Available Scripts

| Command | Description |
|---|---|
| `npm install` | Installs project dependencies. |
| `npm run dev` | Starts the Vite development server. |
| `npm run build` | Creates a production build. |
| `npm run lint` | Runs ESLint checks. |
| `npm run preview` | Previews the production build locally. |

## Development and Integration

The React frontend is part of the University of Velen UDCP capstone project.

It integrates two primary backend capabilities:

1. **Adobe Experience Manager:** Provides structured university content through Headless GraphQL.
2. **FastAPI AI Chatbot:** Provides conversational university assistance using RAG, ChromaDB, and Google Gemini.

## Future Enhancements

- Improved accessibility and keyboard navigation.
- Enhanced chatbot conversation history.
- Additional university content sections.
- Further frontend performance optimization.
- Enhanced responsive behavior across devices.
- Additional UI and integration testing.

## Author

**Suresh Kandregula**

University of Velen – UDCP Capstone Project