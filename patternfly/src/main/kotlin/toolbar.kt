import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import org.patternfly.ButtonVariation
import org.patternfly.bulkSelect
import org.patternfly.dom.Id
import org.patternfly.dom.aria
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.inputFormControl
import org.patternfly.inputGroup
import org.patternfly.pagination
import org.patternfly.pushButton
import org.patternfly.sortOptions
import org.patternfly.toolbar
import org.patternfly.toolbarContent
import org.patternfly.toolbarContentSection
import org.patternfly.toolbarItem

fun RenderContext.toolbar() {
    toolbar {
        toolbarContent {
            toolbarContentSection {
                toolbarItem {
                    bulkSelect(userStore)
                }
                toolbarItem {
                    inputGroup {
                        inputFormControl(id = Id.unique("usr", "flt")) {
                            type("search")
                            aria["invalid"] = false
                            changes.values()
                                .filter { it.isEmpty() }
                                .map { domNode.id }
                                .handledBy(userStore.removeFilter)
                            changes.values()
                                .filter { it.isNotEmpty() }
                                .map { domNode.id to { user: User -> user.match(it) } }
                                .handledBy(userStore.addFilter)
                        }
                        pushButton(ButtonVariation.control) {
                            icon("search".fas())
                        }
                    }
                }
                toolbarItem {
                    sortOptions(userStore, sortInfos.values.toList())
                }
                toolbarItem {
                    pagination(userStore)
                }
            }
        }
    }
}
