import kotlinx.browser.document

fun main() {
    val message = "Hello ${greet()}!"
    document.body?.textContent = message
}

fun greet() = "world"
