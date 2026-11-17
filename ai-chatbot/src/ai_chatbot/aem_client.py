
import os

import requests
from dotenv import load_dotenv

load_dotenv()

AEM_BASE_URL = os.getenv(
    "AEM_BASE_URL",
    "http://localhost:4504",
).rstrip("/")

AEM_USERNAME = os.getenv("AEM_USERNAME")
AEM_PASSWORD = os.getenv("AEM_PASSWORD")

PERSISTED_QUERIES = {
    "courses": ("all-courses", "courseList"),
    "faculty": ("all-faculty", "facultyList"),
    "events": ("all-events", "eventList"),
    "university": (
        "university-information",
        "universityInformationList",
    ),
}


def fetch_collection(resource_name):
    """Fetch and validate one complete AEM GraphQL collection."""

    if resource_name not in PERSISTED_QUERIES:
        raise ValueError(f"Unknown AEM resource: {resource_name}")

    query_name, collection_name = PERSISTED_QUERIES[resource_name]

    url = (
        f"{AEM_BASE_URL}/graphql/execute.json/"
        f"udcp/{query_name}"
    )

    auth = None

    if AEM_USERNAME and AEM_PASSWORD:
        auth = (AEM_USERNAME, AEM_PASSWORD)

    response = requests.get(
        url,
        auth=auth,
        headers={"Accept": "application/json"},
        timeout=30,
    )

    response.raise_for_status()

    result = response.json()

    if not isinstance(result, dict):
        raise ValueError(
            f"Invalid GraphQL response for {resource_name}."
        )

    if result.get("errors"):
        raise ValueError(
            f"AEM GraphQL returned errors for {resource_name}."
        )

    data = result.get("data")

    if not isinstance(data, dict):
        raise ValueError(
            f"Missing or invalid GraphQL data for {resource_name}."
        )

    if collection_name not in data:
        raise ValueError(
            f"Expected collection '{collection_name}' "
            f"was missing from the {resource_name} response."
        )

    collection = data[collection_name]

    if not isinstance(collection, dict):
        raise ValueError(
            f"Invalid {resource_name} collection received from AEM."
        )

    if "items" not in collection:
        raise ValueError(
            f"Missing items in the {resource_name} collection."
        )

    items = collection["items"]

    if not isinstance(items, list):
        raise ValueError(
            f"Invalid items in the {resource_name} collection."
        )

    if not all(isinstance(item, dict) for item in items):
        raise ValueError(
            f"The {resource_name} collection contains invalid items."
        )

    return items


def test_aem_connection():
    """Verify that all configured AEM collections are accessible."""

    total_items = 0

    for resource_name in PERSISTED_QUERIES:
        items = fetch_collection(resource_name)

        total_items += len(items)

        print(
            f"{resource_name.title()}: "
            f"{len(items)} items retrieved"
        )

    print(f"\nTotal AEM items retrieved: {total_items}")


if __name__ == "__main__":
    test_aem_connection()
