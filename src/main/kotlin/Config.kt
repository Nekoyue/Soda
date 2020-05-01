// This file stores all configurations.
// TODO: load configurations from an external Config.kts file, having no idea on how to implement it.

object Config {
    // The folder stores all files images and files.
    const val resourcesFolder = "/resources/"

    // The full url of where all files store.
    // Can leave empty if not using Github pages.
    const val baseUrl =
        "https://raw.githubusercontent.com/Nekoyue/nekoyue.github.io/master"

    val tabs = listOf(
        NavigationTabData("Index", "/index"),
        NavigationTabData("Test", "/posts/Test"),
        NavigationTabData("Hello", "/posts/HelloWorld")
    )

    // It indexes the possible paths of each pages.
    // Format: Map<fullPathName:String, alternativePathNames:List<String>>
    // while alternative path names will be redirected to pathname.
    val pages = mapOf(
        "/" to listOf("/index"),
        "/posts/Test" to listOf("/t", "/1", "/posts/t", "/posts/1"),
        "/posts/HelloWorld" to listOf("/hwd", "/2", "/posts/hwd", "/posts/2")
    )

    val footerMarkdown = "©2020 Kagurazaka Tsuki - Powered by [Soda](https://github.com/Nekoyue/Soda)."
}