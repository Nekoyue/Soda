// This is a temporary file to store configuration data
// It will be replaced later by [MarkdownConfig](https://github.com/Nekoyue/MarkdownConfig)

object TemporaryData {
    const val postsRoot = "/posts/"
    const val baseUrl =
        "https://raw.githubusercontent.com/Nekoyue/nekoyue.github.io/master" // Download assets directly from GitHub, as files are not downloadable in GitHub Pages.
    val tinyUrl = mapOf("t" to "Test", "hwd" to "Hello World") // These are tiny urls of two temporary files. TODO
    val tabs = listOf(TabData("Index", "/"), TabData("Test", "/posts/Test"), TabData("Hello", "/posts/Hello World"))
    val footerMarkdown = "©2020 Kagurazaka Tsuki - Powered by [Soda](https://github.com/Nekoyue/Soda)."
}