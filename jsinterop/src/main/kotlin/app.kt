import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.html.button
import kotlinx.html.currentTimeMillis
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.p
import org.w3c.dom.Element

var running = false
var timestamp = 0L
val values = (100..900 step 100).toList()
val names = arrayOf("red", "yellow", "green", "blue", "indigo", "purple", "pink")
val colors = flow {
    while (running) {
        val color = "${names.random()}-${values.random()}"
        emit(color)
        delay(1000)
    }
}

external fun require(name: String): dynamic

fun main() {
    require("tailwindcss/dist/tailwind.css")

    document.body!!.append {
        div("min-h-screen flex flex-col justify-center") {
            div(classes = "mx-auto text-center") {
                button(
                    classes = "text-xl text-gray-100 " +
                            "bg-gray-500 hover:bg-gray-600 " +
                            "rounded-lg " +
                            "border-4 border-gray-100 " +
                            "p-7 " +
                            "transition duration-500 " +
                            "focus:outline-none"
                ) {
                    +"Choose a color"
                    onClickFunction = { event ->
                        if (running) {
                            running = false
                            val elapsed = currentTimeMillis() - timestamp
                            document.getElementById("time")?.textContent =
                                "It took you ${moment.duration(elapsed).asSeconds()} seconds to choose a color."
                        } else {
                            running = true
                            timestamp = currentTimeMillis()
                            MainScope().launch {
                                colors.collect { color ->
                                    (event.target as Element).textContent = "Choose $color"
                                    document.body!!.className = "transition duration-500 bg-$color"
                                }
                            }
                        }
                    }
                }
                p(classes = "text-color-grey-400 mt-5") {
                    id = "time"
                    +"No color chosen yet."
                }
            }
        }
    }
}
