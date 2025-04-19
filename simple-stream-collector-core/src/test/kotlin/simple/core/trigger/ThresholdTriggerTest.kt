package simple.core.trigger

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import simple.core.collector.model.EventMetric
import simple.core.trigger.model.TriggerType

class ThresholdTriggerTest {

    @Test
    fun `실시간 모드에서는 단일 이벤트로 트리거 발동`() {
        val trigger = ThresholdTrigger(
            type = TriggerType.REALTIME,
            threshold = 5
        )

        val result = trigger.isTrigger(
            event = EventMetric(key = "realtime.test", value = 5.0),
            storedEvents = emptyList()
        )

        assertTrue(result)
    }

    @Test
    fun `저장소 기반 모드에서는 누적 이벤트 기준 트리거 발동`() {
        val trigger = ThresholdTrigger(
            type = TriggerType.STORED,
            threshold = 2
        )

        val events = listOf(
            EventMetric(key = "stored.test", value = 3.0),
            EventMetric(key = "stored.test", value = 4.0)
        )

        val result = trigger.isTrigger(
            event = events.last(),
            storedEvents = events
        )

        assertTrue(result)
    }

    @Test
    fun `저장소 기반 모드에서 조건 미달이면 트리거 되지 않음`() {
        val trigger = ThresholdTrigger(
            type = TriggerType.STORED,
            threshold = 3
        )

        val events = listOf(
            EventMetric(key = "stored.test", value = 1.0),
            EventMetric(key = "stored.test", value = 2.0)
        )

        val result = trigger.isTrigger(
            event = events.last(),
            storedEvents = events
        )

        assertFalse(result)
    }
}