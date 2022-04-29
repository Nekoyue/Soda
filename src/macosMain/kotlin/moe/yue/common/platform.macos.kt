package moe.yue.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily

actual fun getPlatformName(): String {
    return "macOS"
}

@Composable
actual fun imagePainter(resourcePath: String): Painter {
    // TODO: Implement reading file from resource folder
    // TODO: support Bitmap/SVG file type
    return ColorPainter(Color.White)
}

actual suspend fun getDefaultFont(): FontFamily {
    TODO("Not yet implemented")
}