
import json

from aem_client import fetch_collection


for resource_name in ("courses", "faculty", "events"):
    print(f"\n{'=' * 60}")
    print(f"{resource_name.upper()} — SAMPLE CONTENT")
    print("=" * 60)

    items = fetch_collection(resource_name)

    if not items:
        print("No content available.")
        continue

    print(json.dumps(items[0], indent=2, ensure_ascii=False))
