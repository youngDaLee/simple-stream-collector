plugins {
    kotlin("jvm")
    jacoco
}

jacoco {
    toolVersion = "0.8.10"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        html.required.set(true)
        xml.required.set(false)
        csv.required.set(false)
    }

    classDirectories.setFrom(
        fileTree("${buildDir}/classes/kotlin/main") {
            include(
                "**",
            )
        }
    )

    sourceDirectories.setFrom(
        files("src/main/kotlin")
    )

    executionData.setFrom(
        fileTree(buildDir) {
            include("jacoco/test.exec")
        }
    )
}