import Color.YELLOW
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement
import react.dom.div
import react.dom.p
import react.setState
import styled.css
import styled.styledDiv

enum class Color {
    YELLOW, RED, GREEN, BLUE, INDIGO, PURPLE
}

data class ColorDefinition(val color: Color, val level: Int) {
    override fun toString(): String = "${color.name.toLowerCase()}-$level"
}

external interface ColorDefinitionState : RState {
    var colorDefinition: ColorDefinition
}

class ColorDefinitionComponent : RComponent<RProps, ColorDefinitionState>() {

    override fun ColorDefinitionState.init() {
        colorDefinition = ColorDefinition(YELLOW, 400)
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +arrayOf(
                    "flex",
                    "flex-col",
                    "justify-center",
                    "h-full",
                    "bg-${state.colorDefinition}" // bg-yellow-400
                )
            }
            div(classes = "w-1/4 mx-auto bg-gray-500 text-gray-200 p-5 rounded-lg text-center") {
                p(classes = "text-2xl font-light mb-6") {
                    +"Choose a background"
                }
                colorName {
                    colors = Color.values()
                    currentColor = state.colorDefinition.color
                    onSelectColor = { color ->
                        setState {
                            colorDefinition = colorDefinition.copy(color = color)
                        }
                    }
                }
                colorLevel {
                    min = 100
                    max = 900
                    currentLevel = state.colorDefinition.level
                    onSelectLevel = { level ->
                        setState {
                            colorDefinition = colorDefinition.copy(level = level)
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.colorDefinition(): ReactElement = child(ColorDefinitionComponent::class) {}
