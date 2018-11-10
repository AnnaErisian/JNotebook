import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import sigmaj.notebook.Section
import tornadofx.*

private const val ICON_SIZE = 20.0
private const val CORNER_RADIUS = 3.0
private const val BORDER_WIDTH = 2.0
private const val NAME_FONT_SIZE = 18.0
private const val DATA_FONT_SIZE = 12.0
private const val PADDING_V = 1.0
private const val PADDING_H = 6.0

class SectionPanel(private val section: Section, private val parentView: View) : VBox() {

    private val sectionBorder = Border(BorderStroke(
            Color.web(section.color),
            BorderStrokeStyle.SOLID,
            CornerRadii(CORNER_RADIUS),
            BorderWidths(BORDER_WIDTH)))
    private val sectionBackground = Background(BackgroundFill(
            Color.web(section.color).deriveColor(1.0, 0.5, 8.0, 1.0),
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
                text = section.name
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
                            JournalPanel::class.java.getResourceAsStream("default-icons/${section.icon}.png"),
                            ICON_SIZE,
                            ICON_SIZE,
                            false,
                            true)
                }
            }
            //Items
            vbox {
                alignment = Pos.CENTER
                label {
                    alignment = Pos.CENTER
                    text = "Items"
                    font = Font.font("Monospace", DATA_FONT_SIZE)
                }
                label {
                    alignment = Pos.CENTER
                    text = section.items.toString()
                    font = Font.font("Monospace", DATA_FONT_SIZE)
                }
            }
            //Size
            vbox {
                alignment = Pos.CENTER
                label {
                    alignment = Pos.CENTER
                    text = "Size"
                    font = Font.font("Monospace", DATA_FONT_SIZE)
                }
                label {
                    alignment = Pos.CENTER
                    text = section.size.toString()
                    font = Font.font("Monospace", DATA_FONT_SIZE)
                }
            }
        }
        setMargin(children.first(), Insets(4.0))
        setOnMouseClicked {
            openSection()
        }
    }

    private fun openSection() {
        val sectionScope = SectionScope(section, parentView, parentView.scope)
        parentView.replaceWith(find(SectionView::class, sectionScope))
    }
}
