import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement
import react.dom.div
import react.dom.input
import react.dom.output

external interface ColorLevelProps : RProps {
    var min: Int
    var max: Int
    var currentLevel: Int
    var onSelectLevel: (Int) -> Unit
}

class ColorLevelComponent : RComponent<ColorLevelProps, RState>() {

    override fun RBuilder.render() {
        div(classes = "w-full flex") {
            input(classes = "flex-1 rounded focus:outline-none focus:ring-1 focus:ring-gray-600") {
                attrs {
                    type = InputType.range
                    min = props.min.toString()
                    max = props.max.toString()
                    step = "100"
                    value = props.currentLevel.toString()
                    onChangeFunction = { event ->
                        val level = (event.target as HTMLInputElement).value.toIntOrNull() ?: props.min
                        props.onSelectLevel(level)
                    }
                }
            }
            output(classes = "ml-3") {
                +props.currentLevel.toString()
            }
        }
    }
}

fun RBuilder.colorLevel(handler: ColorLevelProps.() -> Unit): ReactElement =
    child(ColorLevelComponent::class) {
        attrs(handler)
    }