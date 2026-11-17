
import { useState } from 'react'
import {
  formatEventDate,
  getContentText,
} from '../utils/contentFormatters'

function RegistrationPage({ event, onBack }) {
  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    phone: '',
  })

  const [isRegistered, setIsRegistered] = useState(false)

  const eventTitle =
    getContentText(event?.eventTitle) || 'University Event'
  const eventDate = formatEventDate(event?.eventDate)
  const eventTime = getContentText(event?.eventTime)
  const venue = getContentText(event?.venue)

  function handleChange(event) {
    const { name, value } = event.target

    setFormData((previous) => ({
      ...previous,
      [name]: value,
    }))
  }

  function handleSubmit(event) {
    event.preventDefault()
    setIsRegistered(true)
  }

  return (
    <section className="registration-page">
      <div className="registration-container">
        <button
          type="button"
          className="registration-back"
          onClick={onBack}
        >
          ← Back to Events
        </button>

        <div className="registration-heading">
          <span>UNIVERSITY OF VELEN</span>
          <h1>Event Registration</h1>
          <p>Complete the form below to register for this event.</p>
        </div>

        <div className="registration-event">
          <span>SELECTED EVENT</span>
          <h2>{eventTitle}</h2>

          {eventDate && <p>Date: {eventDate}</p>}
          {eventTime && <p>Time: {eventTime}</p>}
          {venue && <p>Venue: {venue}</p>}
        </div>

        <form
          className="registration-form"
          onSubmit={handleSubmit}
        >
          <h2>Personal Information</h2>

          <div className="registration-field">
            <label htmlFor="fullName">Full Name</label>
            <input
              id="fullName"
              name="fullName"
              type="text"
              placeholder="Enter your full name"
              value={formData.fullName}
              onChange={handleChange}
              minLength={2}
              maxLength={100}
              autoComplete="name"
              required
            />
          </div>

          <div className="registration-field">
            <label htmlFor="email">Email Address</label>
            <input
              id="email"
              name="email"
              type="email"
              placeholder="Enter your email address"
              value={formData.email}
              onChange={handleChange}
              autoComplete="email"
              required
            />
          </div>

          <div className="registration-field">
            <label htmlFor="phone">Phone Number</label>
            <input
              id="phone"
              name="phone"
              type="tel"
              placeholder="Enter your phone number"
              value={formData.phone}
              onChange={handleChange}
              pattern="[+]?[0-9]{10,15}"
              title="Enter 10 to 15 digits, optionally starting with +"
              autoComplete="tel"
              required
            />
          </div>

          <button
            type="submit"
            className="registration-submit"
          >
            Submit Registration
          </button>
        </form>
      </div>

      {isRegistered && (
        <div
          className="registration-modal-overlay"
          role="presentation"
        >
          <div
            className="registration-modal"
            role="dialog"
            aria-modal="true"
            aria-labelledby="registration-success-title"
          >
            <div className="registration-success-icon">
              ✓
            </div>

            <h2 id="registration-success-title">
              Successfully Registered!
            </h2>

            <p>
              Your registration for <strong>{eventTitle}</strong> has
              been completed successfully.
            </p>

            <button
              type="button"
              onClick={onBack}
              className="registration-submit"
            >
              Back to Homepage
            </button>
          </div>
        </div>
      )}
    </section>
  )
}

export default RegistrationPage
