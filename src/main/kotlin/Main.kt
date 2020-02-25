import pages.post
import react.dom.render
import styled.css
import styled.styledDiv
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    render(document.getElementById("root")) {
        styledDiv {
            css {
                +RootStyles.root
            }
            header {
                tabs = TemporaryData.tabs
                current = TemporaryData.tabs
                    .indexOfFirst { it.url == window.location.pathname } // TODO: it's inefficient and will be replaced.
            }
//            router()
            post {
                url = "/posts/Test.md"
            }
            footer()
        }
    }
}