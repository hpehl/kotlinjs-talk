import kotlinx.browser.document

fun main() {
    val message = "Hallo ${greet()}!"
    document.body?.textContent = message
}

fun greet() = "world"
