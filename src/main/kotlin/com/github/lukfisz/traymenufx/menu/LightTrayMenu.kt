package com.github.lukfisz.traymenufx.menu

import javafx.fxml.FXMLLoader
import javafx.scene.Cursor
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.awt.Image
import java.net.URL

class LightTrayMenu(stage: Stage) : StyledCSSTrayMenuFX<VBox>(stage), FXBasicComponentTrayMenu<VBox> {

    init {
        super.setMenuContent(
            FXMLLoader(
                LightTrayMenu::class.java.getResource("/view/tray-menu.fxml")
            )
        )
        val path =
            LightTrayMenu::class.java.getResource("/view/light-tray-menu.css")?.toString()
            ?: throw Exception("css not found") //todo
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

    override fun addCheckbox(label: String, isSelected: Boolean, action: FXAction) {
        val checkbox = CheckBox().apply { translateX = -22.5; translateY = 1.0; this.isSelected = isSelected; }
        val button = buildButton(label).apply { graphic = checkbox; graphicTextGap = -18.0; }
        button.setOnAction { checkbox.isSelected = !checkbox.isSelected }
        applyRegularStyle(button)
        super.addElement(button, action)
    }

    override fun addButton(label: String, action: FXAction) {
        val button = buildButton(label)
        super.addElement(button, action)
    }

    override fun addButton(index: Int, label: String, action: FXAction) {
        val button = buildButton(label)
        super.addElement(index, button, action)
    }

    override fun addOpenAppButton(label: String) {
        val button = buildButton(label)
        super.addOpenAppElement(button)
    }

    override fun addCloseAppButton(label: String) {
        val button = buildButton(label)
        super.addCloseAppElement(button)
    }

    private fun buildButton(label: String) =
        Button(label).apply {
            prefWidth = Double.MAX_VALUE
            cursor = Cursor.HAND
        }

}