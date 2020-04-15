package pages

import PostStyles
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import pages.post.ArticleData
import pages.post.MetadataInfo
import pages.post.article
import pages.post.metadata
import react.*
import styled.css
import styled.styledDiv
import kotlin.browser.window

data class PostData(val metadata: MetadataInfo, val article: ArticleData) // It stores all information of one post.

external interface PostProps : RProps {
    var url: String
}

external interface PostState : RState {
    var url: String
    var post: PostData
}

class Post : RComponent<PostProps, PostState>() {
    private fun update() {
        val mainScope = MainScope() // Using kotlin coroutine to download the post.
        mainScope.launch {
            setState { post = PostData(MetadataInfo("Loading"), ArticleData("Loading")) }
            val parsedPost = parsePost(props.url)
            setState {
                post = parsedPost
            }
        }
    }

    override fun PostState.init() {
        post = PostData(MetadataInfo("Loading"), ArticleData("Loading")) // Temporary initialized for testing.
        url = ""
        update()
    }

    override fun RBuilder.render() {
        if (state.url != props.url) {
            update()
            setState { url = props.url }
        }

        styledDiv {
            css {
                +PostStyles.post
            }

            metadata {
                metadata = state.post.metadata
            }

            article {
                article = state.post.article
            }

        }
    }
}

fun RBuilder.post(handler: PostProps.() -> Unit): ReactElement {
    return child(Post::class) {
        this.attrs(handler)
    }
}

// TODO
private suspend fun parsePost(url: String): PostData {
    val raw = window.fetch(url).await().text().await().toString()  // Coroutine

    return PostData(metadata = MetadataInfo("Title"), article = ArticleData(raw))
}