package moe.yue

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


val LightColors = lightColors(
    primary = Color(0xffe3f2fd),
    primaryVariant = Color(0xff7fcdf0),
    onPrimary = Color(0xfffafafa),
    secondary = Color(0xfffce4ec),
    secondaryVariant = Color(0xfff76085),
    onSecondary = Color(0xfffafafa),
    background = Color(0xff87daff),
    surface = Color(0xffeaf3fa),
    onSurface = Color(0xff2b2b2b)
)

// Resources mapping for font files
data class FontMapping(val resource: String, val weight: FontWeight, val style: FontStyle)

const val fontName = "Nunito"
val fontMapping = listOf(
    FontMapping("font/Nunito-Regular.ttf", FontWeight.Normal, FontStyle.Normal),
    FontMapping("font/Nunito-Medium.ttf", FontWeight.Medium, FontStyle.Normal),
)