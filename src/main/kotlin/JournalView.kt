import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import tornadofx.*

class JournalView: View() {
    override val scope = super.scope as JournalScope
    val journal = scope.journal
    val self = this
    override val root = scrollpane {
        hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
//        isFitToHeight = true
        isFitToWidth = true
        vbox {
            button {
                text = "Ayylmao"
                onMouseClicked = EventHandler {
                    find(JournalView::class).replaceWith(find(BinderView::class, DefaultScope))
                }
            }
            for (subSection in journal.sections) {
                sectionPanel(subSection, self)
            }
            add(Label("~~~~~~~~"))
            for (page in journal.pages) {
                pagePanel(page, self)
            }
        }
    }
}