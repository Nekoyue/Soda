package moe.yue

import androidx.compose.ui.window.Window
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
    NSApplication.sharedApplication()
    Window(AppTitle) {
        App()
    }
    NSApp?.run()
}