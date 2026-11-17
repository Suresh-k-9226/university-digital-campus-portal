function HeroSection() {
  return (
    <section className="hero-section" id="home">
      <div className="hero-section__content">
        <span className="hero-section__eyebrow">DISCOVER YOUR POTENTIAL</span>

        <h1>
          Shape Your Future
          <span> Through Education</span>
        </h1>

        <p>
          Discover academic programs designed to inspire
          innovation, develop knowledge and prepare you
          for tomorrow&apos;s opportunities.
        </p>

        <div className="hero-actions">
          <a href="#admissions" className="hero-button">
            Apply Now →
          </a>

          <a href="#programs" className="hero-button hero-button--secondary">
            Explore Programs
          </a>
        </div>
      </div>

      <div className="hero-section__decoration" aria-hidden="true">
        <span>U</span>
      </div>
    </section>
  )
}

export default HeroSection
