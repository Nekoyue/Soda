import pages.Error404
import pages.Index
import pages.error404
import pages.post
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
import kotlin.browser.window

external interface urlProps : RProps {
    var name: String
}

class Router : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            header {
                tabs = TemporaryData.tabs
                current = TemporaryData.tabs
                    .indexOfFirst { it.url == window.location.pathname } // TODO: it's inefficient and will be replaced.
            }

            switch {
                route("/", Index::class, exact = true)
                route<urlProps>("${TemporaryData.postsRoot}:name", exact = true) { props ->
                    val fullName = TemporaryData.tinyUrl[props.match.params.name]
                    if (fullName.isNullOrBlank()) {
                        error404()
                    } else {
                        post {
                            url =
                                "${TemporaryData.baseUrl}${TemporaryData.postsRoot}$fullName.md" //A temporary file for testing.
                        }
                    }
                }
                route("", Error404::class)
            }

            footer()
        }
    }
}

fun RBuilder.router() = child(Router::class) {}