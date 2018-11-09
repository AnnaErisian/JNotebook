import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import sigmaj.notebook.Section
import tornadofx.*

class SectionPanel(private val section: Section, private val parentView: View) : VBox() {
    private val a = hbox {
        add(Label(null, ImageView(
                Image(
                        SectionPanel::class.java.getResourceAsStream("default-icons/sbed/chaingun.png"), 20.0, 20.0, false, true)
        )
        ))
        add(Label(section.name))
        border = Border(BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))
        isFillWidth = true
        maxHeight = 20.0
        minHeight = 20.0
    }

    init {
        add(a)
        setMargin(a, Insets(4.0))
        setOnMouseClicked {
            openSection()
        }
    }

    private fun openSection() {
        val sectionScope = SectionScope(section, parentView, parentView.scope)
        parentView.replaceWith(find(SectionView::class, sectionScope))
    }
}
