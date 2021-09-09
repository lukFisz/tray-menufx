package com.github.lukfisz.traymenufx.menu

import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Cursor
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.input.MouseButton
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage
import java.awt.Image
import java.net.URL

class StrapTrayMenu(stage: Stage) : StyledCSSTrayMenuFX<VBox>(stage), FXBasicComponentTrayMenu<VBox> {

    init {
        super.setMenuContent(
            FXMLLoader(
                StrapTrayMenu::class.java.getResource("/view/tray-menu.fxml")
            )
        )
        val path =
            StrapTrayMenu::class.java.getResource("/view/strap-tray-menu.css")?.toString()
            ?: throw Exception("css not found") // todo
        super.addStylesheet(path)
    }

    constructor(stage: Stage, image: URL) : this(stage) {
        super.setImage(image)
    }

    constructor(stage: Stage, image: Image) : this(stage) {
        super.image = image
    }

    constructor(stage: Stage, image: Image, tooltip: String) : this(stage) {
        super.image = image
        super.tooltip = tooltip
    }

    constructor(stage: Stage, image: URL, tooltip: String) : this(stage) {
        super.setImage(image)
        super.tooltip = tooltip
    }

    override fun addButton(label: String, action: FXAction) {
        val button = buildButton(label)
        applyRegularStyle(button)
        super.addElement(button, action)
    }

    override fun addCheckbox(label: String, isSelected: Boolean, action: FXAction) {
        val checkbox = CheckBox().apply { translateX = 2.0; translateY = 4.0; this.isSelected = isSelected; }
        val button = buildButton(label)
        button.setOnAction { checkbox.isSelected = !checkbox.isSelected }
        (button.graphic as Pane).apply {
            children.add(checkbox)
            setOnMousePressed { if (it.button == MouseButton.PRIMARY) checkbox.isSelected = !checkbox.isSelected }
        }
        applyRegularStyle(button)
        super.addElement(button, action)
    }

    override fun addButton(index: Int, label: String, action: FXAction) {
        val button = buildButton(label)
        applyRegularStyle(button)
        super.addElement(index, button, action)
    }

    override fun addOpenAppButton(label: String) {
        val button = buildButton(label)
        applyOpenAppStyle(button)
        super.addOpenAppElement(button)
    }

    override fun addCloseAppButton(label: String) {
        val button = buildButton(label)
        applyCloseAppStyle(button)
        super.addCloseAppElement(button)
    }

    private fun buildButton(label: String): Button {
        val button = Button(label).apply { prefWidth = Double.MAX_VALUE; cursor = Cursor.HAND }
        val pane = Pane()
        pane.prefHeight = 24.0
        pane.prefWidth = 22.0
        pane.background = Background(BackgroundFill(Color.rgb(140, 140, 140, 0.36), CornerRadii(0.0), Insets(0.0)))
        button.graphic = pane
        button.graphicTextGap = 6.0
        return button
    }
}