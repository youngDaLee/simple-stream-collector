package simple.core.collector

import simple.core.model.EventMetric

/**
 * 이벤트 수집의 진입점
 */
object EventCollector {

    private var store: EventStore = InMemoryEventStore()

    fun configure(customStore: EventStore) {
        store = customStore
    }

    /**
     * 이벤트를 수집합니다. duration(ms)은 선택입니다.
     */
    fun record(
        key: String,
        duration: Long? = null,
        value: Double? = null,
        tags: Map<String, String> = emptyMap()
    ) {
        store.save(
            EventMetric(
                key = key,
                duration = duration,
                value = value,
                tags = tags
            )
        )
    }
}