// Every function needs to be called to avoid DCE.

var tmp = true // Avoid code running twice.

@JsName("kotlinMain")
fun main() {
    if (tmp) {
        menu()
        theme()
        console()
        discord()
        tmp = false
    }
}