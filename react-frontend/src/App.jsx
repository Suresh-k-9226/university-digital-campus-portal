
import { useState } from 'react'
import './App.css'
import AdmissionsSection from './components/AdmissionsSection'
import BackToTop from './components/BackToTop'
import CourseSection from './components/CourseSection'
import EventsSection from './components/EventsSection'
import FacultySection from './components/FacultySection'
import Header from './components/Header'
import HeroSection from './components/HeroSection'
import HighlightsSection from './components/HighlightsSection'
import RegistrationPage from './components/RegistrationPage'
import SiteFooter from './components/SiteFooter'
import AIChatbot from './components/AIChatbot'

function App() {
  const [selectedEvent, setSelectedEvent] = useState(null)

  function handleBackToHome() {
    setSelectedEvent(null)
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }

  return (
    <div className="university-app">
      <Header />

      <main>
        {selectedEvent ? (
          <RegistrationPage
            event={selectedEvent}
            onBack={handleBackToHome}
          />
        ) : (
          <>
            <HeroSection />
            <HighlightsSection />
            <CourseSection />
            <FacultySection />
            <EventsSection onRegister={setSelectedEvent} />
            <AdmissionsSection />
          </>
        )}
      </main>

      <SiteFooter />
      <BackToTop />
      <AIChatbot />
    </div>
  )
}

export default App
