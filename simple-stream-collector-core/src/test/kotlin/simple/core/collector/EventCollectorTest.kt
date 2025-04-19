package simple.core.collector

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import simple.core.alert.model.AlertRule
import simple.core.alert.AlertHandler
import simple.core.trigger.ThresholdTrigger
import java.util.concurrent.atomic.AtomicBoolean

class EventCollectorTest {

    @Test
    fun `이벤트 수집 및 알랏`() {
        val triggered = AtomicBoolean(false)
        val store = InMemoryEventStore()
        val trigger = ThresholdTrigger(threshold = 2, duration = 5)
        val handler = object : AlertHandler {
            override fun onTriggered(eventKey: String) {
                triggered.set(true)
            }
        }
        val collector = EventCollector(
            store = store,
            alertRules = listOf(AlertRule(trigger, handler))
        )

        collector.record("event.test")
        collector.record("event.test")

        assertTrue(triggered.get())
        assertEquals(2, store.getEvents()["event.test"]?.size)
    }
}