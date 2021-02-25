import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.w3c.dom.HTMLInputElement

fun RenderContext.colorLevel(min: Int, max: Int) {
    div(baseClass = "w-full flex") {
        input(baseClass = "flex-1 rounded focus:outline-none focus:ring-1 focus:ring-gray-600") {
            type("range")
            min(min.toString())
            max(max.toString())
            step("100")
            value(store.data.map { it.level.toString() })
            changes.map {
                it.target.unsafeCast<HTMLInputElement>().value.toInt()
            } handledBy store.changeLevel
        }
        output(baseClass = "ml-3") {
            store.data.map { it.level }.asText()
        }
    }
}
