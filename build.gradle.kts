import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

group = "moe.yue"
version = "1.0.1"

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}


repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

// For native builds
// Compose & skiko currently only support native build on macOS
val hostOs = with(System.getProperty("os.name")) {
    when {
        this == "Mac OS X" -> "macos"
//        startsWith("Win") -> "mingw"
//        startsWith("Linux") -> "linux"
        else -> null
    }
}

var hostArch = if (hostOs != null) with(System.getProperty("os.arch")) {
    when (this) {
        "x86_64", "amd64" -> "X64"
        "aarch64" -> "Arm64"
        else -> error("Unsupported arch: $this")
    }
} else null

kotlin {
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }

    js(IR) {
        browser {}
        binaries.executable()
    }

    if (hostOs == "macos") {

        // A workaround to add IDE support for macOS target
        // https://kotlinlang.org/docs/multiplatform-mobile-ios-dependencies.html#workaround-to-enable-ide-support-for-the-shared-ios-source-set
        val macosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            when (hostArch) {
                "X64" -> ::macosX64
                "Arm64" -> ::macosArm64
                else -> error("Unsupported arch: $this")
            }

        macosTarget("macos") {}

        val targets = mutableListOf<KotlinNativeTarget>()

        val macosTargets = mutableListOf(macosX64(), macosArm64())
        targets.addAll(macosTargets)

        // TODO: build for iOS devices
        // val iosTargets = mutableListOf(iosX64(), iosArm64())
        // targets.addAll(iosTargets)

        targets.forEach {
            it.apply {
                binaries.executable {
                    entryPoint = "moe.yue.main"
                    freeCompilerArgs += listOf(
                        "-linker-option", "-framework", "-linker-option", "Metal",
                        // "-linker-option", "-framework", "-linker-option", "CoreText", // for iOS
                        // "-linker-option", "-framework", "-linker-option", "CoreGraphics" // for iOS
                    )
                    // // the current compose binary surprises LLVM, so disable checks for now.
                    freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
                }
            }
        }


    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("org.jetbrains.skiko:skiko:${extra["skiko.version"] as String}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        val jsMain by getting {
//            // for DOM runtime setup
//            // currently using Skiko wasm runtime
//            dependencies {
//                implementation(compose.web.core)
//            }
        }
        if (hostOs == "macos") {
            val macosMain by getting { dependsOn(commonMain) }
            val macosX64Main by getting { dependsOn(macosMain) }
            val macosArm64Main by getting { dependsOn(macosMain) }

//            // TODO: build for iOS devices
//            val iosMain by creating { dependsOn(commonMain) }
//            val iosX64Main by getting { dependsOn(iosMain) }
//            val iosArm64Main by getting { dependsOn(iosMain) }

        }
    }
}

val distributionName = rootProject.name
val distributionVersion = rootProject.version as String

// JVM-bundled desktop build
compose.desktop {
    application {
        mainClass = "moe.yue.Main_desktopKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = distributionName
            packageVersion = distributionVersion

            windows {
                menuGroup = distributionName
                upgradeUuid = "c4d4404f-2eb7-48b7-87df-490d427bb124"
            }
        }
    }
}


compose.experimental {
    web.application {}
}

if (hostOs != null) {
    compose.desktop.nativeApplication {
        targets(kotlin.targets.getByName("$hostOs$hostArch"))
        distributions {
            targetFormats(TargetFormat.Dmg)
            packageName = distributionName
            packageVersion = distributionVersion
        }
    }
}

//////

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
