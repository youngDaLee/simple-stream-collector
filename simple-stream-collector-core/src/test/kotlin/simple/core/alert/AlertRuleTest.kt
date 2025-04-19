package simple.core.alert

import kotlin.test.Test
import kotlin.test.assertTrue
import simple.core.alert.model.AlertRule
import simple.core.collector.model.EventMetric
import simple.core.trigger.ThresholdTrigger
import simple.core.trigger.model.TriggerType

class AlertRuleTest {

    @Test
    fun `AlertRule 생성 및 trigger`() {
        var triggered = false
        val trigger = ThresholdTrigger(threshold = 1, type = TriggerType.REALTIME)
        val handler = object : AlertHandler {
            override fun onTriggered(eventKey: String) {
                triggered = true
            }
        }

        val rule = AlertRule(trigger, handler)
        val testEvent = EventMetric(key = "rule.test", value = 1.0)

        if (rule.trigger.isTrigger(testEvent, emptyList())) {
            rule.handler.onTriggered("rule.test")
        }

        assertTrue(triggered)
    }
}
