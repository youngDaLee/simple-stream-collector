package simple.core.alert.model

import simple.core.alert.AlertHandler
import simple.core.trigger.Trigger

data class AlertRule (
    val trigger : Trigger,
    val handler : AlertHandler,
) {
}