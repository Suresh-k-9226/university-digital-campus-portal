import CollectionState from './CollectionState'
import FacultyCard from './FacultyCard'
import SectionHeading from './SectionHeading'
import { useAemCollection } from '../hooks/useAemCollection'
import { AEM_ENDPOINTS } from '../services/aemGraphql'
import { hasFacultyContent } from '../utils/contentFormatters'

function FacultySection() {
  const faculty = useAemCollection(AEM_ENDPOINTS.faculty, 'facultyList', 'faculty')
  const availableFaculty = faculty.items.filter(hasFacultyContent)

  return (
    <section
      className="faculty-section"
      id="faculty"
      aria-labelledby="faculty-heading"
    >
      <SectionHeading
        eyebrow="OUR PEOPLE"
        title="Meet Our Faculty"
        description="Learn from accomplished educators and researchers dedicated to your success."
        headingId="faculty-heading"
      />

      <CollectionState
        {...faculty}
        items={availableFaculty}
        resourceName="faculty"
        emptyMessage="Faculty profiles are not currently available."
        onRetry={faculty.retry}
      />

      {!faculty.loading && !faculty.error && availableFaculty.length > 0 && (
        <div className="faculty-grid">
          {availableFaculty.map((member, index) => (
            <FacultyCard key={member._path || index} faculty={member} />
          ))}
        </div>
      )}
    </section>
  )
}

export default FacultySection
