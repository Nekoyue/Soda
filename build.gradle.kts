group = "moe.yue"
version = "2.0.0"

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}


repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}


kotlin {
    js(IR) {
        browser {}
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.web.core)
                implementation(compose.web.svg)

            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
                implementation(compose.web.testUtils)
            }
        }
    }
}
