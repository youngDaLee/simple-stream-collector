package simple.core.collector

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import simple.core.collector.model.EventMetric

class InMemoryEventStoreTest {

    @Test
    fun `저장 및 이벤트 반환`() {
        val store = InMemoryEventStore()
        val metric = EventMetric(key = "store.test", duration = 100, value = 1.0)

        store.save(metric)

        val result = store.getEvents()["store.test"]
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(metric, result.first())
    }
}
