# User Stories and Acceptance Criteria
## University Digital Campus Portal (UDCP)

**Version:** 1.0  
**Date:** 13 November 2026

## 1. User Stories

| ID | User Story | Priority |
|---|---|---|
| US-01 | As a Content Author, I want to create pages using a Campus Page editable template so that I can manage university pages independently. | High |
| US-02 | As a Content Author, I want to configure a Hero Banner so that I can promote university announcements. | High |
| US-03 | As a Content Author, I want to add multiple course highlights so that visitors can explore available programs. | High |
| US-04 | As a Content Author, I want to manage faculty profiles so that visitors can learn about university faculty. | Medium |
| US-05 | As a Website Visitor, I want to view upcoming events so that I can discover campus activities. | Medium |
| US-06 | As an Administrator, I want to configure event listing settings so that event listings can be controlled without code changes. | Medium |
| US-07 | As a Mobile Application, I want to retrieve structured course information through GraphQL so that I can display university courses. | High |
| US-08 | As a Mobile Application, I want to filter courses by school so that users can find relevant programs. | High |
| US-09 | As a Website Visitor, I want to view course details sourced from Content Fragments so that website information remains consistent with the mobile app. | High |
| US-10 | As a Developer, I want automated tests for Sling Models and OSGi services so that application behavior can be verified. | High |
| US-11 | As a Developer, I want to track code changes through Git so that implementation history is maintained. | Medium |
| US-12 | As a Developer, I want to use GitHub Copilot responsibly so that generated code is reviewed and validated. | Medium |

## 2. Acceptance Criteria

### US-01: Campus Page Template
- Given an authorized author is logged into AEM,
- When the author creates a page using the Campus Page template,
- Then the page must contain the configured header, footer and responsive layout container.
- And the author must be able to add permitted components to the layout container.

### US-02: Hero Banner
- Given an author is editing a campus page,
- When the author configures the Hero Banner,
- Then the component must display its image, headline and CTA link.
- And missing optional values must not cause rendering errors.

### US-03: Course Highlights
- Given an author is editing Course Highlights,
- When the author adds multiple course entries,
- Then every entry must support course name, duration, fees and description.
- And all valid entries must render correctly.

### US-04: Faculty Profile
- Given an author is editing Faculty Profile,
- When the author provides faculty details,
- Then the component must display name, designation, school, photo and biography.

### US-05: Upcoming Events
- Given event pages exist under the configured root path,
- When a visitor opens the events section,
- Then the component must retrieve and display the configured maximum number of events.
- And an empty result must be handled safely.

### US-06: OSGi Configuration
- Given an administrator has configured the event service,
- When the service executes,
- Then it must use the configured root path and maximum item count.

### US-07: GraphQL Course Retrieval
- Given course fragments are available for delivery,
- When a client sends a valid GraphQL query,
- Then AEM must return the requested structured course information.

### US-08: School-Based Filtering
- Given courses exist for multiple schools,
- When a client queries a particular school,
- Then only matching course records must be returned.

### US-09: Website Course Integration
- Given a Course Content Fragment contains valid course information,
- When a visitor opens the website course section,
- Then the website component must display information from that fragment.
- And the same structured content source must be available through GraphQL.

### US-10: Automated Testing
- Given the required Sling Models and OSGi service are implemented,
- When the Maven test suite executes,
- Then JUnit 5 tests using AEM Mocks and Mockito must validate expected behavior.

### US-11: Git Tracking
- Given source code changes have been made,
- When the developer commits changes,
- Then Git must maintain meaningful commit history.

### US-12: GitHub Copilot Usage
- Given GitHub Copilot suggestions are used,
- When suggested code is incorporated,
- Then the developer must review, validate and record the usage in the Copilot Usage Log.


### US-13: Responsive Campus Pages

**User Story:**

As a Website Visitor, I want responsive pages so that I can access university information on desktop, tablet and mobile devices.

**Acceptance Criteria:**

- Given a visitor opens the campus portal on a desktop, when the page loads, then the layout should display correctly.
- Given a visitor opens the portal on a tablet or mobile device, when the page loads, then the layout should adapt to the screen size.
- Given a component contains images or text, when the viewport changes, then the content should remain readable and usable.

### US-14: Controlled Authoring Permissions

**User Story:**

As an Administrator, I want controlled authoring permissions so that only authorized users can modify protected content.

**Acceptance Criteria:**

- Given an authorized Content Author, when they access permitted content, then they should be able to create or edit it.
- Given a user without the required permissions, when they attempt a restricted authoring action, then AEM should deny that action.
- Given an Administrator, when they manage permissions, then they should be able to control access using AEM's permission model.

### US-15: Safe Handling of Missing Content

**User Story:**

As a Website Visitor, I want components to handle missing content safely so that incomplete content does not break the page.

**Acceptance Criteria:**

- Given an optional component field is empty, when the page renders, then the component should not display broken or misleading content.
- Given an events or courses collection is empty, when the component renders, then it should handle the empty result gracefully.
- Given an image or link is missing, when the component renders, then it should not produce a broken image or invalid link.


## 3. Definition of Acceptance

A user story can be marked Done only when:
1. All applicable acceptance criteria are satisfied.
2. Implementation is reviewed.
3. Relevant tests pass.
4. The feature is verified in the applicable AEM environment.
5. Documentation and evidence are updated.
6. Changes are committed to Git.
