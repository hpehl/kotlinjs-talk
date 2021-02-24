import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render

external fun require(name: String): dynamic

fun main() {
    require("tailwindcss/dist/tailwind.css")

    window.onload = {
        render(document.getElementById("app")) {
            colorDefinition()
        }
    }
}
