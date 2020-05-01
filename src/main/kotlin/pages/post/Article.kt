package pages.post

import PostStyles
import react.*
import styled.css
import styled.styledDiv

// Article is the main content of a post.
// TODO: support anchor links for headings

@JsModule("react-markdown")
@JsNonModule
external val ReactMarkdown: RClass<ReactMarkdownProps>

external interface ReactMarkdownProps : RProps {
    // Full list of options: https://github.com/rexxars/react-markdown#options
    var source: String // Markdown text.
    var escapeHtml: Boolean? // Escape HTML tag or not, default: true. False will render HTML codes in the text.
}

data class ArticleData(val markdownText: String) // It stores the content of an article.

external interface ArticleProps : RProps {
    var article: ArticleData
}


class ArticleApp : RComponent<ArticleProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +PostStyles.article
            }

            ReactMarkdown {
                attrs.source = props.article.markdownText
                attrs.escapeHtml = false
            }
        }
        highlight()
    }
}

fun RBuilder.article(handler: ArticleProps.() -> Unit): ReactElement {
    return child(ArticleApp::class) {
        this.attrs(handler)
    }
}