package simple.core.trigger

import java.util.concurrent.ConcurrentHashMap

class ThresholdTrigger (
    private val threshold: Int,
    private val duration: Long,
) : Trigger {
    private var buffer = ConcurrentHashMap<String, MutableList<Long>>()

    override fun isTrigger(eventKey: String): Boolean {
        val currentTime = System.currentTimeMillis()
        val windowStartTime = currentTime - duration * 1000
        val events = buffer.computeIfAbsent(eventKey) { mutableListOf() }

        synchronized(buffer) {
            events.add(currentTime)
            events.removeIf { it < windowStartTime }
            return events.size >= threshold
        }
    }
}