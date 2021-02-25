import dev.fritz2.dom.appendToBody
import dev.fritz2.dom.html.renderElement

external fun require(name: String): dynamic

fun main() {
    require("tailwindcss/dist/tailwind.css")

    appendToBody(renderElement {
        div(baseClass = "h-screen") {
            div(baseClass = "flex flex-col justify-center h-full") {
                className(store.background)
                div(baseClass = "w-1/4 mx-auto bg-gray-500 text-gray-200 p-5 rounded-lg text-center") {
                    p(baseClass = "text-2xl font-light mb-6") {
                        +"Choose a background"
                    }
                    colorNames(Color.values())
                    colorLevel(100, 900)
                }
            }
        }
    })
}
