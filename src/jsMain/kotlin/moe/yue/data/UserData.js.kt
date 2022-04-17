package moe.yue.data

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.launch
import moe.yue.common.fetchFile
import org.jetbrains.skia.Image

@Composable
actual fun imagePainter(resourcePath: String): Painter {
    val coroutineScope = rememberCoroutineScope()
    var imageFile: ByteArray? by remember { mutableStateOf(null) }

    coroutineScope.launch {
        if (imageFile == null)
            imageFile = fetchFile(resourcePath) ?: byteArrayOf()
    }
    // TODO: support Bitmap/SVG file type
    return if (imageFile == null || imageFile!!.isEmpty()) ColorPainter(Color.Transparent)
    else BitmapPainter(Image.makeFromEncoded(imageFile).toComposeImageBitmap())
}