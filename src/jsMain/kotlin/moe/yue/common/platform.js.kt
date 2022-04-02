package moe.yue.common

actual fun getPlatformName(): String {
    return "JavaScript"
}

actual fun setClipboard(content: String) {
}

actual fun openWebpage(url: String) {
}

// TODO: implement fetchFile
//fun fetchFile(url: String): ByteArray? {
//    window.fetch(url).await().arrayBuffer()
//}