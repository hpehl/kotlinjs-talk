import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onKeyDownFunction
import kotlinx.html.tabIndex
import org.w3c.dom.events.KeyboardEvent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement
import react.dom.ol
import styled.css
import styled.styledLi

external interface ColorNameProps : RProps {
    var colors: Array<Color>
    var currentColor: Color
    var onSelectColor: (Color) -> Unit
}

class ColorNameComponent : RComponent<ColorNameProps, RState>() {

    override fun RBuilder.render() {
        ol(classes = "mb-3 rounded focus:outline-none focus:ring-2 focus:ring-gray-600") {
            attrs {
                tabIndex = "0"
                onKeyDownFunction = { event ->
                    when (event.unsafeCast<KeyboardEvent>().key) {
                        "ArrowUp" -> previousColor()
                        "ArrowDown" -> nextColor()
                    }
                }
            }
            props.colors.forEach { color ->
                styledLi {
                    css {
                        +arrayOf("p-0.5", "cursor-pointer")
                        if (props.currentColor == color) {
                            +arrayOf("bg-gray-400", "rounded")
                        }
                    }
                    +color.name.toLowerCase()
                    attrs.onClickFunction = {
                        props.onSelectColor(color)
                    }
                }
            }
        }
    }

    fun previousColor() {
        val index = props.colors.indexOf(props.currentColor)
        val previous = if (index == 0) props.colors.lastIndex else index - 1
        props.onSelectColor(props.colors[previous])
    }

    fun nextColor() {
        val index = props.colors.indexOf(props.currentColor)
        val next = if (index == props.colors.lastIndex) 0 else index + 1
        props.onSelectColor(props.colors[next])
    }
}

fun RBuilder.colorName(handler: ColorNameProps.() -> Unit): ReactElement =
    child(ColorNameComponent::class) {
        attrs(handler)
    }