import react.*
import styled.css
import styled.styledA
import styled.styledDiv

data class TabData(val title: String, val url: String)

interface HeaderProps : RProps {
    var tabs: List<TabData>
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
                props.tabs.forEachIndexed { index, it ->
                    tab(it.title, it.url, index == props.current)
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


interface TabProps : RProps {
    var text: String
    var url: String
    var selected: Boolean // True if the tab is currently selected.
}

private class Tab : RComponent<TabProps, RState>() {
    override fun RBuilder.render() {
        styledA(href = props.url) {
            css {
                +HeaderStyles.navigationTab
                if (props.selected) {
                    +HeaderStyles.navigationTabSelected
                }
            }
            +props.text
        }
    }

}

private fun RBuilder.tab(text: String, url: String, selected: Boolean): ReactElement {
    return child(Tab::class) { //TODO to be tested: this.
        attrs.text = text
        attrs.url = url
        attrs.selected = selected
    }
}


private interface TitleProps : RProps {
    var text: String
    var imgUrl: String
    var url: String
}

private class Title : RComponent<TitleProps, RState>() {
    override fun RBuilder.render() {
        styledA(href = props.url) {
            css {
                +HeaderStyles.title
            }
            +props.text
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