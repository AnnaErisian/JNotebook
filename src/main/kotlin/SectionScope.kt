import sigmaj.notebook.Journal
import sigmaj.notebook.Section
import tornadofx.*

class SectionScope(val section: Section, val parentView: View, val parentScope: Scope) : Scope()
