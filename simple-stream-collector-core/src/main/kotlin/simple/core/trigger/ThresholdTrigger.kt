package simple.core.trigger

import simple.core.collector.model.EventMetric
import simple.core.trigger.model.TriggerType

/**
 * 저장된 이벤트 개수에 따라 트리거 발동
 */
class ThresholdTrigger (
    private val threshold: Int,
    override val type: TriggerType,
) : Trigger {
    override fun isTrigger(event: EventMetric, storedEvents: List<EventMetric>): Boolean {
        return when (type) {
            TriggerType.REALTIME -> true
            TriggerType.STORED -> storedEvents.size >= threshold
        }
    }
}