package moe.yue.data


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter

@Composable
actual fun imagePainter(resourcePath: String): Painter {
    // TODO: Implement reading file from resource folder
    // TODO: support Bitmap/SVG file type
    return ColorPainter(Color.White)
}