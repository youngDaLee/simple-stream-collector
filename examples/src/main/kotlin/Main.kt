package examples

import simple.core.alert.AlertHandler
import simple.core.alert.model.AlertRule
import simple.core.collector.EventCollector
import simple.core.collector.InMemoryEventStore
import simple.core.trigger.ThresholdTrigger
import simple.core.trigger.Trigger
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("🟢 예제 실행 시작")

    // 사용자 정의 콘솔 알람 핸들러
    val consoleHandler = object : AlertHandler {
        override fun onTriggered(eventKey: String) {
            println("🚨 [콘솔 알림] '$eventKey' 이벤트가 임계치를 초과했습니다!")
        }
    }

    val trigger = ThresholdTrigger(threshold = 3, duration = 5)
    val alertRule = AlertRule(trigger, consoleHandler)

    // EventCollector 생성
    val store = InMemoryEventStore()
    val collector = EventCollector(
        store = store,
        alertRules = listOf(alertRule)
    )

    // 이벤트 수집 예시
    repeat(3) {
        collector.record("login.success", duration = 123)
        Thread.sleep(1000)
    }

    println("✅ 예제 완료")
}
