function CollectionState({
  items = [],
  loading,
  error,
  resourceName,
  emptyMessage,
  onRetry,
}) {
  if (loading) {
    return (
      <div className="loading-state" role="status">
        Loading {resourceName} from AEM...
      </div>
    )
  }

  if (error) {
    return (
      <div className="error-state" role="alert">
        <h3>Unable to load {resourceName}</h3>
        <p>{error}</p>
        <button type="button" onClick={onRetry}>
          Try Again
        </button>
      </div>
    )
  }

  if (items.length === 0) {
    return (
      <div className="empty-state" role="status">
        {emptyMessage}
      </div>
    )
  }

  return null
}

export default CollectionState
