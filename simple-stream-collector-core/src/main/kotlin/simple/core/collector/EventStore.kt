package simple.core.collector

import simple.core.collector.model.EventMetric
import simple.core.collector.model.RetentionPolicy

/**
 * 수집되는 이벤트를 저장하는 인터페이스
 */
interface EventStore {
    val retentionPolicy: RetentionPolicy

    /**
     * 수집되는 이벤트를 저장하는 메서드
     *
     * @param event 수집되는 이벤트
     */
    fun save(event: EventMetric) {
        internalSave(event)
        cleanup()
    }

    /**
     * 실제 저장 로직 (구현체에 위임)
     */
    fun internalSave(event: EventMetric)

    /**
     * 저장 주기에 따라 이벤트를 삭제하는 메서드
     */
    fun cleanup()

    /**
     * 저장된 이벤트를 반환하는 메서드
     *
     * @return 수집된 이벤트
     */
    fun getEvents(): Map<String, List<EventMetric>> {
        return emptyMap()
    }
}