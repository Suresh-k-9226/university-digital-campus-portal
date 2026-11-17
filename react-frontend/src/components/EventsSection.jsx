
import CollectionState from './CollectionState'
import EventCard from './EventCard'
import SectionHeading from './SectionHeading'
import { useAemCollection } from '../hooks/useAemCollection'
import { AEM_ENDPOINTS } from '../services/aemGraphql'
import { hasEventContent } from '../utils/contentFormatters'

function EventsSection({ onRegister }) {
  const events = useAemCollection(AEM_ENDPOINTS.events, 'eventList', 'events')
  const availableEvents = events.items.filter(hasEventContent)

  const sortedEvents = [...availableEvents].sort(
    (first, second) => getEventTimestamp(first) - getEventTimestamp(second)
  )

  return (
    <section
      className="events-section"
      id="events"
      aria-labelledby="events-heading"
    >
      <SectionHeading
        eyebrow="CAMPUS LIFE"
        title="Upcoming Events"
        description="Join the conversations, celebrations and experiences happening across our university."
        headingId="events-heading"
      />

      <CollectionState
        {...events}
        items={availableEvents}
        resourceName="events"
        emptyMessage="There are no upcoming events at this time."
        onRetry={events.retry}
      />

      {!events.loading && !events.error && availableEvents.length > 0 && (
        <div className="events-grid">
          {sortedEvents.map((event, index) => (
            <EventCard
              key={event._path || index}
              event={event}
              onRegister={onRegister}
            />
          ))}
        </div>
      )}
    </section>
  )
}

function getEventTimestamp(event) {
  const timestamp = Date.parse(event?.eventDate)
  return Number.isFinite(timestamp) ? timestamp : Number.MAX_SAFE_INTEGER
}

export default EventsSection
