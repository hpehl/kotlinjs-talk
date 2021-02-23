import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.figcaption
import kotlinx.html.figure
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLImageElement

fun main() {
    document.body?.append {
        div(classes = "min-h-screen bg-gray-800 flex flex-col justify-center") {
            figure(classes = "w-2/5 mx-auto rounded-md bg-gray-100") {
                img(classes = "w-32 h-32 mx-auto rounded-full mt-8 ring-1 ring-offset-4 ring-gray-400") {
                    id = "user-photo"
                    alt = "New user"
                    onClickFunction = { reload() }
                }
                figcaption(classes = "p-8 text-center") {
                    div(classes = "text-lg text-gray-400") { +"Hi, my name is" }
                    div(classes = "text-3xl") { id = "user-name" }
                }
            }
        }
    }
    reload()
}

fun reload() {
    MainScope().launch {
        val user = randomUser()
        (document.getElementById("user-photo") as HTMLImageElement).src = user.picture.large
        document.getElementById("user-name")?.textContent = "${user.name.first} ${user.name.last}"
    }
}
