
from langchain_core.documents import Document

if __package__:
    from .aem_client import fetch_collection
else:
    from aem_client import fetch_collection


def extract_text(value):
    """Extract clean text from an AEM rich-text field."""

    if isinstance(value, str):
        return value.strip()

    if isinstance(value, dict):
        plaintext = value.get("plaintext")

        if isinstance(plaintext, str):
            return plaintext.strip()

    return ""


def format_value(value):
    """Convert AEM field values into readable text."""

    if isinstance(value, dict):
        return extract_text(value) or "Not specified"

    if value is None or value == "":
        return "Not specified"

    return str(value)


def create_course_document(course):
    title = course.get("courseName") or "Unnamed Course"

    content = f"""
University Course
Course Name: {title}
School: {course.get("school", "Not specified")}
Duration: {course.get("duration", "Not specified")}
Fees: {course.get("fees", "Not specified")}
Eligibility: {extract_text(course.get("eligibility"))}
Description: {extract_text(course.get("description"))}
""".strip()

    return Document(
        page_content=content,
        metadata={
            "source_type": "course",
            "title": title,
            "path": course.get("_path", ""),
        },
    )


def create_faculty_document(faculty):
    title = faculty.get("facultyName") or "Unnamed Faculty Member"

    content = f"""
University Faculty
Name: {title}
Designation: {faculty.get("designation", "Not specified")}
Department: {faculty.get("department", "Not specified")}
Qualification: {faculty.get("qualification", "Not specified")}
Experience: {faculty.get("experience", "Not specified")} years
Biography: {extract_text(faculty.get("biography"))}
Email: {faculty.get("email", "Not specified")}
""".strip()

    return Document(
        page_content=content,
        metadata={
            "source_type": "faculty",
            "title": title,
            "path": faculty.get("_path", ""),
        },
    )


def create_event_document(event):
    title = event.get("eventTitle") or "Unnamed Event"

    content = f"""
University Event
Event Name: {title}
Category: {event.get("eventCategory", "Not specified")}
Date: {event.get("eventDate", "Not specified")}
Time: {event.get("eventTime", "Not specified")}
Venue: {event.get("venue", "Not specified")}
Description: {extract_text(event.get("description"))}
Registration URL: {event.get("registrationUrl", "Not specified")}
""".strip()

    return Document(
        page_content=content,
        metadata={
            "source_type": "event",
            "title": title,
            "path": event.get("_path", ""),
        },
    )


def create_university_document(university):
    """Convert the University Information fragment into one RAG document."""

    title = university.get("universityName") or "University Information"

    fields = [
        ("University Name", "universityName"),
        ("Tagline", "tagline"),
        ("Overview", "overview"),
        ("History", "history"),
        ("Vision", "vision"),
        ("Mission", "mission"),
        ("Core Values", "coreValues"),
        ("Accreditation", "accreditation"),
        ("Established Year", "establishedYear"),

        ("Campus Address", "campusAddress"),
        ("Campus Location", "campusLocation"),
        ("Campus Overview", "campusOverview"),
        ("Infrastructure", "infrastructure"),
        ("Sports Facilities", "sportsFacilities"),
        ("Hostel Facilities", "hostelFacilities"),
        ("Transportation", "transportation"),
        ("Other Facilities", "otherFacilities"),

        ("Schools and Departments", "schoolsAndDepartments"),
        ("Academic Approach", "academicApproach"),
        ("Research Opportunities", "researchOpportunities"),
        ("Academic Calendar", "academicCalendar"),
        ("Academic Support", "academicSupport"),

        ("Admission Overview", "admissionOverview"),
        ("Admission Process", "admissionProcess"),
        ("General Eligibility", "generalEligibility"),
        ("Scholarship Information", "scholarshipInformation"),
        ("Student Support", "studentSupport"),
        ("Career Guidance", "careerGuidance"),

        ("Placement Overview", "placementOverview"),
        ("Placement Support", "placementSupport"),
        ("Industry Partnerships", "industryPartnerships"),
        ("Internship Opportunities", "internshipOpportunities"),
        ("Campus Life", "campusLife"),
        ("Clubs and Activities", "clubsAndActivities"),

        ("Contact Email", "contactEmail"),
        ("Contact Phone", "contactPhone"),
        ("Website URL", "websiteUrl"),
        ("Office Address", "officeAddress"),
        ("Office Hours", "officeHours"),
        ("Social Media Links", "socialMediaLinks"),
        ("Frequently Asked Questions", "frequentlyAskedQuestions"),
    ]

    content_lines = [
        "University of Velen - General University Information",
        f"University: {title}",
        "",
    ]

    for label, field_name in fields:
        value = format_value(university.get(field_name))

        if value != "Not specified":
            content_lines.append(f"{label}: {value}")

    return Document(
        page_content="\n".join(content_lines),
        metadata={
            "source_type": "university",
            "title": title,
            "path": university.get("_path", ""),
        },
    )


def load_aem_documents():
    """Fetch all AEM collections and convert them into RAG documents."""

    documents = []

    courses = fetch_collection("courses")
    faculty = fetch_collection("faculty")
    events = fetch_collection("events")
    university_items = fetch_collection("university")

    documents.extend(
        create_course_document(course) for course in courses
    )

    documents.extend(
        create_faculty_document(member) for member in faculty
    )

    documents.extend(
        create_event_document(event) for event in events
    )

    documents.extend(
        create_university_document(item)
        for item in university_items
    )

    return documents


if __name__ == "__main__":
    documents = load_aem_documents()

    print(f"\nTotal RAG documents created: {len(documents)}")

    for index, document in enumerate(documents, start=1):
        print(f"\n{'=' * 60}")
        print(f"DOCUMENT {index}")
        print(f"Type: {document.metadata['source_type']}")
        print(f"Title: {document.metadata['title']}")
        print(f"Path: {document.metadata['path']}")
        print(f"Content:\n{document.page_content}")
