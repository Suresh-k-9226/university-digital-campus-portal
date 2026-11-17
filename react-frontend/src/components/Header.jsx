function Header() {
  return (
    <header className="site-header">
      <div className="top-bar">
        <div className="top-bar__inner">
          <span>Welcome to University of Velen</span>
          <span>Excellence in Education Since 1985</span>
        </div>
      </div>

      <nav className="main-nav">
        <a href="#home" className="brand" aria-label="University of Velen home">
          <span className="brand__emblem">V</span>
          <span className="brand__name">
            <strong>UNIVERSITY</strong>
            <small>OF VELEN</small>
          </span>
        </a>

        <div className="nav-links">
          <a href="#home">Home</a>
          <a href="#programs">Programs</a>
          <a href="#faculty">Faculty</a>
          <a href="#events">Events</a>
          <a href="#contact">Contact</a>
        </div>

        <a href="#admissions" className="apply-button">
          Apply Now
        </a>
      </nav>
    </header>
  )
}

export default Header
