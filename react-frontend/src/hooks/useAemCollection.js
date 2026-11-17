import { useEffect, useState } from 'react'
import { fetchAemCollection } from '../services/aemGraphql'

export function useAemCollection(endpoint, collectionName, resourceName) {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [attempt, setAttempt] = useState(0)

  useEffect(() => {
    const controller = new AbortController()

    async function fetchCollection() {
      try {
        setLoading(true)
        setError('')
        const collectionItems = await fetchAemCollection(
          endpoint,
          collectionName,
          resourceName,
          controller.signal
        )
        setItems(collectionItems)
      } catch (requestError) {
        if (
          requestError instanceof Error &&
          requestError.name === 'AbortError'
        ) {
          return
        }

        console.error(`AEM ${resourceName} GraphQL error:`, requestError)
        setError(
          requestError instanceof Error
            ? requestError.message
            : `Unable to load ${resourceName}.`
        )
      } finally {
        if (!controller.signal.aborted) {
          setLoading(false)
        }
      }
    }

    fetchCollection()

    return () => controller.abort()
  }, [attempt, collectionName, endpoint, resourceName])

  return {
    items,
    loading,
    error,
    retry: () => setAttempt((currentAttempt) => currentAttempt + 1),
  }
}
