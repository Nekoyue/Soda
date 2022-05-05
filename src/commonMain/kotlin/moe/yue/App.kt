package moe.yue

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import moe.yue.common.getDefaultFont
import moe.yue.common.getPlatformName

const val AppTitle = "Soda"

enum class ScreenLayout { Desktop, Mobile }

//TODO: get rid of material components

@Composable
fun App(screenLayout: ScreenLayout) {
    val platformName = getPlatformName()

    var defaultFontFamily: FontFamily? by remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        defaultFontFamily = getDefaultFont()
    }

    if (defaultFontFamily != null)
        MaterialTheme(
            colors = LightColors,
            typography = Typography(defaultFontFamily = defaultFontFamily!!)
        ) {
            when (screenLayout) {
                ScreenLayout.Desktop -> DesktopLayout()
                ScreenLayout.Mobile -> MobileLayout()
            }
        }
}
