package examples

import kotlinx.coroutines.*
import simple.core.alert.AlertHandler
import simple.core.alert.model.AlertRule
import simple.core.collector.EventCollector
import simple.core.collector.InMemoryEventStore
import simple.core.trigger.ThresholdTrigger
import simple.core.trigger.model.TriggerType
import simple.core.collector.model.RetentionPolicy
import java.util.Date

fun main() = runBlocking {
    saveExample()
    loginExample()
}

// 콘솔 알림 핸들러 정의
val ConsoleHandler = object : AlertHandler {
    override fun onTriggered(eventKey: String) {
        println("🚨 [콘솔 알림] '$eventKey' 이벤트가 발생했습니다")
    }
}

suspend fun saveExample() = coroutineScope {
    println("🟢 예제 시작: saveExample")

    val store = InMemoryEventStore(RetentionPolicy.ofSeconds(3))
    val trigger = ThresholdTrigger(threshold = 3, type = TriggerType.STORED)
    val alertRule = AlertRule(trigger, ConsoleHandler)

    val collector = EventCollector(
        store = store,
        alertRules = listOf(alertRule)
    )

    launch {
        repeat(10) {
            collector.record("event.aggregate", value = (1..5).random().toDouble())
            delay(500)
        }
    }

    repeat(2) {
        delay(3000)
        val recentEvents = store.getEvents()["event.aggregate"] ?: emptyList()
        val count = recentEvents.size
        val average = recentEvents.mapNotNull { it.value }.average()

        println("📊 [${Date()}] 최근 3초 간 이벤트 수: $count, 평균 값: ${"%.2f".format(average)}")
    }

    println("✅ 예제 종료: saveExample")
}

fun loginExample() {
    println("🟢 예제 시작: loginExample")

    val store = InMemoryEventStore()
    val trigger = ThresholdTrigger(threshold = 1, type = TriggerType.REALTIME)
    val alertRule = AlertRule(trigger, ConsoleHandler)

    val collector = EventCollector(
        store = store,
        alertRules = listOf(alertRule)
    )

    repeat(3) {
        collector.record("login.success")
        Thread.sleep(1000)
    }

    println("✅ 예제 종료: loginExample")
}