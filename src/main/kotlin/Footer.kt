import pages.post.ReactMarkdown
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv

class Footer : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +FooterStyles.footer
            }
            ReactMarkdown {
                +TemporaryData.footerMarkdown
            }

        }
    }
}

fun RBuilder.footer() = child(Footer::class) {}