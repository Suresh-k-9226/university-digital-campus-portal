function SectionHeading({ eyebrow, title, description, headingId }) {
  return (
    <div className="section-heading">
      <span>{eyebrow}</span>
      <h2 id={headingId}>{title}</h2>
      <p>{description}</p>
    </div>
  )
}

export default SectionHeading
