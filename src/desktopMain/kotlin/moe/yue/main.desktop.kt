package moe.yue

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(800.dp, 800.dp))
    var screenLayout by remember { mutableStateOf(getScreenLayout(windowState)) }

    Window(title = AppTitle, onCloseRequest = ::exitApplication, state = windowState) {
        App(screenLayout)

        LaunchedEffect(windowState) {
            snapshotFlow { windowState.size }
                .onEach { screenLayout = getScreenLayout(windowState) }
                .launchIn(this)
        }
    }
}

fun getScreenLayout(windowState: WindowState): ScreenLayout {
    return if (windowState.size.width > 600.dp)
        ScreenLayout.Desktop
    else
        ScreenLayout.Mobile
}

@Preview
@Composable
fun MobilePreview() {
    App(ScreenLayout.Mobile)
}

@Preview
@Composable
fun DesktopPreview() {
    App(ScreenLayout.Desktop)
}