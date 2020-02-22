import kotlinx.css.*
import styled.StyleSheet

// This file contains all the style sheets.

object RootStyles : StyleSheet("Root", isStatic = true) {
    val root by css {
        boxSizing = BoxSizing.inherit
        fontFamily = "Lato, Arial, sans-serif"
    }

    val titleFont by css {
        fontFamily = "Montserrat, Arial, sans-serif"
    }

    val codeFont by css {
        fontFamily = "Menlo, Consolas, monospace"
    }
}

object PostStyles : StyleSheet("Post", isStatic = true) {
    val root by css {
        display = Display.flex
        margin(top = 1.6.rem, bottom = 3.2.rem)
    }

    val metadata by css {

    }

    val article by css {
        margin(top = 0.px, bottom = 0.px, right = LinearDimension.auto, left = LinearDimension.auto)
        maxWidth = 90.rem
        width = 100.pct
        padding(left = 2.rem, right = 2.rem)

    }
}