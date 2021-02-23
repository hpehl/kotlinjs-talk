import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.Img
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.html.TextElement
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.util

fun RenderContext.address(user: User, content: TextElement.() -> Unit = {}): TextElement =
    address(baseClass = "user-card__address") {
        content(this)
        +"${user.location.street.name} ${user.location.street.number}"
        br { }
        +"${user.location.postcode} ${user.location.city}"
        br { }
        +"${user.location.state} ${user.nat}"
        a(baseClass = "ml-sm".util()) {
            href(googleMaps(user.location))
            target("map")
            icon("map-marked-alt".fas())
        }
    }

fun RenderContext.nat(user: User): Img = img(baseClass = "user-nat") {
    src("https://lipis.github.io/flag-icon-css/flags/4x3/${user.nat.toLowerCase()}.svg")
    with(domNode) {
        title = user.nat
    }
}

fun RenderContext.photo(user: User): Div = div(baseClass = "user-photo-75") {
    img {
        src(user.picture.medium)
    }
}

fun googleMaps(location: Location): String =
    "https://www.google.com/maps/search/?api=1&query=${location.coordinates.latitude},${location.coordinates.longitude}"
