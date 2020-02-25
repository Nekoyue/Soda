package pages.post

import PostStyles
import react.*
import styled.css
import styled.styledDiv

data class MetadataInfo(val title: String) // It stores all the metadata information of a post. Perhaps better named as MetadataData(?). TODO

interface MetadataProps : RProps {
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