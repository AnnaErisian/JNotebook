import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import sigmaj.notebook.Page
import sigmaj.notebook.Section
import tornadofx.*

class PagePanel(private val page: Page, private val parentView: View) : VBox() {
    private val a = hbox {
        add(Label(null, ImageView(
                Image(
                        PagePanel::class.java.getResourceAsStream("default-icons/sbed/shield.png"), 20.0, 20.0, false, true)
        )
        ))
        add(Label(page.name))
        border = Border(BorderStroke(Color.DARKGOLDENROD, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))
        isFillWidth = true
        maxHeight = 20.0
        minHeight = 20.0
    }

    init {
        add(a)
        setMargin(a, Insets(4.0))
        setOnMouseClicked {
            TODO("Not yet implemented")
        }
    }
}
