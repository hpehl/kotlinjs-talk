import dev.fritz2.dom.appendToBody
import dev.fritz2.dom.html.renderElement
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.patternfly.AlertGroup
import org.patternfly.brand
import org.patternfly.modifier
import org.patternfly.notificationBadge
import org.patternfly.page
import org.patternfly.pageHeader
import org.patternfly.pageHeaderTools
import org.patternfly.pageHeaderToolsItem
import org.patternfly.pageMain
import org.patternfly.pageSection

external fun require(name: String): dynamic

fun main() {
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")

    AlertGroup.addToastAlertGroup()
    appendToBody(renderElement {
        page {
            pageHeader {
                brand {
                    link {
                        href("#")
                    }
                    img {
                        src("./logo.svg")
                    }
                }
                pageHeaderTools {
                    pageHeaderToolsItem {
                        notificationBadge()
                    }
                }
            }
            pageMain {
                pageSection(baseClass = "light".modifier()) {
                    toolbar()
                    cardView()
                }
            }
        }
    })

    MainScope().launch {
        userStore.addAll(randomUsers(73))
    }
}
