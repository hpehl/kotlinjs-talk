import dev.fritz2.dom.html.RenderContext
import org.patternfly.Align.RIGHT
import org.patternfly.Notification
import org.patternfly.card
import org.patternfly.cardAction
import org.patternfly.cardBody
import org.patternfly.cardCheckbox
import org.patternfly.cardFooter
import org.patternfly.cardHeader
import org.patternfly.cardView
import org.patternfly.classes
import org.patternfly.dropdown
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.item
import org.patternfly.items
import org.patternfly.kebabToggle
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.unwrap
import org.patternfly.util

fun RenderContext.cardView() {
    cardView(userStore) {
        display { user ->
            card(user, baseClass = classes {
                +"hoverable".modifier()
                +"compact".modifier()
                +"flat".modifier()
                +"user-card"
            }) {
                cardHeader {
                    nat(user)
                    span(baseClass = classes("ml-sm".util(), "user-card__title")) {
                        +"${user.name.first} ${user.name.last}"
                    }
                    cardAction {
                        dropdown<String>(align = RIGHT) {
                            store.clicked.unwrap() handledBy Notification.add { action ->
                                info("$action ${user.name.last} not yet implemented")
                            }
                            kebabToggle()
                            items {
                                item("Edit")
                                item("Remove")
                            }
                        }
                        cardCheckbox()
                    }
                }
                cardBody(baseClass = classes {
                    +"flex".layout()
                    +"inline-flex".modifier()
                    +"align-items-center".modifier()
                }) {
                    photo(user)
                    address(user)
                }
                cardFooter {
                    icon(iconClass = "user-alt".fas(), baseClass = "mr-sm".util())
                    +user.login.username
                }
            }
        }
    }
}
