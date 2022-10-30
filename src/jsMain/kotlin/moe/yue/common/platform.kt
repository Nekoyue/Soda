package moe.yue.common

import androidx.compose.runtime.*
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.khronos.webgl.Int8Array



/**Skiko implementation (without response.ok check) can be found at [org.jetbrains.skiko.loadBytesFromPath]*/
suspend fun fetchFile(url: String): ByteArray? {
    val response = window.fetch(url).await()
    return if (response.ok)
        Int8Array(response.arrayBuffer().await()).unsafeCast<ByteArray>()
    else null
}
