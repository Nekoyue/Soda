package moe.yue.common

import org.jetbrains.skiko.ClipboardManager
import org.jetbrains.skiko.URIManager

expect fun getPlatformName(): String

fun setClipboard(content: String) {
    ClipboardManager().setText(content)
}

fun openWebpage(url: String) {
    URIManager().openUri(url)
}