import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

group = "moe.yue"
version = "1.0.0"

buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}


repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
}

// For native builds
val hostOs = with(System.getProperty("os.name")) {
    when {
        this == "Mac OS X" -> "macos"
        startsWith("Win") -> "windows"
        startsWith("Linux") -> "linux"
        else -> error("Unsupported OS: $this")
    }
}

var hostArch = with(System.getProperty("os.arch")) {
    when (this) {
        "x86_64", "amd64" -> "X64"
        "aarch64" -> "Arm64"
        else -> error("Unsupported arch: $this")
    }
}

kotlin {
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }

    js(IR) {
        browser()
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
                        "-linker-option", "-framework", "-linker-option", "CoreText",
                        "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                    )
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


        val macosMain by getting { dependsOn(commonMain) }

        val macosX64Main by getting { dependsOn(macosMain) }

        val macosArm64Main by getting { dependsOn(macosMain) }


// TODO: build for iOS devices
//
//        val iosMain by creating { dependsOn(commonMain) }
//        val iosX64Main by getting { dependsOn(iosMain) }
//        val iosArm64Main by getting { dependsOn(iosMain) }
    }
}

val distributionName = rootProject.name
val distributionVersion = rootProject.version as String

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

compose.desktop.nativeApplication {
    targets(kotlin.targets.getByName("$hostOs$hostArch"))
    distributions {
        targetFormats(TargetFormat.Dmg)
        packageName = distributionName
        packageVersion = distributionVersion
    }
}

//////

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

kotlin {
    targets.withType<KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
}

//// a temporary workaround for a bug in jsRun invocation - see https://youtrack.jetbrains.com/issue/KT-48273
//afterEvaluate {
//    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
//        versions.webpackDevServer.version = "4.0.0"
//        versions.webpackCli.version = "4.9.0"
//        nodeVersion = "16.0.0"
//    }
//}


// TODO: remove when https://youtrack.jetbrains.com/issue/KT-50778 fixed
project.tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile::class.java).configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xir-dce-runtime-diagnostic=log"
    )
}
