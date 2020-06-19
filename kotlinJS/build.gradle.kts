plugins {
    id("org.jetbrains.kotlin.js") version "1.4-M2"
}

group = "moe.yue.soda"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

kotlin {
    js {
            browser {}
//        produceExecutable()
        }
}

task("buildApp").dependsOn("build").doLast {
            delete{
                delete("../assets/js/${rootProject.name}-dev.js")
            }
            copy {
                from("build/distributions/${rootProject.name}.js")
                into("../assets/js/")
            }
        }

task("devBuildApp").dependsOn("browserDevelopmentWebpack").doLast {
    delete{
        delete("../assets/js/${rootProject.name}.js")
    }
    copy {
        from("build/distributions/${rootProject.name}.js")
        into("../assets/js/")
        rename("${rootProject.name}.js", "${rootProject.name}-dev.js")
    }
}
