package simple.core.alert

import kotlin.test.Test
import kotlin.test.assertTrue
import simple.core.alert.model.AlertRule
import simple.core.trigger.ThresholdTrigger

class AlertRuleTest {

    @Test
    fun `AlertRule 생성 및 trigger`() {
        var triggered = false
        val trigger = ThresholdTrigger(threshold = 1, duration = 5)
        val handler = object : AlertHandler {
            override fun onTriggered(eventKey: String) {
                triggered = true
            }
        }

        val rule = AlertRule(trigger, handler)
        if (rule.trigger.isTrigger("rule.test")) {
            rule.handler.onTriggered("rule.test")
        }

        assertTrue(triggered)
    }
}
