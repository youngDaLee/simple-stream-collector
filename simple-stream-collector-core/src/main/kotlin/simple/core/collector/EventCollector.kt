package simple.core.collector

import simple.core.alert.model.AlertRule
import simple.core.collector.model.EventMetric

/**
 * 이벤트 수집의 진입점
 */
class EventCollector (
    private val store: EventStore = InMemoryEventStore(),
    private val alertRules: List<AlertRule> = emptyList()
) {

    /**
     * 이벤트를 수집합니다. duration(ms)은 선택입니다.
     */
    fun record(
        key: String,
        duration: Long? = null,
        value: Double? = null,
        tags: Map<String, String> = emptyMap()
    ) {
        val metric = EventMetric(
            key = key,
            duration = duration,
            value = value,
            tags = tags
        )

        // 저장소 저장
        store.save(metric)

        // 알림 조건 확인
        alertRules.forEach { rule ->
            if (rule.trigger.isTrigger(key)) {
                rule.handler.onTriggered(key)
            }
        }
    }
}