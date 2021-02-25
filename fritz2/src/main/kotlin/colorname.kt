import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

fun RenderContext.colorNames(colors: Array<Color>) {
    ol(baseClass = "mb-3 rounded focus:outline-none focus:ring-2 focus:ring-gray-600") {
        attr("tabIndex", "0")

        keydowns.map { it.key }.filter { it == "ArrowUp" }
            .map { } handledBy store.previousColor
        keydowns.map { it.key }.filter { it == "ArrowDown" }
            .map { } handledBy store.nextColor

        colors.forEach { color ->
            li(baseClass = "p-0.5 cursor-pointer") {
                classMap(store.data.map { cd ->
                    mapOf("bg-gray-400 rounded" to (cd.color == color))
                })
                +color.name.toLowerCase()
                clicks.map { color } handledBy store.changeColor
            }
        }
    }
}
