@file:JsNonModule
@file:JsModule("moment")

package moment

external fun duration(millis: Long): Duration

external interface Duration {
    fun asSeconds(): String
}
