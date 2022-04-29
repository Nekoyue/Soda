package moe.yue.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import moe.yue.fontMapping

actual fun getPlatformName(): String {
    return "Desktop"
}

@Composable
actual fun imagePainter(resourcePath: String): Painter {
    return painterResource(resourcePath)
}

actual suspend fun getDefaultFont(): FontFamily = FontFamily(
    fontMapping.map { Font(it.resource, it.weight, it.style) }
)
