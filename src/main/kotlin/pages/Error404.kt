package pages

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.styledDiv

class Error404 : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            +"This is an extremely simple 404 page!"
        }
    }
}

fun RBuilder.error404() = child(Error404::class) {}