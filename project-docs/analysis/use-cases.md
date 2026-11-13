# Use Case Specification
## University Digital Campus Portal (UDCP)

**Version:** 1.0  
**Date:** 13 November 2026

## 1. Actors

| Actor | Description |
|---|---|
| Content Author | Creates and updates university content through AEM. |
| Website Visitor | Views university information and courses. |
| Administrator | Manages AEM configurations and permissions. |
| Mobile Application | Retrieves structured course information using GraphQL. |
| Developer | Implements and maintains AEM functionality. |

## 2. Use Case List

| ID | Use Case | Primary Actor |
|---|---|---|
| UC-01 | Create Campus Page | Content Author |
| UC-02 | Manage Hero Banner | Content Author |
| UC-03 | Manage Course Highlights | Content Author |
| UC-04 | Manage Faculty Profiles | Content Author |
| UC-05 | View Upcoming Events | Website Visitor |
| UC-06 | Configure Event Listings | Administrator |
| UC-07 | Create Course Content Fragments | Content Author |
| UC-08 | Retrieve Courses Using GraphQL | Mobile Application |
| UC-09 | Filter Courses by School | Mobile Application |
| UC-10 | Display Course Content on Website | Website Visitor |
| UC-11 | Manage Authoring Permissions | Administrator |

## 3. Detailed Use Cases

### UC-01: Create Campus Page

**Actor:** Content Author

**Preconditions:**
- Author is logged into AEM.
- Author has page creation permissions.
- Campus Page template is available.

**Main Flow:**
1. Author opens AEM Sites.
2. Author navigates to the appropriate university content location.
3. Author selects Create Page.
4. Author selects Campus Page template.
5. Author enters page properties.
6. Author creates the page.
7. Author opens the page editor.
8. Author adds permitted components.
9. Author saves and previews the page.

**Postcondition:** Campus page is created using the editable template.

**Alternative Flow:** If the template is unavailable or the author lacks permission, page creation cannot proceed.

### UC-02: Manage Hero Banner

**Actor:** Content Author

**Preconditions:** A campus page exists and is editable.

**Main Flow:**
1. Author opens the page editor.
2. Author adds or selects Hero Banner.
3. Author opens the component dialog.
4. Author selects an image.
5. Author enters a headline.
6. Author configures a CTA link.
7. Author saves the dialog.

**Postcondition:** Hero Banner displays configured content.

### UC-03: Manage Course Highlights

**Actor:** Content Author

**Main Flow:**
1. Author opens the Course Highlights dialog.
2. Author adds a course entry.
3. Author enters course name, duration, fees and description.
4. Author adds additional entries using the multifield.
5. Author saves the component.

**Postcondition:** Multiple course entries are available for rendering.

### UC-04: Manage Faculty Profiles

**Actor:** Content Author

**Main Flow:**
1. Author opens the Faculty Profile component.
2. Author enters faculty name and designation.
3. Author selects the school.
4. Author selects a photograph.
5. Author enters biography.
6. Author saves the component.

**Postcondition:** Faculty profile is displayed on the website.

### UC-05: View Upcoming Events

**Actor:** Website Visitor

**Preconditions:** Event pages exist under the configured events root path.

**Main Flow:**
1. Visitor opens the university website.
2. Visitor navigates to the events section.
3. The component retrieves child pages.
4. The component applies the configured maximum item count.
5. The visitor sees the available event listings.

**Postcondition:** Upcoming event information is displayed.

**Alternative Flow:** If no events exist, the component handles the empty result without errors.

### UC-06: Configure Event Listings

**Actor:** Administrator

**Main Flow:**
1. Administrator accesses the applicable AEM OSGi configuration.
2. Administrator configures the events root path.
3. Administrator configures the maximum number of items.
4. Configuration is saved.
5. Events service uses the configuration.

**Postcondition:** Event listing behavior follows the configured settings.

### UC-07: Create Course Content Fragments

**Actor:** Content Author

**Preconditions:** Course Content Fragment Model is available.

**Main Flow:**
1. Author opens AEM Assets or the Content Fragments console.
2. Author creates a Course Content Fragment.
3. Author enters course name and school.
4. Author enters duration, fees and eligibility.
5. Author enters course description.
6. Author saves the fragment.

**Postcondition:** Structured course content is available for reuse.

### UC-08: Retrieve Courses Using GraphQL

**Actor:** Mobile Application

**Preconditions:**
- GraphQL endpoint is configured.
- Course Content Fragments are available for delivery.
- Client has access to the endpoint.

**Main Flow:**
1. Mobile application sends a GraphQL request.
2. AEM receives the request.
3. AEM executes the query.
4. AEM retrieves matching course fragments.
5. AEM returns structured course data.

**Postcondition:** Mobile application receives the requested course information.

### UC-09: Filter Courses by School

**Actor:** Mobile Application

**Main Flow:**
1. Mobile application sends a query with a school filter.
2. GraphQL processes the filter.
3. Matching course fragments are retrieved.
4. Structured results are returned.

**Postcondition:** Only matching courses are returned.

### UC-10: Display Course Content on Website

**Actor:** Website Visitor

**Main Flow:**
1. Author creates course fragments.
2. Website component reads the relevant fragments.
3. Component processes the course information.
4. Visitor opens the course section.
5. Course details are displayed.

**Postcondition:** Website displays information sourced from the shared Course Content Fragments.

### UC-11: Manage Authoring Permissions

**Actor:** Administrator

**Main Flow:**
1. Administrator accesses AEM user and group management.
2. Administrator assigns appropriate permissions.
3. Author attempts to access permitted content.
4. AEM enforces the configured permissions.

**Postcondition:** Only authorized users can perform protected actions.

## 4. Use Case Dependencies

| Use Case | Dependency |
|---|---|
| UC-01 | Campus Page editable template |
| UC-02 | Hero Banner component |
| UC-03 | Course Highlights component |
| UC-04 | Faculty Profile component |
| UC-05 | Events component and OSGi configuration |
| UC-06 | Events OSGi service |
| UC-07 | Course Content Fragment Model |
| UC-08 | GraphQL endpoint and Course Content Fragments |
| UC-09 | GraphQL filtering support |
| UC-10 | Course Content Fragments and website integration |
| UC-11 | AEM user and permission configuration |
