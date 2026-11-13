# Software Requirements Specification (SRS)
## University Digital Campus Portal (UDCP)

**Project:** University Digital Campus Portal  
**Version:** 1.0  
**Date:** 13 November 2026  
**Industry:** Higher Education  
**Technology:** Adobe Experience Manager (AEM) as a Cloud Service  
**Architecture:** Hybrid — Headful and Headless AEM

---

# 1. Introduction

## 1.1 Purpose

The purpose of this document is to define the functional and non-functional requirements of the University Digital Campus Portal.

The portal will provide a centralized content management solution for university websites and enable structured course information to be reused by mobile applications.

## 1.2 Project Background

A private university offering Engineering, Management and Design programs currently manages its website using hand-coded pages.

The admissions team depends on developers to update course information, faculty profiles and university events.

Additionally, the university mobile application does not have a reliable reusable source of course information.

This creates content maintenance difficulties, duplication and inconsistent information across platforms.

## 1.3 Project Objectives

1. Provide a centralized AEM-based content management platform.
2. Enable authors to manage website content without developer assistance for routine updates.
3. Develop reusable AEM components.
4. Implement editable templates for consistent page creation.
5. Create structured Course Content Fragments.
6. Expose course information through GraphQL.
7. Reuse the same course data across website and mobile application.
8. Implement automated testing and maintainable application code.

# 2. Project Scope

## 2.1 In Scope

- AEM Cloud Service SDK-compatible project setup.
- AEM Sites page authoring.
- Editable Templates and Content Policies.
- Custom Hero Banner component.
- Course Highlights component with composite multifield.
- Faculty Profile component.
- Upcoming Events List component.
- Sling Models using interface and implementation patterns.
- OSGi configuration and services.
- ClientLibs for CSS and JavaScript.
- Course Content Fragment Model.
- At least six sample Course Content Fragments.
- GraphQL endpoint and queries.
- Persisted GraphQL query filtered by school.
- Website integration with Course Content Fragments.
- JUnit 5, AEM Mocks and Mockito testing.
- Git version control.
- GitHub Copilot Usage Log.
- Agile Scrum documentation.
- README and technical documentation.

## 2.2 Out of Scope

- Development of a complete native Android or iOS application.
- Production university authentication integration.
- Real payment gateway integration.
- Production Cloud Manager deployment unless access and environment are provided.
- Integration with external student information systems.
- Real-time admission application processing.

# 3. Stakeholders and System Actors

| Stakeholder / Actor | Responsibility |
|---|---|
| University Management | Defines business objectives and approves the portal. |
| Admissions Team | Maintains course and admission-related content. |
| Content Author | Creates and updates website pages and content. |
| Administrator | Manages AEM configurations and permissions. |
| Website Visitor | Views university information. |
| Mobile Application | Retrieves structured course information through GraphQL. |
| Developer | Implements, tests and maintains the AEM solution. |
| Product Owner | Trainer responsible for product requirements and prioritization. |

# 4. Functional Requirements

Functional requirements define what the system must do.

| ID | Requirement | Description | Priority |
|---|---|---|---|
| FR-01 | Page Management | Authors must be able to create campus pages using an editable template. | High |
| FR-02 | Hero Banner | Authors must be able to configure an image, headline and CTA link. | High |
| FR-03 | Course Highlights | Authors must be able to add multiple course entries with name, duration, fees and description. | High |
| FR-04 | Faculty Profile | Authors must be able to manage faculty name, designation, school, photograph and biography. | Medium |
| FR-05 | Upcoming Events | The system must display event pages retrieved from a configured parent path. | Medium |
| FR-06 | Events Configuration | Administrators must be able to configure the events root path and maximum item count using OSGi configuration. | Medium |
| FR-07 | Course Content Model | The system must provide a structured Course Content Fragment Model. | High |
| FR-08 | Sample Course Content | Authors must be able to create at least six Course Content Fragments. | High |
| FR-09 | GraphQL Delivery | The system must expose structured course information through GraphQL. | High |
| FR-10 | School Filtering | GraphQL queries must support filtering course information by school. | High |
| FR-11 | Persisted Queries | The system must provide a persisted GraphQL query for course retrieval and school-based filtering. | High |
| FR-12 | Website Integration | The website must display course information sourced from the Course Content Fragments. | High |
| FR-13 | Content Reusability | Website and mobile application delivery must use the same structured course source. | High |
| FR-14 | Responsive Website | The website must provide responsive layouts for supported screen sizes. | High |
| FR-15 | Author Independence | Authors must be able to update routine website content without developer intervention. | High |

# 5. Non-Functional Requirements

Non-functional requirements define the expected quality and operational characteristics of the system.

| ID | Requirement | Description |
|---|---|---|
| NFR-01 | Performance | Components and GraphQL queries should operate efficiently under expected usage. |
| NFR-02 | Scalability | The solution should support additional schools, courses, faculty and events. |
| NFR-03 | Security | AEM authoring and administrative operations must follow appropriate permission controls. |
| NFR-04 | Maintainability | Code should follow AEM conventions and use reusable components, Sling Models and OSGi services. |
| NFR-05 | Reusability | Structured course information should be reusable across website and mobile application channels. |
| NFR-06 | Responsiveness | Pages should adapt to desktop, tablet and mobile screen sizes. |
| NFR-07 | Reliability | Missing optional content and empty collections must be handled safely. |
| NFR-08 | Testability | Required Sling Models and OSGi services must have automated tests. |
| NFR-09 | Compatibility | The project must build against the selected AEM Cloud Service SDK. |
| NFR-10 | Accessibility | Components should use semantic markup, appropriate alternative text and keyboard-accessible controls. |
| NFR-11 | Observability | Build, testing, deployment and application issues should be diagnosable through available logs and validation. |
| NFR-12 | Content Consistency | Website and mobile application must consume the same structured course content source. |
| NFR-13 | Version Control | Source code changes must be maintained using Git. |
| NFR-14 | Documentation | Technical implementation and development decisions must be documented. |

# 6. Business Rules

1. Course information must have a consistent structured representation.
2. Each course must belong to an appropriate university school.
3. Authors must be able to manage routine content using AEM authoring interfaces.
4. Events displayed by the events component must be retrieved from the configured root path.
5. The number of displayed events must respect the configured maximum.
6. Missing optional component fields must not break page rendering.
7. The same Course Content Fragments must be used for website and GraphQL delivery.
8. Only authorized users should be able to perform protected authoring and administrative operations.

# 7. System Architecture Requirements

The solution will use a hybrid AEM architecture.

## 7.1 Headful AEM Sites

The website will use:

- AEM Sites.
- HTL.
- Sling Models.
- Editable Templates.
- Content Policies.
- Custom AEM Components.
- Touch UI Dialogs.
- Client Libraries.
- OSGi Services.

## 7.2 Headless AEM

Structured course information will use:

- Content Fragment Models.
- Course Content Fragments.
- GraphQL endpoint.
- GraphQL queries.
- Persisted Queries.

## 7.3 Content Delivery

The website and mobile application will consume the same structured Course Content Fragments.

The website will display course information through an AEM component, while the mobile application will retrieve structured information using GraphQL.

# 8. Technology Requirements

| Technology | Purpose |
|---|---|
| AEM Cloud Service SDK | Local AEM development and validation |
| Java 21 | Backend development |
| Maven | Build and dependency management |
| Sling Models | Backend component data models |
| HTL | AEM component rendering |
| OSGi | Services and configuration |
| Content Fragments | Structured reusable course content |
| GraphQL | Headless content delivery |
| ClientLibs | Frontend CSS and JavaScript |
| JUnit 5 | Automated unit testing |
| AEM Mocks | AEM-specific test support |
| Mockito | Mocking dependencies in tests |
| Git | Version control |
| GitHub Copilot | AI-assisted development with human review |

# 9. Constraints

1. The project must be created from the AEM Project Archetype.
2. The solution must not use WKND or another existing application as its starting project.
3. The implementation must be compatible with the selected AEM Cloud Service SDK.
4. Local development and deployment will use the AEM Author instance on port 4504.
5. The project must be completed within the six-day capstone timeline.
6. GitHub Copilot-generated code must be reviewed and validated.
7. Actual Cloud Manager deployment depends on the availability of an authorized Cloud Manager environment.

# 10. Assumptions and Dependencies

## Assumptions

- AEM Author is accessible locally.
- The generated Maven project builds successfully.
- Java 21 and Maven are available.
- Sample university course, faculty and event data can be created for demonstration.
- The trainer acts as Product Owner.

## Dependencies

- AEM Cloud Service SDK.
- AEM Content Fragment and GraphQL capabilities.
- Required Maven dependencies and AEM APIs.
- Access to AEM Author for template and content configuration.
- Git repository for source control.

# 11. Acceptance Overview

The project will be considered ready for final acceptance when:

1. The AEM project builds successfully.
2. The starter and final packages are verified in local AEM Author.
3. The Campus Page editable template works.
4. All four custom components function correctly.
5. Required Sling Models and OSGi service are implemented.
6. ClientLibs are integrated.
7. Course Content Fragment Model and at least six sample fragments exist.
8. GraphQL and persisted queries work, including school-based filtering.
9. Website and mobile delivery use the same structured course content.
10. Required automated tests pass.
11. Git history and Copilot Usage Log are maintained.
12. README and technical documentation are completed.

# 12. Document Approval

| Role | Name / Responsibility | Status |
|---|---|---|
| Product Owner | Trainer | Pending review |
| Developer | Project developer | Prepared |
| Final Approval | Product Owner | Pending |
