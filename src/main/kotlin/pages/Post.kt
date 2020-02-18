package pages

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import pages.post.ArticleData
import pages.post.MetadataInfo
import pages.post.article
import pages.post.metadata
import react.*
import react.dom.div
import kotlin.browser.window

data class PostData(val metadata: MetadataInfo, val article: ArticleData) // It stores all information of one post.

interface PostProps : RProps {
    var url: String
}

interface PostState : RState {
    var ok: Boolean // Redirect to 404 if false.
    var post: PostData
}

class Post : RComponent<PostProps, PostState>() {
    override fun PostState.init() {
        post = PostData(MetadataInfo("Loading"), ArticleData("Loading")) // Temporary initialized for testing.
        val mainScope = MainScope() // Using kotlin coroutine.
        mainScope.launch {
            val parsedPost = parsePost(props.url)
            setState {
                post = parsedPost
            }
        }
    }

    override fun RBuilder.render() {
        div {
            metadata {
                metadata = state.post.metadata
            }
        }

        div {
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

// To be finished
private suspend fun parsePost(url: String): PostData {
    val raw = window.fetch(url).await().text().await().toString()  // Coroutine

    return PostData(metadata = MetadataInfo("Title"), article = ArticleData(raw))
}