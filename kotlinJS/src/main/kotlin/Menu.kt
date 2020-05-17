import kotlin.browser.document
import kotlin.browser.window

// Rewrite assets/js/menu.js in Kotlin

fun menu() {
    val menuTrigger = document.querySelector(".menu-trigger")
    val menu = document.querySelector(".menu")
    val mobileQuery = document.body?.let { window.getComputedStyle(it).getPropertyValue("--phoneWidth") }
    fun isMobile() = mobileQuery?.let { window.matchMedia(it).matches }
    fun isMobileMenu() {
        isMobile()?.let { menuTrigger?.classList?.toggle("hidden", !it) }
        isMobile()?.let { menu?.classList?.toggle("hidden", it) }
    }

    isMobileMenu()


    menuTrigger?.addEventListener("click", {
        menu?.classList?.toggle("hidden")
    })

    window.addEventListener("resize", { isMobileMenu() })
}