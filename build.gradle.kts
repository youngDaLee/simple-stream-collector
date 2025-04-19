plugins {
    kotlin("jvm") version "2.0.21" apply false
}

allprojects {
    group = "com.github.youngDaLee"
    version = "0.1.0"

    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
