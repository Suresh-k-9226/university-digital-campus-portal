
import {
  formatEventDate,
  getContentText,
  getRichText,
} from '../utils/contentFormatters'

function EventCard({ event, onRegister }) {
  const category = getContentText(event?.eventCategory)
  const date = formatEventDate(event?.eventDate)
  const title = getContentText(event?.eventTitle)
  const time = getContentText(event?.eventTime)
  const venue = getContentText(event?.venue)
  const description = getRichText(event?.description)

  return (
    <article className="event-card">
      <div className="event-card__art" aria-hidden="true">
        <span>UNIVERSITY OF VELEN</span>
      </div>

      <div className="event-card__content">
        {(category || date) && (
          <div className="event-card__topline">
            {category && (
              <span className="event-card__category">{category}</span>
            )}
            {date && <time className="event-card__date">{date}</time>}
          </div>
        )}

        {title && <h3 className="event-card__title">{title}</h3>}

        {(time || venue) && (
          <dl className="event-card__details">
            {time && (
              <div>
                <dt>Time</dt>
                <dd>{time}</dd>
              </div>
            )}
            {venue && (
              <div>
                <dt>Venue</dt>
                <dd>{venue}</dd>
              </div>
            )}
          </dl>
        )}

        {description && (
          <p className="event-card__description">{description}</p>
        )}

        <button
          type="button"
          className="event-card__registration"
          onClick={() => onRegister(event)}
          aria-label={`Register for ${title || 'event'}`}
        >
          Register for event
          <span aria-hidden="true">→</span>
        </button>
      </div>
    </article>
  )
}

export default EventCard
