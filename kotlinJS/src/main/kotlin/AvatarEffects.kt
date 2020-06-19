import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.removeClass

fun avatar() {
    val avatar = document.querySelector(".avatar")
    val image = document.querySelector(".avatar-img")
    val overlay = document.querySelector(".avatar-overlay")
    val root = document.querySelector(".container")
    val header = document.querySelector(".header")
    val text = document.querySelector(".homeSubtitle")
    val originalText = text?.innerHTML.toString()

    var state = 0 // 0=original

    fun fire() {
        println("CPU fan effect: on (It's not a feature, it's a bug)")
        lightTheme()
        image?.setAttribute("src", "/images/avatar-fire.jpg")
        overlay?.addClass("avatarEffects-overlay-fire")
        root?.addClass("avatarEffects-background-fire")
        header?.addClass("avatarEffects-header-fire")
        text?.innerHTML = originalText.replace("right here!", "in <span style=\"color: #6c0000; font-weight: 600\">fire!</span>")
    }

    fun ice() {
        lightTheme()
        image?.setAttribute("src", "/images/avatar-ice.jpg")
        overlay?.addClass("avatarEffects-overlay-ice")
        root?.apply {
            removeClass("avatarEffects-background-fire")
            addClass("avatarEffects-background-ice")
        }
        header?.addClass("avatarEffects-header-ice")
        text?.innerHTML = originalText.replace("right here!", "in <span style=\"color: #003e80; font-weight: 600\">ice!</span>")
    }

    fun reset() {
        println("CPU fans effect: off")
        defaultTheme()
        image?.setAttribute("src", "/images/avatar.jpg")
        overlay?.apply {
            removeClass("avatarEffects-overlay-fire")
            removeClass("avatarEffects-overlay-ice")
        }
        root?.apply {
            removeClass("avatarEffects-background-ice")
        }
        header?.apply {
            removeClass("avatarEffects-header-fire")
            removeClass("avatarEffects-header-ice")
        }
        text?.innerHTML = originalText
    }

    avatar?.addEventListener("click", {
        when (state % 3) {
            0 -> fire()
            1 -> ice()
            2 -> reset()
        }
        state++
    })
}