import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import javafx.stage.Screen
import sigmaj.notebook.Journal
import tornadofx.*


private const val PANEL_WIDTH = 275.0
private const val PANEL_PADDING = 22.0
private const val ICON_HEIGHT = 200.0
private const val ICON_PADDING = 20.0
private const val LABEL_ICON_SEPARATION = 30.0
private const val LABEL_PADDING = 5.0
private const val LABEL_FONT_SIZE = 24.0
private const val MAX_LABEL_LINES = 2
private const val CORNER_RADIUS = 8.0
private const val BORDER_WIDTH = 4.0


class JournalPanel(val journal: Journal) : VBox() {

    private val journalBorder = Border(BorderStroke(
            Color.web(journal.color),
            BorderStrokeStyle.SOLID,
            CornerRadii(CORNER_RADIUS),
            BorderWidths(BORDER_WIDTH)))
    private val journalBackground =  Background(BackgroundFill(
            Color.web(journal.color).deriveColor(1.0, 0.5, 8.0, 1.0),
            CornerRadii(CORNER_RADIUS),
            Insets(BORDER_WIDTH/2)
    ))

    init {
        vgrow = Priority.ALWAYS
        borderpane {

            isFillWidth = true
            top {
                pane {
                    border = journalBorder
                    background = journalBackground
                    label {
                        alignment = Pos.CENTER
                        maxWidth = PANEL_WIDTH
                        minWidth = PANEL_WIDTH
                        maxHeight = ICON_HEIGHT + ICON_PADDING
                        minHeight = ICON_HEIGHT + ICON_PADDING
                        graphic = imageview {
                            image = Image(
                                    JournalPanel::class.java.getResourceAsStream("default-icons/${journal.icon}.png"),
                                    ICON_HEIGHT,
                                    ICON_HEIGHT,
                                    false,
                                    true)
                        }
                    }
                }
            }
            center {
                vgrow = Priority.ALWAYS
                pane {
                    minHeight = LABEL_ICON_SEPARATION
                }
            }
            bottom {
                alignment = Pos.BOTTOM_CENTER
                vbox {
                    border = journalBorder
                    background = journalBackground
                    label {
                        alignment = Pos.CENTER
                        prefWidth = PANEL_WIDTH - LABEL_PADDING
                        text = journal.name
                        textAlignment = TextAlignment.CENTER
                        isWrapText = true
                        font = Font.font("Monospace", LABEL_FONT_SIZE)
                        maxHeight = LABEL_FONT_SIZE / 72 * Screen.getPrimary().dpi * (MAX_LABEL_LINES)
                    }
                    setMargin(children.first(), Insets(LABEL_PADDING))
                }
            }

        }
        setMargin(children.first(), Insets(PANEL_PADDING))
        setOnMouseClicked {
            openJournal()
        }
    }

    private fun openJournal() {
        val journalScope = JournalScope(journal)
        find(BinderView::class).replaceWith(find(JournalView::class, journalScope))
    }
}
