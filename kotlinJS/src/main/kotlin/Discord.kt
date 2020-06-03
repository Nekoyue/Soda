import org.w3c.dom.Element
import org.w3c.dom.NodeList
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.addClass
import kotlin.dom.appendElement
import kotlin.dom.removeClass

// Add click to copy to discord social icon.


private fun NodeList.getDiscord(): Element? {
    for (i in 0 until this.length) {
        val node = this[i] as Element
        if (node.getAttribute("title") == "Discord"
                && node.getAttribute("href")?.matches("#\\d{4}\$") == true) { // Only match username#0000.
            return node
        }
    }
    return null
}

var count = 0
val messages_ = listOf("Copied", "Double Copy!", "Triple Copy!", "Dominating!!", "Rampage!!", "Mega Copy!!", "Unstoppable!!", "Wicked Sick!!", "Monster Copy!!!", "GODLIKE!!!!", "BEYOND GODLIKE!!!!")

fun discord() {
    val icon = document.querySelectorAll(".socialIcon").getDiscord()
    val username = icon?.getAttribute("href")
    icon?.removeAttribute("href")
    icon?.addClass("socialIcon-discord")

    var bubble: Element? = icon?.appendElement("span") { this.addClass("bubble") }

    fun bubble(text: String, classes: String) {
        bubble?.innerHTML = text
        bubble?.addClass(classes)
        val offsetX = bubble?.asDynamic().offsetWidth / 2 - 12
        bubble?.setAttribute("style", "left: -${offsetX}px;")
    }

    icon?.addEventListener("mouseenter", {
        bubble?.removeClass("bubble-copied", "bubble-copied-fadeout", "bubble-copied-overflow", "bubble-copied-overflow-fadeout")
        bubble?.innerHTML = ""
        bubble?.addClass("bubble-hint")
        bubble("Click to copy username", "")
    })

    icon?.addEventListener("click", {
        bubble?.removeClass("bubble-hint", "bubble-copied-fadeout", "bubble-copied-overflow")
        window.setTimeout({ bubble?.addClass("bubble-copied", "bubble-copied-fadeout") }, 150)
        if (count < messages_.size) {
            bubble(messages_[count], "bubble-copied")
            username?.let { username -> window.navigator.clipboard.writeText(username) }
        } else {
            bubble?.removeClass("bubble-copied")
            bubble?.addClass("bubble-copied-overflow")
            window.setTimeout({ bubble?.addClass("bubble-copied-overflow-fadeout") }, 150)
            bubble("IndexOutOfBoundsException", "")
        }
        count++
    })

    icon?.addEventListener("mouseleave", {
        count = 0
    })
}