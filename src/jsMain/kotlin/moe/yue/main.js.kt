package moe.yue

import androidx.compose.ui.window.Window
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window(AppName) {
            App()
        }
    }
}
