function CourseFilter({
  schools = [],
  selectedSchool = '',
  onSchoolChange,
}) {
  return (
    <label className="course-filter" htmlFor="course-school-filter">
      <span>Filter by school</span>
      <select
        id="course-school-filter"
        value={selectedSchool}
        onChange={(event) => onSchoolChange(event.target.value)}
        disabled={schools.length === 0}
      >
        <option value="">All schools</option>
        {schools.map((school) => (
          <option key={school} value={school}>
            {school}
          </option>
        ))}
      </select>
    </label>
  )
}

export default CourseFilter
