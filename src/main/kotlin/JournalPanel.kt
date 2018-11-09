import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import sigmaj.notebook.Journal
import tornadofx.*

class JournalPanel(val journal: Journal) : VBox() {
    private val a = vbox {
        add(Label(null, ImageView(
                Image(
                        JournalPanel::class.java.getResourceAsStream("default-icons/sbed/acid.png"), 50.0, 50.0, false, true)
        )
        ))
        add(Label(journal.name))
        border = Border(BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))
        maxWidth = 100.0
        minWidth = 100.0
        maxHeight = 100.0
        minHeight = 100.0
    }

    init {
        add(a)
        setMargin(a, Insets(12.0))
        setOnMouseClicked {
            openJournal()
        }
    }

    private fun openJournal() {
        val journalScope = JournalScope(journal)
        find(BinderView::class).replaceWith(find(JournalView::class, journalScope))
    }
}
