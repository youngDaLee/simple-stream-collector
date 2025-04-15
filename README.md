# simple-stream-collector
코틀린으로 제작한 실시간 이벤트 스트림 콜렉터 유틸리티 입니다.
채팅, 페이지 뷰, 아이템 획득 등 실행 도중 발생하는 다양한 이벤트를 kafka나 redis 없이 가볍고 빠르게 수집할 수 있도록 도와주는 경량 라이브러리 입니다.

## 개요
- 실시간으로 들어오는 이벤트 스트림(채팅, 페이지 뷰 등)을 일정 시간 단위로 통계적으로 집계
- 프로그램 실행 도중 발생하는 다양한 이벤트(채팅, 아이템 획득 등)를 부하 없이 가볍게 수집
- Kafka, Redis 등 복잡한 외부 의존성 없이도 통계/알림 제공 가능
- 향후 관리자 대시보드, 부정 행위 탐지 시스템과 연동될 수 있도록 확장 가능

## 주요 기능
- 이벤트 수신
- 지정 시간 간격 집계
- 슬라이딩 윈도우 평균치 계산
- 이벤트 호출 속도 분석
- 집계 수치 초과 시 알람

## 사용 방법
```kotlin
// 1. 저장소 설정 (InMemoryEventStore는 기본 제공)
CollectorConfig.useStore(InMemoryEventStore())

// 2. 알람 조건 + 처리 조합 등록
// 필요 시 Trigger, AlertHandler 구현하여 사용 가능
AlertmManager.register(
    AlertRule(
        trigger = ThresholdTrigger(threshold = 100, duration = 10), // 10초간 100회 이상
        handler = SlackAlertmHandler(webhookUrl = "https://hooks.slack.com/...")
    )
)

// 3. 이벤트 수집
EventCollector.record(
    key = "rpc.user.login",
    duration = 180,
    tags = mapOf("uri" to "/login", "method" to "POST")
)
```

### 기본 수집
```kotlin
EventCollector.record("rpc.user.login", duration = 145)
EventCollector.record("api.page.view")
```

### 수치 포함 수집 (속도, 점수 등)
```kotlin
EventCollector.record(
    key = "rpc.user.login",
    duration = 145,
    score = 3.0
)
```

### 부가 태그 포함 수집
```kotlin
EventCollector.record(
    key = "rpc.user.register",
    duration = 250,
    tags = mapOf("method" to "POST", "uri" to "/register")
)
```

## 📄 License
This project is licensed under the MIT License – see the [LICENSE](./LICENSE) file for details.



