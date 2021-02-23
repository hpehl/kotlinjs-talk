import kotlinx.browser.document

fun main() {
    document.body?.textContent = "Hello, ${greet()}"
}

fun greet() = "world"
