# University Digital Campus Portal — Product Backlog

## 1. Product Vision

Build a hybrid Adobe Experience Manager (AEM) University Digital Campus Portal that enables university authors to manage website content independently and allows structured course information to be reused by the university website and mobile application.

## 2. Scrum Roles

- **Product Owner:** Trainer
- **Scrum Team / Developer:** Project developer
- **Scrum Master activities:** Facilitated by the developer where required.

## 3. Product Backlog

Estimates are initial relative effort estimates in story points. Priorities and estimates can be refined with the Product Owner.

| ID | User Story | Priority | Story Points | Acceptance Criteria | Status |
|---|---|---|---:|---|---|
| US-01 | As a Content Author, I want to create pages using a Campus Page editable template so that I can manage university pages independently. | High | 5 | Template contains header, footer and an unlocked responsive layout container. | To Do |
| US-02 | As a Content Author, I want to configure a Hero Banner so that I can promote university announcements. | High | 3 | Dialog supports image, headline and CTA link; missing optional values are handled safely. | To Do |
| US-03 | As a Content Author, I want to add multiple course highlights so that visitors can explore available programs. | High | 5 | Composite multifield supports course name, duration, fees and description for multiple entries. | To Do |
| US-04 | As a Content Author, I want to manage faculty profiles so that visitors can learn about university faculty. | Medium | 3 | Profile displays name, designation, school, photo and biography. | To Do |
| US-05 | As a Website Visitor, I want to view upcoming events so that I can discover campus activities. | Medium | 5 | Component retrieves event child pages and safely handles an empty result. | To Do |
| US-06 | As an Administrator, I want to configure event listing settings so that event listings can be controlled without code changes. | Medium | 3 | OSGi configuration provides events root path and maximum item count. | To Do |
| US-07 | As a Mobile Application, I want to retrieve structured course information through GraphQL so that I can display university courses. | High | 8 | Course Content Fragment Model, sample fragments and a working GraphQL query are available. | To Do |
| US-08 | As a Mobile Application, I want to filter courses by school so that users can find relevant programs. | High | 3 | GraphQL query supports filtering by school and returns matching course records. | To Do |
| US-09 | As a Website Visitor, I want to view course details sourced from Content Fragments so that website information remains consistent with the mobile app. | High | 5 | Website course component displays data from the same Course Content Fragments exposed through GraphQL. | To Do |
| US-10 | As a Developer, I want automated tests for Sling Models and OSGi services so that application behavior can be verified. | High | 5 | JUnit 5 tests using AEM Mocks and Mockito validate the required models and service. | To Do |
| US-11 | As a Developer, I want to track code changes through Git so that implementation history is maintained. | Medium | 2 | Project changes are committed with meaningful commit messages. | To Do |
| US-12 | As a Developer, I want to use GitHub Copilot responsibly so that development is faster while generated code is reviewed and validated. | Medium | 2 | Copilot Usage Log records prompts, accepted or modified suggestions and validation. | To Do |
| US-13 | As a Website Visitor, I want responsive pages so that I can access university information on desktop, tablet and mobile devices. | High | 3 | Page layout adapts to the supported screen sizes. | To Do |
| US-14 | As an Administrator, I want controlled authoring permissions so that only authorized users can modify protected content. | High | 3 | AEM permissions restrict authoring and administrative actions to authorized users. | To Do |
| US-15 | As a Website Visitor, I want components to handle missing content safely so that incomplete content does not break the page. | High | 3 | Components handle missing optional fields and empty collections without rendering errors. | To Do |

## 4. Technical and Quality Expectations

The following non-functional requirements apply across the backlog:

- Performance: Components and queries should operate efficiently under expected usage.
- Scalability: The design should accommodate additional schools, courses, faculty and events.
- Maintainability: Use reusable AEM components, Sling Models, OSGi services and established project conventions.
- Reusability: Use shared Course Content Fragments for website and mobile delivery.
- Reliability: Handle missing content and empty results safely.
- Security: Follow AEM permission and access-control practices.
- Accessibility: Use semantic markup, keyboard-accessible controls and appropriate alternative text.
- Compatibility: Build against the selected AEM Cloud Service SDK.
- Testability: Include automated tests for the required Sling Models and OSGi service.
- Observability: Record relevant build, test, deployment and troubleshooting evidence.

## 5. Definition of Ready

A backlog item is ready for sprint planning when:

- Its business purpose is understood.
- Acceptance criteria are documented.
- Dependencies are identified.
- The Product Owner has clarified outstanding questions.
- The team has an initial estimate.

## 6. Definition of Done

A backlog item is considered Done when:

- Implementation meets its acceptance criteria.
- Code follows appropriate AEM conventions.
- Relevant automated tests pass.
- Maven build succeeds.
- Local AEM functionality is verified where applicable.
- Git changes are committed.
- Relevant documentation and evidence are updated.

## 7. Product Backlog Management

The Product Backlog is a living document.

- Priorities may change after discussion with the Product Owner.
- Estimates are planning estimates, not actual hours spent.
- Status changes only after implementation and verification.
- New requirements and defects are added as backlog items.
- Sprint work is selected from the prioritized Product Backlog.

## 8. Project Delivery Plan

The project is planned across six one-day sprints:

| Sprint | Goal |
|---|---|
| Sprint 1 | Requirements, project foundation and Campus Page editable template |
| Sprint 2 | Four custom AEM components and authoring dialogs |
| Sprint 3 | Sling Models, OSGi service and ClientLibs |
| Sprint 4 | Content Fragment Model, sample fragments, GraphQL and website integration |
| Sprint 5 | Automated tests, Git, Copilot log and documentation |
| Sprint 6 | Final build, deployment verification, acceptance testing and viva preparation |

Each sprint will have its own planning, daily progress record, review and retrospective.
