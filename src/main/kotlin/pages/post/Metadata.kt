package pages.post

import PostStyles
import react.*
import styled.css
import styled.styledDiv

// Metadata include information like time created and last edited. It shows above the article.

data class MetadataInfo(val title: String) // It stores all the metadata information of a post. Perhaps better to be named as MetadataData(?). TODO

external interface MetadataProps : RProps {
    var metadata: MetadataInfo
}


class Metadata : RComponent<MetadataProps, RState>() {

    override fun RBuilder.render() {
        styledDiv {
            css {
                +PostStyles.metadata
            }

            +props.metadata.title // TODO
        }
    }
}

fun RBuilder.metadata(handler: MetadataProps.() -> Unit): ReactElement {
    return child(Metadata::class) {
        this.attrs(handler)
    }
}