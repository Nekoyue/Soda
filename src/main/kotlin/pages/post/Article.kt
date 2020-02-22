package pages.post

import PostStyles
import react.*
import styled.css
import styled.styledDiv

@JsModule("react-markdown")
@JsNonModule
external val ReactMarkdown: RClass<ReactMarkdownProps>

interface ReactMarkdownProps : RProps {
    var source: String // Markdown text.
    var escapeHtml: Boolean // Escape HTML tag or not, default: true. HTML in text will be rendered if false.
}

data class ArticleData(val markdown: String) // It stores the content of an article.

interface ArticleProps : RProps {
    var article: ArticleData
}


class ArticleApp : RComponent<ArticleProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +PostStyles.article
            }

            ReactMarkdown {
                attrs.source = props.article.markdown
            }
        }
    }
}

fun RBuilder.article(handler: ArticleProps.() -> Unit): ReactElement {
    return child(ArticleApp::class) {
        this.attrs(handler)
    }
}