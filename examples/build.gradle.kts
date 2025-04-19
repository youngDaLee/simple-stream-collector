plugins {
    kotlin("jvm") version "2.0.21"
}

group = "simple"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io") // JitPack을 통해 라이브러리 다운로드
}

dependencies {
    implementation("com.github.youngDaLee:simple-stream-collector:v1.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}