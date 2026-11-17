export function getContentText(value) {
  if (typeof value === 'string') {
    return value.trim()
  }

  if (typeof value === 'number' && Number.isFinite(value)) {
    return String(value)
  }

  return ''
}

export function getRichText(value) {
  return getContentText(value?.plaintext)
}

export function formatFees(fees) {
  const feeValue = getContentText(fees)
  const amount = feeValue ? Number(feeValue) : Number.NaN

  if (!Number.isFinite(amount)) {
    return ''
  }

  return new Intl.NumberFormat('en-IN', {
    style: 'currency',
    currency: 'INR',
    maximumFractionDigits: 0,
  }).format(amount)
}

export function formatEventDate(value) {
  const dateText = getContentText(value)
  const dateParts = dateText.match(/^(\d{4})-(\d{2})-(\d{2})/)

  if (!dateParts) {
    return ''
  }

  const [, year, month, day] = dateParts
  const date = new Date(Date.UTC(Number(year), Number(month) - 1, Number(day)))

  if (
    date.getUTCFullYear() !== Number(year) ||
    date.getUTCMonth() !== Number(month) - 1 ||
    date.getUTCDate() !== Number(day)
  ) {
    return ''
  }

  return new Intl.DateTimeFormat('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    timeZone: 'UTC',
  }).format(date)
}

export function getCourseSchool(course) {
  return typeof course?.school === 'string' ? course.school.trim() : ''
}

export function hasCourseContent(course) {
  return Boolean(
    getContentText(course?.courseName) ||
      getCourseSchool(course) ||
      getContentText(course?.duration) ||
      formatFees(course?.fees) ||
      getRichText(course?.description) ||
      getRichText(course?.eligibility)
  )
}

export function getFacultyInitials(name) {
  return getContentText(name)
    .trim()
    .split(/\s+/)
    .filter(Boolean)
    .slice(0, 2)
    .map((part) => part[0])
    .join('')
    .toUpperCase()
}

export function formatExperience(value) {
  const years = getContentText(value)
  const numericYears = Number(years)

  if (!years || !Number.isFinite(numericYears) || numericYears < 0) {
    return ''
  }

  return `${years} year${numericYears === 1 ? '' : 's'} of experience`
}

export function hasFacultyContent(faculty) {
  return Boolean(
    getContentText(faculty?.facultyName) ||
      getContentText(faculty?.designation) ||
      getContentText(faculty?.department) ||
      getContentText(faculty?.qualification) ||
      formatExperience(faculty?.experience) ||
      getRichText(faculty?.biography) ||
      getEmailAddress(faculty?.email)
  )
}

export function hasEventContent(event) {
  return Boolean(
    getContentText(event?.eventCategory) ||
      getContentText(event?.eventTitle) ||
      formatEventDate(event?.eventDate) ||
      getContentText(event?.eventTime) ||
      getContentText(event?.venue) ||
      getRichText(event?.description) ||
      getSafeLink(event?.registrationUrl)
  )
}

export function getSafeLink(value) {
  const href = getContentText(value)

  if (!href || href === '#' || href === '?') {
    return ''
  }

  try {
    const url = new URL(href, 'https://university-of-velen.invalid')
    if (url.protocol === 'http:' || url.protocol === 'https:') {
      return href
    }

    if (url.protocol === 'mailto:' && /^[^\s@]+@[^\s@]+$/.test(url.pathname)) {
      return href
    }

    if (url.protocol === 'tel:' && /^[+()\d.\-\s]+$/.test(url.pathname)) {
      return href
    }

    return ''
  } catch {
    return ''
  }
}

export function getEmailAddress(value) {
  const email = getContentText(value)
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) ? email : ''
}
