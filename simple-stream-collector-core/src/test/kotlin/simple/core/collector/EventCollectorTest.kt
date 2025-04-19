package simple.core.collector

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import simple.core.alert.model.AlertRule
import simple.core.alert.AlertHandler
import simple.core.collector.model.RetentionPolicy
import simple.core.trigger.ThresholdTrigger
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.assertNotNull

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

    @Test
    fun `10초 후 이벤트 자동 삭제 확인`() = runBlocking {
        val store = InMemoryEventStore(RetentionPolicy.ofSeconds(10))
        val collector = EventCollector(store = store)

        collector.record("event.ttl")
        assertEquals(1, store.getEvents()["event.ttl"]?.size)

        delay(10_500)
        collector.record("event.ttl") // trigger cleanup indirectly

        val events = store.getEvents()["event.ttl"]
        assertNotNull(events)
        assertEquals(1, events.size) // old one should be cleaned up
    }
}