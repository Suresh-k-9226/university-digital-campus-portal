import {
  getContentText,
  getEmailAddress,
  formatExperience,
  getFacultyInitials,
  getRichText,
} from '../utils/contentFormatters'

function FacultyCard({ faculty }) {
  const name = getContentText(faculty?.facultyName)
  const designation = getContentText(faculty?.designation)
  const department = getContentText(faculty?.department)
  const qualification = getContentText(faculty?.qualification)
  const experience = formatExperience(faculty?.experience)
  const biography = getRichText(faculty?.biography)
  const email = getEmailAddress(faculty?.email)

  return (
    <article className="faculty-card">
      {(name || designation) && (
        <div className="faculty-card__profile">
          {name && (
            <div className="faculty-card__avatar" aria-hidden="true">
              {getFacultyInitials(name)}
            </div>
          )}
          <div>
            {name && <h3 className="faculty-card__name">{name}</h3>}
            {designation && (
              <p className="faculty-card__designation">{designation}</p>
            )}
          </div>
        </div>
      )}

      {(department || qualification || experience) && (
        <dl className="faculty-card__details">
          {department && (
            <div>
              <dt>Department</dt>
              <dd>{department}</dd>
            </div>
          )}
          {qualification && (
            <div>
              <dt>Qualification</dt>
              <dd>{qualification}</dd>
            </div>
          )}
          {experience && (
            <div>
              <dt>Experience</dt>
              <dd>{experience}</dd>
            </div>
          )}
        </dl>
      )}

      {biography && (
        <div className="faculty-card__biography">
          <h4>Biography</h4>
          <p>{biography}</p>
        </div>
      )}

      {email && (
        <div className="faculty-card__footer">
          <a
            href={`mailto:${email}`}
            aria-label={`Email ${name || 'faculty member'} at ${email}`}
          >
            {email}
          </a>
        </div>
      )}
    </article>
  )
}

export default FacultyCard
