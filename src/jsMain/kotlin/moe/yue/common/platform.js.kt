package moe.yue.common

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.khronos.webgl.Int8Array

actual fun getPlatformName(): String {
    return "JavaScript"
}

actual fun setClipboard(content: String) {
}

actual fun openWebpage(url: String) {
}

suspend fun fetchFile(url: String): ByteArray? {
    val response = window.fetch(url).await()
    return if (response.ok)
        Int8Array(response.arrayBuffer().await()).unsafeCast<ByteArray>()
    else null
}