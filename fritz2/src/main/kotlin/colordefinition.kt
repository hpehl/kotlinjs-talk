import Color.YELLOW
import dev.fritz2.binding.Handler
import dev.fritz2.binding.RootStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val store = ColorDefinitionStore()

enum class Color {
    YELLOW, RED, GREEN, BLUE, INDIGO, PURPLE
}

data class ColorDefinition(val color: Color, val level: Int) {
    override fun toString(): String = "${color.name.toLowerCase()}-$level"
}

class ColorDefinitionStore : RootStore<ColorDefinition>(ColorDefinition(YELLOW, 400)) {

    val background: Flow<String> = data.map { "bg-${it.color.name.toLowerCase()}-${it.level}" }

    val changeColor: Handler<Color> = handle { cd, color ->
        cd.copy(color = color)
    }

    val changeLevel: Handler<Int> = handle { cd, level ->
        cd.copy(level = level)
    }

    val previousColor: Handler<Unit> = handle { cd ->
        val index = Color.values().indexOf(cd.color)
        val previous = if (index == 0) Color.values().lastIndex else index - 1
        cd.copy(color = Color.values()[previous])
    }

    val nextColor: Handler<Unit> = handle { cd ->
        val index = Color.values().indexOf(cd.color)
        val next = if (index == Color.values().lastIndex) 0 else index + 1
        cd.copy(color = Color.values()[next])
    }
}
