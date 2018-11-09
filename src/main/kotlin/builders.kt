import javafx.event.EventTarget
import javafx.scene.Node
import sigmaj.notebook.Journal
import sigmaj.notebook.Page
import sigmaj.notebook.Section
import tornadofx.*

private inline fun <T : Node> opcr(parent: EventTarget, node: T, op: T.() -> Unit = {}) = node.apply {
    parent.addChildIfPossible(this)
    op(this)
}

fun EventTarget.journalPanel(journal: Journal, op: JournalPanel.() -> Unit = {}) =
        opcr(this, JournalPanel(journal), op)

fun EventTarget.sectionPanel(section: Section, parentView: View, op: SectionPanel.() -> Unit = {}) =
        opcr(this, SectionPanel(section, parentView), op)

fun EventTarget.pagePanel(page: Page, parentView: View, op: PagePanel.() -> Unit = {}) =
        opcr(this, PagePanel(page, parentView), op)
