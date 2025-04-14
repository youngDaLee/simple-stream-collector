package simple.core.collector

import simple.core.model.EventMetric

/**
 * 수집되는 이벤트를 저장하는 인터페이스
 */
fun interface EventStore {
    /**
     * 수집되는 이벤트를 저장하는 메서드
     *
     * @param event 수집되는 이벤트
     */
    fun save(event: EventMetric)
}