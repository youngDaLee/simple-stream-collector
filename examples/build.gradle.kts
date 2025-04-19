plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "examples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io") // JitPack을 통해 라이브러리 다운로드
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("com.github.youngDaLee:simple-stream-collector:v0.1.0")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("examples.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}