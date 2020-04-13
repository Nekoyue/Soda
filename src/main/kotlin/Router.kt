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

// Router will handle the path of the url and display the corresponding page.

external interface urlProps : RProps {
    var name: String
}

class Router : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            header {
                navigationTabs = TemporaryData.tabs
                current = TemporaryData.tabs
                    .indexOfFirst { it.url == window.location.pathname } // TODO: it's inefficient and will be replaced.
            }

            switch {
                route("/", Index::class, exact = true)
                route<urlProps>("${TemporaryData.postsRoot}:name", exact = true) { props ->
                    val request = props.match.params.name // The requested url.
                    val fullName = TemporaryData.shortUrl[request].also { }
                        ?: request.takeIf { // Check if the request url is valid.
                            TemporaryData.shortUrl.containsValue(request)
                        }

                    if (fullName == null) {
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