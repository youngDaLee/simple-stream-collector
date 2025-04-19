package simple.core.collector

import simple.core.collector.model.EventMetric
import java.util.concurrent.ConcurrentHashMap

/**
 * 수집되는 이벤트를 JVM 힙 메모리에 저장하는 클래스
 *
 * @property events 수집되는 이벤트를 저장하는 맵
 */
class InMemoryEventStore : EventStore {
    private val events: MutableMap<String, MutableList<EventMetric>> = ConcurrentHashMap()

    /**
     * 수집되는 이벤트를 저장하는 메서드
     *
     * @param event 수집되는 이벤트
     */
    override fun save(event: EventMetric) {
        events.computeIfAbsent(event.key) { mutableListOf() }.add(event)
    }

    /**
     * 수집된 이벤트를 반환하는 메서드
     *
     * @return 수집된 이벤트
     */
    fun getEvents(): Map<String, List<EventMetric>> {
        return events.toMap()
    }
}