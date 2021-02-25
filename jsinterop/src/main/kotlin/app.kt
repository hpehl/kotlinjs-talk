import kotlinx.browser.document
import kotlinx.html.InputType.text
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.input
import kotlinx.html.js.main
import kotlinx.html.js.onInputFunction
import kotlinx.html.output
import kotlinx.html.section
import org.w3c.dom.HTMLInputElement

fun main() {
    document.body?.append {
        main(classes = "center") {
            section(classes = "in-out") {
                input(classes = "in") {
                    autoFocus = true
                    type = text
                    placeholder = "in"
                    onInputFunction = { event ->
                        val input = (event.target as HTMLInputElement).value
                        document.querySelector(".out")?.textContent =
                            camelcase(input).ifEmpty { "n/a" }
                    }
                }
                div(classes = "down") { +"⇓" }
                output(classes = "out") { +"n/a" }
            }
        }
    }
}
