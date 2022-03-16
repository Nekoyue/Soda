import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

group = "moe.yue"
version = "1.0.0"

buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
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

    macosX64("macos") {
        binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal"
                )
            }
        }
    }
//    macosArm64 {
//        binaries {
//            executable {
//                entryPoint = "main"
//                freeCompilerArgs += listOf(
//                    "-linker-option", "-framework", "-linker-option", "Metal"
//                )
//            }
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
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
        }

        val macosMain by getting {
        }

//        val nativeMain by creating {
//            dependsOn(commonMain)
//        }
//        val macosMain by creating {
//            dependsOn(nativeMain)
//        }
//        val macosX64Main by getting {
//            dependsOn(macosMain)
//        }
//        val macosArm64Main by getting {
//            dependsOn(macosMain)
//        }
    }
}

val distributionName = rootProject.name
val distributionVersion = rootProject.version as String

compose.desktop {
    application {
        mainClass = "Main_desktopKt"
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
    targets(kotlin.targets.getByName("macos"))
//    targets(kotlin.targets.getByName("macosX64"))
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
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
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
