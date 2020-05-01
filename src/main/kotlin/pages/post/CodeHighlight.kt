package pages.post

// import kotlinext.js.require as require

// Currently using Prism.js loaded from index.html. TODO: will be replaced with react-syntax-highlighter in the future.
@JsModule("prismjs")
@JsNonModule
external val Prism: dynamic

fun highlight() {
    // kotlinext.js.require("prismjs/themes/prism.css") // TODO: https://youtrack.jetbrains.com/issue/KT-32721
    Prism.highlightAll()
}