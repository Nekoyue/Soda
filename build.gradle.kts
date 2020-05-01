plugins {
    id("org.jetbrains.kotlin.js") version "1.3.72"
}

group = "moe.yue.soda"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlin-dev")
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))

    //React, React DOM + Wrappers
    implementation("org.jetbrains:kotlin-react:16.13.1-pre.103-kotlin-1.3.72")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.103-kotlin-1.3.72")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))

    //Kotlin Styled
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.103-kotlin-1.3.72")
    implementation(npm("styled-components"))
    implementation(npm("inline-style-prefixer"))

    //React Router
    implementation("org.jetbrains:kotlin-react-router-dom:5.1.2-pre.103-kotlin-1.3.72")
    implementation(npm("react-router-dom"))

    //Components
    implementation(npm("react-share"))
    implementation(npm("react-markdown"))
    implementation(npm("prismjs")) // An alternative will be react-syntax-highlighter.

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
}


kotlin.target.browser { }