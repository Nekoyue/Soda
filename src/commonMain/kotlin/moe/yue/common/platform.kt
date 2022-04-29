package moe.yue.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.skiko.ClipboardManager
import org.jetbrains.skiko.URIManager

expect fun getPlatformName(): String

fun setClipboard(content: String) {
    ClipboardManager().setText(content)
}

fun openWebpage(url: String) {
    URIManager().openUri(url)
}

@Composable
expect fun imagePainter(resourcePath: String): Painter

// suspend for JS target
expect suspend fun getDefaultFont(): FontFamily
