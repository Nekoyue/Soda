import pages.error404
import pages.index
import pages.post
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
import styled.css
import styled.styledDiv
import kotlin.browser.window

// Router will handle the path of the url and display the corresponding page.

class Router : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            header {
                navigationTabs = Config.tabs
                current = Config.tabs
                    .indexOfFirst { it.url == window.location.pathname } // TODO: it's inefficient and will be replaced.
            }

            styledDiv {
                css {
                    +ContentStyles.content
                }

                switch {
                    route("") {
                        val path = window.location.pathname
                        val fullName =
                            if (Config.pages.containsKey(path)) { // The request already uses the full path name.
                                path
                            } else {
                                Config.pages.filter { it.value.contains(path) }.keys.firstOrNull() // Change to full path name if using alternative path names.
                                    .also { window.history.pushState(null, "", it) } // Redirect to new url.
                            }

                        when (fullName) {
                            null -> error404()
                            "/" -> index()
                            else -> post {
                                url =
                                    "${Config.baseUrl}${Config.resourcesFolder}$fullName.md" // The location of markdown file.
                            }

                        }
                    }
                }
            }

            footer()
        }
    }
}

fun RBuilder.router() = child(Router::class) {}