import { useState } from 'react'
import CollectionState from './CollectionState'
import CourseCard from './CourseCard'
import CourseFilter from './CourseFilter'
import SectionHeading from './SectionHeading'
import { useAemCollection } from '../hooks/useAemCollection'
import { AEM_ENDPOINTS } from '../services/aemGraphql'
import {
  getCourseSchool,
  hasCourseContent,
} from '../utils/contentFormatters'

function CourseSection() {
  const [selectedSchool, setSelectedSchool] = useState('')
  const courses = useAemCollection(
    AEM_ENDPOINTS.courses,
    'courseList',
    'academic programs'
  )
  const availableCourses = courses.items.filter(hasCourseContent)
  const schools = [...new Set(availableCourses.map(getCourseSchool).filter(Boolean))]
    .sort((first, second) => first.localeCompare(second))
  const activeSchool = schools.includes(selectedSchool) ? selectedSchool : ''
  const filteredCourses = activeSchool
    ? availableCourses.filter((course) => getCourseSchool(course) === activeSchool)
    : availableCourses

  return (
    <section className="programs-section" id="programs">
      <SectionHeading
        eyebrow="ACADEMICS"
        title="Explore Our Academic Programs"
        description="Find the right program to pursue your ambitions and build your future."
      />

      {courses.loading || courses.error ? (
        <CollectionState
          {...courses}
          resourceName="academic programs"
          emptyMessage="No academic programs are currently available."
          onRetry={courses.retry}
        />
      ) : (
        <>
          <div className="course-toolbar">
            <CourseFilter
              schools={schools}
              selectedSchool={activeSchool}
              onSchoolChange={setSelectedSchool}
            />

            <p className="course-count" aria-live="polite">
              Showing {filteredCourses.length} of {availableCourses.length} academic
              programs
            </p>
          </div>

          {availableCourses.length === 0 ? (
            <div className="empty-state" role="status">
              No academic programs are currently available.
            </div>
          ) : filteredCourses.length > 0 ? (
            <div className="courses-grid">
              {filteredCourses.map((course, index) => (
                <CourseCard key={course._path || index} course={course} />
              ))}
            </div>
          ) : (
            <div className="empty-state" role="status">
              No academic programs are available for this school.
            </div>
          )}
        </>
      )}
    </section>
  )
}

export default CourseSection
