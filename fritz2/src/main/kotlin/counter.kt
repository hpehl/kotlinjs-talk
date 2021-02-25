import dev.fritz2.binding.Handler
import dev.fritz2.binding.RootStore
import dev.fritz2.dom.appendToBody
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.html.renderElement

class CounterStore(val step: Int) : RootStore<Int>(0) {
    val decrement: Handler<Unit> = handle { current -> current - step }
    val increment: Handler<Unit> = handle { current -> current + step }
}

fun RenderContext.counter(step: Int) {
    val store = CounterStore(step)
    div {
        button {
            +"dec"
            clicks handledBy store.decrement
        }
        store.data.asText()
        button {
            +"inc"
            clicks handledBy store.increment
        }
    }
}

fun main() {
    appendToBody(renderElement {
        counter(10)
    })
}