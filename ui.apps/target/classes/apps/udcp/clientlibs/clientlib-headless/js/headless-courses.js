(function (document) {
    "use strict";

    function createElement(tag, className, text) {
        var element = document.createElement(tag);

        if (className) {
            element.className = className;
        }

        if (text !== undefined && text !== null) {
            element.textContent = text;
        }

        return element;
    }

    function formatFees(fees) {
        if (fees === null || fees === undefined || fees === "") {
            return "Fees not available";
        }

        var amount = Number(fees);

        if (!Number.isFinite(amount)) {
            return "Fees not available";
        }

        return new Intl.NumberFormat("en-IN", {
            style: "currency",
            currency: "INR",
            maximumFractionDigits: 0
        }).format(amount);
    }

    function renderCourses(container, courses) {
        var results = container.querySelector(
            "[data-headless-courses-results]"
        );

        results.replaceChildren();

        courses.forEach(function (course) {
            var card = createElement(
                "article",
                "cmp-headless-courses__card"
            );

            card.appendChild(createElement(
                "h3",
                "cmp-headless-courses__name",
                course.courseName || "Untitled Course"
            ));

            if (course.school) {
                card.appendChild(createElement(
                    "p",
                    "cmp-headless-courses__school",
                    course.school
                ));
            }

            var details = createElement(
                "div",
                "cmp-headless-courses__details"
            );

            if (course.duration) {
                details.appendChild(createElement(
                    "p",
                    "cmp-headless-courses__duration",
                    "Duration: " + course.duration
                ));
            }

            details.appendChild(createElement(
                "p",
                "cmp-headless-courses__fees",
                "Fees: " + formatFees(course.fees)
            ));

            card.appendChild(details);

            var description = course.description &&
                course.description.plaintext;

            if (description) {
                card.appendChild(createElement(
                    "p",
                    "cmp-headless-courses__description",
                    description
                ));
            }

            var eligibility = course.eligibility &&
                course.eligibility.plaintext;

            if (eligibility) {
                card.appendChild(createElement(
                    "p",
                    "cmp-headless-courses__eligibility",
                    "Eligibility: " + eligibility
                ));
            }

            results.appendChild(card);
        });
    }

    function loadCourses(container) {
        if (container.dataset.initialized === "true") {
            return;
        }

        container.dataset.initialized = "true";

        var endpoint = container.dataset.endpoint;
        var status = container.querySelector(
            "[data-headless-courses-status]"
        );

        if (!endpoint) {
            status.textContent = "Course endpoint is not configured.";
            return;
        }

        fetch(endpoint, {
            method: "GET",
            headers: {
                "Accept": "application/json"
            },
            credentials: "same-origin"
        })
            .then(function (response) {
                if (!response.ok) {
                    throw new Error(
                        "GraphQL request failed: " + response.status
                    );
                }

                return response.json();
            })
            .then(function (response) {
                var courses = response &&
                    response.data &&
                    response.data.courseList &&
                    response.data.courseList.items;

                if (!Array.isArray(courses) || courses.length === 0) {
                    status.textContent = "No courses are currently available.";
                    return;
                }

                renderCourses(container, courses);
                status.textContent = "";
            })
            .catch(function (error) {
                console.error("Headless Courses error:", error);
                status.textContent =
                    "Unable to load courses. Please try again later.";
            });
    }

    function initialize() {
        var components = document.querySelectorAll(
            "[data-cmp-is='headless-courses']"
        );

        components.forEach(loadCourses);
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", initialize);
    } else {
        initialize();
    }

})(document);