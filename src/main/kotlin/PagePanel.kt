import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import sigmaj.notebook.Page
import tornadofx.*

private const val ICON_SIZE = 20.0
private const val CORNER_RADIUS = 3.0
private const val BORDER_WIDTH = 2.0
private const val NAME_FONT_SIZE = 18.0
private const val PADDING_V = 1.0
private const val PADDING_H = 6.0

class PagePanel(private val page: Page, private val parentView: View) : VBox() {

    private val sectionBorder = Border(BorderStroke(
            Color.web(page.color),
            BorderStrokeStyle.SOLID,
            CornerRadii(CORNER_RADIUS),
            BorderWidths(BORDER_WIDTH)))
    private val sectionBackground = Background(BackgroundFill(
            Color.web(page.color).deriveColor(1.0, 0.5, 8.0, 1.0),
            CornerRadii(CORNER_RADIUS),
            Insets(BORDER_WIDTH / 2)
    ))

    init {
        hbox {
            border = sectionBorder
            background = sectionBackground
            padding = Insets(PADDING_V, PADDING_H, PADDING_V, PADDING_H)
            hgrow = Priority.ALWAYS
            //Name
            label {
                alignment = Pos.CENTER
                text = page.name
                isWrapText = true
                font = Font.font("Monospace", NAME_FONT_SIZE)
            }
            //Spacer
            pane {
                hgrow = Priority.ALWAYS
            }
            //Icon
            label {
                alignment = Pos.CENTER
                padding = Insets(0.0, 8.0, 0.0, 8.0)
                maxHeight = Double.MAX_VALUE
                graphic = imageview {
                    alignment = Pos.CENTER
                    image = Image(
                            JournalPanel::class.java.getResourceAsStream("default-icons/${page.icon}.png"),
                            ICON_SIZE,
                            ICON_SIZE,
                            false,
                            true)
                }
            }
        }
        setMargin(children.first(), Insets(4.0))
        setOnMouseClicked {
            TODO()
        }
    }
}
