package moe.yue

// This import is unresolved for current IDEA but able to compile with macosX64/macosArm64 target
import androidx.compose.ui.window.Window
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
    NSApplication.sharedApplication()
    Window(AppName) {
        App()
    }
    NSApp?.run()
}