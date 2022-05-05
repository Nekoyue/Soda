// calling internal functions ComposeLayer and ComposeWindow
@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "EXPOSED_PARAMETER_TYPE")

package moe.yue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.native.ComposeLayer
import androidx.compose.ui.window.ComposeWindow
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.HTMLCanvasElement

fun main() {
    onWasmReady {
        ComposeWindow().apply {
            setTitle(AppTitle)
            setContent {
                var screenLayout by remember { mutableStateOf(getScreenLayout()) }
                canvasResize(canvas, layer)
                window.addEventListener("resize", {
                    canvasResize(canvas, layer)
                    screenLayout = getScreenLayout()
                })
                App(screenLayout)
            }
        }

//        // non-internal way of creating a composable canvas
//        Window(AppTitle) {
//            App()
//        }
    }
}


fun getScreenLayout(): ScreenLayout {
    return if (window.innerWidth > 800)
        ScreenLayout.Desktop
    else
        ScreenLayout.Mobile
}

fun canvasResize(canvas: HTMLCanvasElement, layer: ComposeLayer) {
    val scale = layer.layer.contentScale
    canvas.setAttribute("width", "${window.innerWidth}")
    canvas.setAttribute("height", "${window.innerHeight}")
    layer.layer.attachTo(canvas)
    layer.layer.needRedraw()
    layer.setSize(
        (canvas.width / scale).toInt(),
        (canvas.height / scale).toInt()
    )
}