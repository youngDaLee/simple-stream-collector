package simple.core.collector.model

data class RetentionPolicy(
    val durationMillis: Long
) {
    companion object {
        fun ofMinutes(min: Long) = RetentionPolicy(min * 60 * 1000)
        fun ofHours(hour: Long) = RetentionPolicy(hour * 60 * 60 * 1000)
        fun ofSeconds(sec: Long) = RetentionPolicy(sec * 1000)
    }
}