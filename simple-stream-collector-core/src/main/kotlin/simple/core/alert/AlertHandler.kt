package simple.core.alert

interface AlertHandler {
    fun onTriggered(eventKey : String)
}