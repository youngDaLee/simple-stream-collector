package simple.core.trigger

import simple.core.collector.model.EventMetric
import simple.core.trigger.model.TriggerType

interface Trigger {
    val type: TriggerType   // 트리거 타입(실시간, 저장된 이벤트 기준)
    fun isTrigger(event: EventMetric, storedEvents: List<EventMetric> = emptyList()): Boolean
}