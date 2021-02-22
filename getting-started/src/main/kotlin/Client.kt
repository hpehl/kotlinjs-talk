import kotlinx.browser.document

fun main() {
    console.log("Hello, ${greet()}")
    document.body?.textContent = "Hello, ${greet()}"
}

fun greet() = "world"
