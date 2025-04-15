package simple.core.collector.config

import simple.core.collector.EventCollector
import simple.core.collector.EventStore

/**
 * 외부에서 저장소 구현을 주입하는 설정
 */
object CollectorConfig {
    fun useStore(store: EventStore) {
        EventCollector.configure(store)
    }
}