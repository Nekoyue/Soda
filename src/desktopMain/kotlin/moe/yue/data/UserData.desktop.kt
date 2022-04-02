package moe.yue.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun imagePainter(resourcePath: String): Painter {
    return painterResource(resourcePath)
}