plugins {
    id("org.jetbrains.kotlin.js") version "1.3.70-eap-184"
}

group = "moe.yue.soda"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))

    //React, React DOM + Wrappers
    implementation("org.jetbrains:kotlin-react:16.9.0-pre.91-kotlin-1.3.61")
    implementation("org.jetbrains:kotlin-react-dom:16.9.0-pre.91-kotlin-1.3.61")
    implementation(npm("react", "16.12.0"))
    implementation(npm("react-dom", "16.12.0"))

    //Kotlin Styled
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.91-kotlin-1.3.61")
    implementation(npm("styled-components"))
    implementation(npm("inline-style-prefixer"))

    //React Router
    implementation("org.jetbrains:kotlin-react-router-dom:4.3.1-pre.91-kotlin-1.3.61")
    implementation(npm("react-router-dom"))

    //Components
    implementation(npm("react-share"))
    implementation(npm("react-markdown"))


}


kotlin.target.browser { }