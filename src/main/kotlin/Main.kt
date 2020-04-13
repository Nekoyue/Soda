import react.dom.render
import styled.css
import styled.styledDiv
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        styledDiv {
            css {
                +RootStyles.root
            }
            router()
        }
    }
}