import {
  formatFees,
  getContentText,
  getCourseSchool,
  getRichText,
} from '../utils/contentFormatters'

function CourseCard({ course }) {
  const school = getCourseSchool(course)
  const duration = getContentText(course?.duration)
  const courseName = getContentText(course?.courseName)
  const fees = formatFees(course?.fees)
  const description = getRichText(course?.description)
  const eligibility = getRichText(course?.eligibility)

  return (
    <article className="course-card">
      {(school || duration) && (
        <div className="course-card__top">
          {school && <span className="course-card__school">{school}</span>}
          {duration && (
            <span className="course-card__duration">{duration}</span>
          )}
        </div>
      )}

      {courseName && <h3 className="course-card__title">{courseName}</h3>}

      {fees && <div className="course-card__fees">{fees}</div>}

      {description && (
        <div className="course-card__section">
          <h4>About this program</h4>
          <p>{description}</p>
        </div>
      )}

      {eligibility && (
        <div className="course-card__section course-card__eligibility">
          <h4>Eligibility</h4>
          <p>{eligibility}</p>
        </div>
      )}

      <div className="course-card__footer">
        <span>University of Velen</span>
        <span className="course-card__arrow" aria-hidden="true">
          →
        </span>
      </div>
    </article>
  )
}

export default CourseCard
