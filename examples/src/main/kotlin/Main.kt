package simple

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println("Hello, " + name + "!")

    for (i in 1..5) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        println("i = $i")
    }
}

/**


// ./gradlew :simple-stream-collector-core:run --args='examples.ExampleCore'

fun main() {
println("🟢 예제 실행 시작")

val store = InMemoryEventStore()

// 사용자 정의 콘솔 알람 핸들러
val consoleHandler = object : AlertHandler {
override fun onTriggered(eventKey: String) {
println("🚨 [콘솔 알림] '$eventKey' 이벤트가 임계치를 초과했습니다!")
}
}

val trigger = ThresholdTrigger(threshold = 3, duration = 5)
val alertRule = AlertRule(trigger, consoleHandler)

// EventCollector 생성
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
 */