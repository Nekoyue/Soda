package pages

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv

class Index : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {

            }
            +"This is an extremely simple index page!"
        }
    }
}

fun RBuilder.index() = child(Index::class) {}