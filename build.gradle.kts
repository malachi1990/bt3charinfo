import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.org.fusesource.jansi.AnsiRenderer.test

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("java")
}

group = "com.dbzl"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}


kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation( "org.junit.jupiter:junit-jupiter:5.9.3")
                implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.1")
            }
        }
        val jvmTest by getting {
            dependencies {

                implementation(kotlin("test"))
            }
        }

    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED, org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED)
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "bt3charinfo"
            packageVersion = "1.0.0"
        }
    }
}

