package pages.post

import react.*

data class MetadataInfo(val title: String) // It stores all the metadata information of a post.

interface MetadataProps : RProps {
    var metadata: MetadataInfo
}


class Metadata : RComponent<MetadataProps, RState>() {

    override fun RBuilder.render() {
        +props.metadata.title
    }
}

fun RBuilder.metadata(handler: MetadataProps.() -> Unit): ReactElement {
    return child(Metadata::class) {
        this.attrs(handler)
    }
}