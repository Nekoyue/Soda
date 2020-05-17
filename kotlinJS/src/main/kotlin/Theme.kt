import kotlin.browser.document
import kotlin.browser.window

// Rewrite assets/js/theme.js in Kotlin

fun theme() {
    val getTheme = window.localStorage.getItem("theme")
    val themeToggle = document.querySelector(".theme-toggle");
    val isDark = getTheme == "dark"
    val metaThemeColor = document.querySelector("meta[name=theme-color]");

    if (getTheme !== null) {
        document.body?.classList?.toggle("dark-theme", isDark);
        if (isDark) metaThemeColor?.setAttribute("content", "#252627") else metaThemeColor?.setAttribute("content", "#fafafa");
    }

    themeToggle?.addEventListener("click", {
        document.body?.classList?.toggle("dark-theme");
        window.localStorage.setItem("theme", if (document.body?.classList?.contains("dark-theme") == true) "dark" else "light")
        if (document.body?.classList?.contains("dark-theme")==true)
            metaThemeColor?.setAttribute("content", "#252627") else metaThemeColor?.setAttribute("content", "#fafafa");
    })
}