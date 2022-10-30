package moe.yue


import androidx.compose.runtime.*


const val AppTitle = "Soda"

enum class ScreenLayout { Desktop, Mobile }

@Composable
fun App(screenLayout: ScreenLayout) {
    MaterialTheme(
        colors = LightColors,
        typography = Typography(defaultFontFamily = defaultFontFamily!!)
    )

    when (screenLayout) {
        ScreenLayout.Desktop -> DesktopLayout()
        ScreenLayout.Mobile -> MobileLayout()
    }

}
