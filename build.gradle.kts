
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

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
                val lifecycle_version = "2.6.1"
                val arch_version = "2.2.0"

                implementation(compose.desktop.currentOs)
                implementation( "org.junit.jupiter:junit-jupiter:5.9.3")
                implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
                // ViewModel utilities for Compose
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
                // LiveData
                implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
                // Lifecycles only (without ViewModel or LiveData)
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
                // Lifecycle utilities for Compose
                implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

                // Saved state module for ViewModel
                implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")



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
dependencies {
    implementation("com.github.skydoves:viewmodel-lifecycle:1.1.0")
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

