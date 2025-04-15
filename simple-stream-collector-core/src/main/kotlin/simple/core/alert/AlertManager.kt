package simple.core.alert

import simple.core.alert.model.AlertRule

class AlertManager {
    private val rules = mutableListOf<AlertRule>()

    fun register(rule: AlertRule) {
        rules.add(rule)
    }

    fun onEvent(eventKey: String) {
        rules.forEach { rule ->
            if (rule.trigger.isTrigger(eventKey)) {
                rule.handler.onTriggered(eventKey)
            }
        }
    }
}