import react.*
import react.router.dom.routeLink
import styled.css
import styled.styledDiv

data class NavigationTabData(val title: String, val url: String)

external interface HeaderProps : RProps {
    var navigationTabs: List<NavigationTabData>
    var current: Int // The index of current selected tab
}

class Header : RComponent<HeaderProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +HeaderStyles.header
            }
            title("Kagurazaka Tsuki", "", "/") // TODO: to be configurable.

            styledDiv {
                css {
                    +HeaderStyles.navigation
                }
                props.navigationTabs.forEachIndexed { index, it ->
                    navigationTab(it.title, it.url, index == props.current)
                }
            }
        }
    }
}

fun RBuilder.header(handler: HeaderProps.() -> Unit): ReactElement {
    return child(Header::class) {
        this.attrs(handler)
    }
}

// Title
private external interface TitleProps : RProps {
    var text: String
    var imgUrl: String
    var url: String
}

private class Title : RComponent<TitleProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +HeaderStyles.title
            }
            routeLink(to = props.url) {
                +props.text
            }
        }
    }
}

private fun RBuilder.title(text: String, imgUrl: String, url: String): ReactElement {
    return child(Title::class) {
        attrs.text = text
        attrs.imgUrl = imgUrl
        attrs.url = url
    }
}

// Navigation tabs
external interface NavigationTabProps : RProps {
    var text: String
    var url: String
    var selected: Boolean // True if the tab is selected.
}

private class NavigationTab : RComponent<NavigationTabProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +HeaderStyles.navigationTab
                if (props.selected) {
                    +HeaderStyles.navigationTabSelected
                }
            }
            routeLink(to = props.url) {
                +props.text
            }
        }

    }

}

private fun RBuilder.navigationTab(text: String, url: String, selected: Boolean): ReactElement {
    return child(NavigationTab::class) { //TODO to be tested: this.
        attrs.text = text
        attrs.url = url
        attrs.selected = selected
    }
}