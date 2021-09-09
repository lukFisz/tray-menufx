package com.github.lukfisz.traymenufx.menu

import com.github.lukfisz.traymenufx.core.TrayMenuFX
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.input.MouseButton
import javafx.scene.layout.Pane
import javafx.stage.Stage
import java.awt.Image
import java.net.URL

open class StructuredTrayMenuFX<T : Pane>() : TrayMenuFX<T>(),
    FXStructuredTrayMenu<T> {

    override var menuStructureBuilder: FXMenuStructureBuilder? = ListStructureBuilderFX()
    var stage: Stage? = null
    var showAppOnDoubleClick: Boolean = true

    init {
        Platform.setImplicitExit(true) // prevent from killing the app if no stage is visible
    }

    constructor(menu: T, stage: Stage) : this() {
        this.stage = stage
        doubleClickAction {
            if (showAppOnDoubleClick)
                Platform.runLater {
                    stage.show()
                    stage.toFront()
                    stage.isIconified = false
                }
        }
        setMenuContent(menu)
    }

    constructor(fxmlLoader: FXMLLoader, stage: Stage) : this(fxmlLoader.load<T>(), stage)

    constructor(menu: T) : this() {
        this.setMenuContent(menu)
    }

    override fun addElement(element: Node, action: FXAction) {
        element.setOnAction { action.doWork() }
        menuStructureBuilder?.add(element)
    }

    override fun addElement(index: Int, element: Node, action: FXAction) {
        element.setOnAction { action.doWork() }
        menuStructureBuilder?.add(index, element)
    }

    override fun addOpenAppElement(element: Node) {
        element.setOnAction {
            stage?.show()
            stage?.isIconified = false
            stage?.toFront()
            super.hide()
        }
        menuStructureBuilder?.addTop(element)
    }

    override fun addCloseAppElement(element: Node) {
        element.setOnAction {
            super.exit()
        }
        menuStructureBuilder?.addBottom(element)
    }

    final override fun init(image: Image, tooltip: String) = initAndLeaveBuilder { buttonStructureBuilder ->
        super.init(image, buttonStructureBuilder.build(super.getMenuContent()), tooltip)
    }

    final override fun init(image: Image) = initAndLeaveBuilder { buttonStructureBuilder ->
        super.setMenuContent(buttonStructureBuilder.build(super.getMenuContent()))
        super.image = image
        super.init()
    }

    final override fun init(image: URL, tooltip: String) = initAndLeaveBuilder { buttonStructureBuilder ->
        super.setImage(image)
        super.tooltip = tooltip
        super.setMenuContent(buttonStructureBuilder.build(super.getMenuContent()))
        super.init()
    }

    final override fun init() = initAndLeaveBuilder { buttonStructureBuilder ->
        super.setMenuContent(buttonStructureBuilder.build(super.getMenuContent()))
        super.init()
    }

    private fun initAndLeaveBuilder(init: (FXMenuStructureBuilder) -> Unit) {
        val buttonStructureBuilder: FXMenuStructureBuilder =
            menuStructureBuilder ?: throw RuntimeException() // todo
        init(buttonStructureBuilder)
        this.menuStructureBuilder = null
    }

    private fun Node.setOnAction(action: () -> Unit) {
        setOnMouseClicked {
            if (it.button == MouseButton.PRIMARY) {
                action()
            }
        }
    }

}