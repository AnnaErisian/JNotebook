import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import tornadofx.*

class SectionView: View() {
    override val scope = super.scope as SectionScope
    val section = scope.section
    val self = this
    override val root = scrollpane {
        hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
//        isFitToHeight = true
        isFitToWidth = true
        vbox {
            button {
                text = "Ayylmao"
                onMouseClicked = EventHandler {
                    find(SectionView::class).replaceWith(find(scope.parentView::class, scope.parentScope))
                }
            }
            for (subSection in section.sections) {
                sectionPanel(subSection, self)
            }
            add(Label("~~~~~~~~"))
            for (page in section.pages) {
                pagePanel(page, self)
            }
        }
    }
}