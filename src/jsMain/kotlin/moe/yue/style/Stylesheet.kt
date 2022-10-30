package moe.yue.style

import org.jetbrains.compose.web.css.*


object Stylesheet:StyleSheet(){
    val wtCardThemeLight by style {
        border(color = rgba(39,40,44,.2))
        color(Color("#27282c"))
        backgroundColor(Color("white"))
    }
}
