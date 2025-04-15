package simple.core.trigger

interface Trigger {
    fun isTrigger(eventKey: String): Boolean
}