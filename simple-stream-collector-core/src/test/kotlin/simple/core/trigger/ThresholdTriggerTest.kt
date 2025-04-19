package simple.core.trigger

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class ThresholdTriggerTest {

    @Test
    fun `threshold 도달 후 trigger 여부 판단`() {
        val trigger = ThresholdTrigger(threshold = 2, duration = 5)

        assertFalse(trigger.isTrigger("trigger.test"))
        assertTrue(trigger.isTrigger("trigger.test"))
    }

    @Test
    fun `기간 지난 후 threshold 리셋`() = runBlocking {
        val trigger = ThresholdTrigger(threshold = 2, duration = 1)

        assertFalse(trigger.isTrigger("window.test"))
        delay(1200)
        assertFalse(trigger.isTrigger("window.test"))
        assertTrue(trigger.isTrigger("window.test"))
    }
}