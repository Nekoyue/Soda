package moe.yue.common

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.promise
import moe.yue.fontMapping
import moe.yue.fontName
import org.khronos.webgl.Int8Array
import org.jetbrains.skia.Image as SkImage

actual fun getPlatformName(): String {
    return "JavaScript"
}

/**Skiko implementation (without response.ok check) can be found at [org.jetbrains.skiko.loadBytesFromPath]*/
suspend fun fetchFile(url: String): ByteArray? {
    val response = window.fetch(url).await()
    return if (response.ok)
        Int8Array(response.arrayBuffer().await()).unsafeCast<ByteArray>()
    else null
}


@Composable
actual fun imagePainter(resourcePath: String): Painter {
    var imageFile: ByteArray? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        imageFile = fetchFile(resourcePath) ?: byteArrayOf()
    }

    // TODO: support SVG file type
    return if (imageFile == null || imageFile!!.isEmpty()) ColorPainter(Color.Transparent)
    else BitmapPainter(SkImage.makeFromEncoded(imageFile!!).toComposeImageBitmap())
}

actual suspend fun getDefaultFont(): FontFamily {
    val fontsJob = MainScope().promise {
        fontMapping.map {
            val fontFile = fetchFile(it.resource)!!
            Font(fontName, fontFile, it.weight, it.style)
        }
    }

    return FontFamily(fontsJob.await())
}
