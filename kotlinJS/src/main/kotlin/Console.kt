import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import kotlin.browser.document
import kotlin.browser.window

fun console() {
    val inputBar = document.querySelector(".logo__inputBar")!!
    fun inputBar(text: String) {
        inputBar.innerHTML = text
    }

    val arrow = document.querySelector(".logo__mark-color")!!
    val outputBar = document.querySelector(".logo__outputBar")!!
    fun outputBar(text: String, error: Boolean = false) {
        outputBar.innerHTML = text
        if (error) {
            arrow.classList.add("logo__mark-color-error")
        } else {
            arrow.classList.remove("logo__mark-color-error")
        }
    }

    var inside = false

    document.addEventListener("click", {
        inside = (it as MouseEvent).target.asDynamic().closest(".header")
    })

    var text = ""
    var lastText = ""
    var nextText = ""

    var ctrlDown = false

    val input: (KeyboardEvent) -> dynamic = { event: KeyboardEvent ->
        if (event.keyCode == 8 || event.keyCode == 9) event.preventDefault() // Prevent page actions from delete and tab
        if (inside) {
            when (event.keyCode) {
                8 -> inputBar(text.dropLast(1)) // Delete
                9 -> { // Tab
                    when {
                        text.trim() == "" -> outputBar("Available commands: cat / cd / ls")
                        text.trim().toLowerCase() == "c" -> {
                            outputBar("Available commands: cat / cd")
                            inputBar("c")
                        }
                        text.trim().toLowerCase() == "ca" || text.trimStart().toLowerCase() == "cat" -> inputBar("cat ")
                        text.trimStart().toLowerCase() == "cd" -> inputBar("cd ")
                        text.trim().toLowerCase() == "h" || text.trim().toLowerCase() == "he" || text.trim().toLowerCase() == "hel" -> inputBar("help")
                        text.trim().toLowerCase() == "l" || text.trimStart().toLowerCase() == "ls" -> inputBar("ls ")
                        text.trimStart().toLowerCase().startsWith("cat ") -> {
                            val path = text.removePrefix("cat").trim().removePrefix("./").toLowerCase()
                            val folder = if (path.contains("/")) path.split("/")[0] else ""
                            val file = if (path.contains("/")) path.split("/")[1] else path
                            when {
                                folder == "" -> {
                                    val possibleFolders = index.keys.filter { it.startsWith(file) && it != "" }
                                    val possibleFiles = index[""]?.filter { it.startsWith(file) } ?: listOf()

                                    if (possibleFolders.size == 1 && possibleFiles.isEmpty()) {
                                        val result = possibleFolders[0]
                                        inputBar(if (text.trimStart().toLowerCase().startsWith("cat ./")) "cat ./$result/" else "cat $result/")
                                        outputBar("")
                                    } else if (possibleFolders.isEmpty() && possibleFiles.size == 1) {
                                        val result = possibleFiles[0]
                                        inputBar(if (text.trimStart().toLowerCase().startsWith("cat ./")) "cat ./$result" else "cat $result")
                                        outputBar("")
                                    } else if (possibleFolders.isEmpty() && possibleFiles.isEmpty()) {
                                        outputBar("")
                                    } else {
                                        var result = ""
                                        val options = possibleFolders.toMutableList()
                                        options.forEach { result += "<b>$it</b>/    " }
                                        options.addAll(possibleFiles)
                                        possibleFiles.forEach { result += "$it    " }
                                        outputBar(result.trim())

                                        var commonPrefix = options[0]
                                        options.forEach { commonPrefix = commonPrefix.commonPrefixWith(it) }
                                        inputBar(if (text.trimStart().toLowerCase().startsWith("cat ./")) "cat ./$commonPrefix" else "cat $commonPrefix")
                                    }
                                }
                                path.count { it == '/' } > 1 || !index.keys.contains(folder) -> outputBar("")
                                else -> {
                                    var result = ""
                                    val options = index[folder]?.filter { it.startsWith(file) } ?: listOf()
                                    options.forEach { result += "$it    " }
                                    if (options.size > 1) {
                                        outputBar(result.trim())
                                    } else {
                                        outputBar("")
                                    }

                                    if (options.isNotEmpty()) {
                                        var commonPrefix = options[0]
                                        options.forEach { commonPrefix = commonPrefix.commonPrefixWith(it) }
                                        inputBar(if (text.trimStart().toLowerCase().startsWith("cat ./")) "cat ./$folder/$commonPrefix" else "cat $folder/$commonPrefix")
                                    }
                                }
                            }
                        }

                        text.trimStart().toLowerCase().startsWith("cd ") -> {
                            val path = text.removePrefix("cd").trim().removePrefix("./").toLowerCase()
                            when {
                                index.keys.filter { it.startsWith(path) }.count() == 1 -> {
                                    val result = (index.keys.filter { it.startsWith(path) })[0]
                                    inputBar(if (text.trimStart().toLowerCase().startsWith("cd ./")) "cd ./$result/" else "cd $result/")
                                    outputBar("")
                                }
                                else -> {
                                    var result = ""
                                    var options = index.keys.filter { it.startsWith(path) && it != "" }
                                    options.forEach { result += "<b>$it</b>/    " }
                                    outputBar(result.trim())
                                    if (options.size > 1) {
                                        outputBar(result.trim())
                                    } else {
                                        outputBar("")
                                    }

                                    if (options.isNotEmpty()) {
                                        var commonPrefix = options[0]
                                        options.forEach { commonPrefix = commonPrefix.commonPrefixWith(it) }
                                        inputBar(if (text.trimStart().toLowerCase().startsWith("cd ./")) "cd ./$commonPrefix" else "cd $commonPrefix")
                                    }
                                }
                            }
                        }

                        text.trimStart().toLowerCase().startsWith("ls ") -> {
                            val path = text.removePrefix("ls").trim().removePrefix("./").toLowerCase()
                            when {
                                index.keys.filter { it.startsWith(path) }.count() == 1 -> {
                                    val result = (index.keys.filter { it.startsWith(path) })[0]
                                    inputBar(if (text.trimStart().toLowerCase().startsWith("ls ./")) "ls ./$result/" else "ls $result/")
                                    outputBar("")
                                }
                                else -> {
                                    var result = ""
                                    var options = index.keys.filter { it.startsWith(path) && it != "" }
                                    options.forEach { result += "<b>$it</b>/    " }
                                    outputBar(result.trim())
                                    if (options.isNotEmpty()) {
                                        var commonPrefix = options[0]
                                        options.forEach { commonPrefix = commonPrefix.commonPrefixWith(it) }
                                        inputBar(if (text.trimStart().toLowerCase().startsWith("ls ./")) "ls ./$commonPrefix" else "ls $commonPrefix")
                                    }
                                }
                            }
                        }
                        else -> {
                            outputBar("")
                            inputBar(text.trimStart().toLowerCase())
                        }
                    }
                }

                13 -> { // Enter
                    if (text != "" && lastText != text) lastText = text
                    nextText = ""
                    inputBar("")
                    when {
                        text.trim().toLowerCase() == "cat" || text.trim().toLowerCase() == "cd" || text.trim().toLowerCase() == "" -> outputBar("")
                        text.trim().toLowerCase() == "ls" -> {
                            var result = ""
                            index.keys.filter { it != "" }.forEach { result += "<b>$it</b>/    " }
                            index[""]?.forEach { result += "$it    " }
                            outputBar(result.trim())
                        }
                        text.trim().toLowerCase() == "help" -> outputBar("Available commands: cat / cd / ls")

                        text.trimStart().toLowerCase().startsWith("cat ") -> {
                            val path = text.removePrefix("cat").trim().removePrefix("./").toLowerCase()
                            val folder = if (path.contains("/")) path.split("/")[0] else ""
                            val file = if (path.contains("/")) path.split("/")[1] else path
                            when {
                                folder == "" && index.keys.contains(path) || file == "" && index.keys.contains(folder) -> {
                                    outputBar("cat: $path: Is a directory", true)
                                }
                                path.count { it == '/' } > 1 -> {
                                    outputBar("cat: $path: File not found", true)
                                }
                                folder == "" && index[""]?.contains(file) == true -> {
                                    outputBar("Redirecting to <b>$file</b>")
                                    window.setTimeout({ window.location.href = "/${file.removeSuffix(".md")}/" }, 500)
                                }
                                index[folder]?.contains(file) == true -> {
                                    outputBar("Redirecting to <b>$folder/$file</b>")
                                    window.setTimeout({ window.location.href = "/$folder/${file.removeSuffix(".md")}/" }, 500)
                                }
                                else -> {
                                    outputBar("cat: $path: File not found", true)
                                }
                            }
                        }

                        text.trimStart().toLowerCase().startsWith("cd ") -> {
                            val path = text.removePrefix("cd").trim().removePrefix("./").toLowerCase()
                            val folder = if (path.contains("/")) path.split("/")[0] else ""
                            val file = if (path.contains("/")) path.split("/")[1] else path
                            when {
                                index.keys.contains(path.removeSuffix("/")) -> {
                                    outputBar("Redirecting to <b>$path</b>")
                                    window.setTimeout({ window.location.href = "/$path/" }, 500)
                                }
                                path.removeSuffix("/").count { it == '/' } > 1 -> {
                                    outputBar("cd: $path: Directory not found", true)
                                }
                                folder == "" && index[""]?.contains(file) == true || index[folder]?.contains(file) == true -> {
                                    outputBar("cd: $path: Not a directory", true)
                                }
                                else -> {
                                    outputBar("cd: $path: Directory not found", true)
                                }
                            }
                        }

                        text.trimStart().toLowerCase().startsWith("ls ") -> {
                            val path = text.removePrefix("ls").trim().removePrefix("./").toLowerCase()
                            if (!path.startsWith("/") && index.keys.contains(path.removeSuffix("/"))) {
                                var result = ""
                                index[path.removeSuffix("/")]?.forEach { result += "$it    " }
                                outputBar(result.trim())
                            } else {
                                outputBar("ls: ${text.removePrefix("ls ")}: No such file or directory", true)
                            }
                        }
                        else -> outputBar("Command not found: ${text.split(" ")[0]}", true)
                    }
                }
                17 -> ctrlDown = true // Ctrl
                38 -> { // Arrow up
                    nextText = text
                    inputBar(lastText)
                }
                40 -> { // Arrow down
                    lastText = text
                    inputBar(nextText)
                }
                32, in 48..90, 173, in 186..191, in 219..222 -> { // Keys and symbols
                    if (event.key == "c" && ctrlDown) {
                        lastText = text
                        nextText = ""
                        inputBar("")
                        outputBar("")
                    } else {
                        inputBar.appendChild(document.createTextNode(event.key))
                    }
                }
            }
            text = inputBar.innerHTML
        }
    }
    document.onkeydown = input.asDynamic()

    val up: (KeyboardEvent) -> dynamic = { event: KeyboardEvent ->
        if (event.keyCode == 17) {
            ctrlDown = false
        }
    }
    document.onkeyup = up.asDynamic()

}