package examples

import simple.core.alert.AlertManager
import simple.core.alert.SlackAlertHandler
import simple.core.alert.model.AlertRule
import simple.core.collector.EventCollector
import simple.core.collector.InMemoryEventStore
import simple.core.collector.config.CollectorConfig
import simple.core.trigger.ThresholdTrigger


// ./gradlew :simple-stream-collector-core:run --args='examples.ExampleCore'

fun main() {
    println("hello")
    // 1. 저장소 설정
    CollectorConfig.useStore(InMemoryEventStore())

    // 2. 알람 조건 + 핸들러 등록
    val thresholdTrigger = ThresholdTrigger(threshold = 3, duration = 5)
    val slackHandler = SlackAlertHandler(webhookUrl = "https://hooks.slack.com/services/your/webhook/url")

    val alertManager = AlertManager()
    alertManager.register(
        AlertRule(trigger = thresholdTrigger, handler = slackHandler)
    )

    // 3. 이벤트 수집 예시
    repeat(3) {
        EventCollector.record(
            key = "rpc.user.login",
            duration = 150,
            value = 1.0,
            tags = mapOf("method" to "POST", "uri" to "/login")
        )
        Thread.sleep(500)
    }

    println("📦 Example completed")
}

