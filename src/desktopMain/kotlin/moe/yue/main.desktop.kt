package moe.yue

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() =
    singleWindowApplication(
        title = AppName,
        state = WindowState(size = DpSize(800.dp, 800.dp))
    ) {
        App()
    }

@Preview
@Composable
fun Preview() {
    App()
}
