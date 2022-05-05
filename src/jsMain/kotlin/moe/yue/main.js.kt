// calling internal functions ComposeLayer and ComposeWindow
@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "EXPOSED_PARAMETER_TYPE")

package moe.yue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.native.ComposeLayer
import androidx.compose.ui.window.ComposeWindow
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.HTMLCanvasElement

val canvas = document.getElementById("ComposeTarget") as HTMLCanvasElement

fun main() {
    onWasmReady {
        canvasResize()
        ComposeWindow().apply {
            setTitle(AppTitle)
            setContent {
                var screenLayout by remember { mutableStateOf(getScreenLayout()) }
                window.addEventListener("resize", {
                    composableResize(layer)
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

fun canvasResize(width: Int = window.innerWidth, height: Int = window.innerHeight) {
    canvas.setAttribute("width", "$width")
    canvas.setAttribute("height", "$height")
}

fun composableResize(layer: ComposeLayer) {
    val scale = layer.layer.contentScale
    canvasResize()
    layer.layer.attachTo(canvas)
    layer.layer.needRedraw()
    layer.setSize(
        (canvas.width / scale).toInt(),
        (canvas.height / scale).toInt()
    )
}
