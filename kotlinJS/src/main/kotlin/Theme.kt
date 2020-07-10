import kotlin.browser.document
import kotlin.browser.window

// Rewrite assets/js/theme.js in Kotlin


private fun getTheme() = window.localStorage.getItem("theme")
private val themeToggle = document.querySelector(".theme-toggle")
private fun isDark() = getTheme() == "dark"
private val metaThemeColor = document.querySelector("meta[name=theme-color]")

fun theme() {
    defaultTheme()

    themeToggle?.addEventListener("click", {
        if (isDark()) {
            lightTheme()
            window.localStorage.setItem("theme", "light")
        } else {
            darkTheme()
            window.localStorage.setItem("theme", "dark")
        }
    })
}

fun lightTheme() {
    document.body?.classList?.remove("dark-theme")
    metaThemeColor?.setAttribute("content", "#252627")
}

fun darkTheme() {
    document.body?.classList?.add("dark-theme")
    metaThemeColor?.setAttribute("content", "#fafafa")
}


fun defaultTheme() {
    when {
        isDark() -> darkTheme()
        else -> lightTheme()
    }
}