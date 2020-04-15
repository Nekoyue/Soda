import react.dom.render
import styled.StyledComponents
import styled.css
import styled.injectGlobal
import styled.styledDiv
import kotlin.browser.document

fun main() {
    StyledComponents.injectGlobal(GlobalStyles)

    render(document.getElementById("root")) {
        styledDiv {
            css {
                +ContainerStyles.container
            }
            router()
        }
    }
}