import kotlin.browser.document

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
        lightTheme()
        image?.setAttribute("src", "/images/avatar-fire.jpg")
        overlay?.setAttribute("style", "border-color: #ffb6a8")
        root?.setAttribute("style", "background: linear-gradient(to bottom right, #ff9975 0%, #db4f59 100%);")
        header?.setAttribute("style", "background: #ff997530")
        text?.innerHTML = originalText.replace("ice!", "<span style=\"color: #6c0000; font-weight: 600\">fire!</span>")
    }

    fun ice() {
        lightTheme()
        image?.setAttribute("src", "/images/avatar-ice.jpg")
        overlay?.setAttribute("style", "border-color: #caeafc")
        root?.setAttribute("style", "background: linear-gradient(to bottom right, #00bcd2 0%, #9bcdfc 100%);")
        header?.setAttribute("style", "background: #00bcd230")
        text?.innerHTML = originalText.replace("ice!", "<span style=\"color: #003e80; font-weight: 600\">ice!</span>")
    }

    fun reset() {
        defaultTheme()
        image?.setAttribute("src", "/images/avatar.jpg")
        overlay?.setAttribute("style", "border-color: #fff")
        root?.removeAttribute("style")
        header?.removeAttribute("style")
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