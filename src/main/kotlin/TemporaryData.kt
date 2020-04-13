// This is a temporary file to store configuration data.
// It will be replaced later by config files.

object TemporaryData {
    const val postsRoot = "/posts/"
    const val baseUrl =
        "https://raw.githubusercontent.com/Nekoyue/nekoyue.github.io/master" // Download assets directly from GitHub, as files are not downloadable in GitHub Pages.
    val shortUrl = mapOf("t" to "Test", "hwd" to "Hello World") // These are short urls of two posts. TODO
    val tabs = listOf(
        NavigationTabData("Index", "/"),
        NavigationTabData("Test", "/posts/Test"),
        NavigationTabData("Hello", "/posts/Hello World")
    )
    val footerMarkdown = "©2020 Kagurazaka Tsuki - Powered by [Soda](https://github.com/Nekoyue/Soda)."
}