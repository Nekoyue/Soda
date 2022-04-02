package moe.yue.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter

@Composable
actual fun imagePainter(resourcePath: String): Painter {
//    val imageFile = fetchFile(resourcePath)
    // TODO: Implement reading file from url
    // TODO: support Bitmap/SVG file type
//    return BitmapPainter(Image.makeFromEncoded(imageFile).toComposeImageBitmap())
    return ColorPainter(Color.White)
}