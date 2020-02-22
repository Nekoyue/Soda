import pages.post
import react.dom.render
import styled.css
import styled.styledDiv
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        console.log("App start")
        styledDiv {
            css {
                +RootStyles.root
            }

            post {
                url = "/posts/Test.md" //A temporary file for testing.
            }
        }
    }
}