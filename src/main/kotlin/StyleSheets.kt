import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.TextDecorationLine
import kotlinx.css.properties.textDecoration
import styled.StyleSheet

// This file stores all the style sheets.

val GlobalStyles = CSSBuilder().apply {
    body {
        margin(0.px)
        padding(0.px)
    }
}.toString()

object ContainerStyles : StyleSheet("Container", isStatic = true) {
    val container by css {
        boxSizing = BoxSizing.inherit
        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.spaceBetween
        minHeight = 100.vh
        +defaultFont
    }

    val defaultFont by css {
        fontFamily = "Lato, Arial, sans-serif"
    }

    val altFont by css {
        fontFamily = "Montserrat, Arial, sans-serif"
    }

    val codeFont by css {
        fontFamily = "Menlo, Consolas, monospace"
    }
}

object HeaderStyles : StyleSheet("Header", isStatic = true) {
    val header by css {
//        position = Position.fixed // Uncomment to keep header floating at the top.
        padding(left = 15.pct, right = 15.pct)
        display = Display.flex
        backgroundColor = Color.white
        height = 60.px
        fontSize = 1.6.rem
        alignItems = Align.center
        textDecoration = TextDecoration.none
    }
    val title by css {
        marginLeft = 30.px
    }

    val navigation by css {
        marginLeft = LinearDimension.auto
        display = Display.flex
        flexDirection = FlexDirection.row
    }

    val navigationTab by css {
        padding(left = 20.px, right = 20.px)
    }

    val navigationTabSelected by css {
        textDecoration(lines = *arrayOf(TextDecorationLine.underline))
    }
}

object FooterStyles : StyleSheet("Footer", isStatic = true) {
    val footer by css {
        alignItems = Align.center
        textAlign = TextAlign.center
        marginBottom = 1.rem
    }
}

object ContentStyles : StyleSheet("Content", isStatic = true) {
    val content by css {
        display = Display.flex
        flexDirection = FlexDirection.column
        flex(flexGrow = 1.0, flexShrink = 1.0, flexBasis = LinearDimension.auto)
        alignItems = Align.center
        justifyContent = JustifyContent.center
        padding(all = 20.px)
    }
}


object PostStyles : StyleSheet("Post", isStatic = true) {
    val post by css {
        margin(top = 20.px, bottom = 40.px)
        width = 100.pct
        maxWidth = 1000.px
    }

    val metadata by css {

    }

    val article by css {
        margin(top = 0.px, right = LinearDimension.auto, bottom = 0.px, left = LinearDimension.auto)
    }
}