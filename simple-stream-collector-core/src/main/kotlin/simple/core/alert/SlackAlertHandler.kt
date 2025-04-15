package simple.core.alert

class SlackAlertHandler (
    private val webhookUrl: String,
    private val messageBuilder: (String) -> String = { "🚨 Alarm triggered: $it" }
) : AlertHandler {
    override fun onTriggered(eventKey: String) {
        val message = messageBuilder(eventKey)
        println("[Slack] Would send to $webhookUrl: $message")
        // TODO: 실제 HTTP POST 구현
    }
}