export const AEM_ENDPOINTS = {
  courses: '/graphql/execute.json/udcp/all-courses',
  faculty: '/graphql/execute.json/udcp/all-faculty',
  events: '/graphql/execute.json/udcp/all-events',
}

export async function fetchAemCollection(
  endpoint,
  collectionName,
  resourceName,
  signal
) {
  const response = await fetch(endpoint, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
    },
    credentials: 'same-origin',
    signal,
  })

  if (!response.ok) {
    throw new Error(
      `Unable to retrieve ${resourceName}. Server returned ${response.status}.`
    )
  }

  const result = await response.json()
  const data = result?.data

  if (!data || typeof data !== 'object' || Array.isArray(data)) {
    throw new Error(`Invalid ${resourceName} data received from AEM.`)
  }

  const collection = data[collectionName]

  if (collection === null || collection === undefined) {
    return []
  }

  if (typeof collection !== 'object' || Array.isArray(collection)) {
    throw new Error(`Invalid ${resourceName} data received from AEM.`)
  }

  const collectionItems = collection.items

  if (collectionItems === null || collectionItems === undefined) {
    return []
  }

  if (!Array.isArray(collectionItems)) {
    throw new Error(`Invalid ${resourceName} data received from AEM.`)
  }

  return collectionItems.filter(
    (item) => item !== null && typeof item === 'object' && !Array.isArray(item)
  )
}
