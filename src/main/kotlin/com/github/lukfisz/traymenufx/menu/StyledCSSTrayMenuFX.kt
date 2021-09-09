package com.github.lukfisz.traymenufx.menu

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.layout.Pane
import javafx.stage.Stage

open class StyledCSSTrayMenuFX<T : Pane>() : StructuredTrayMenuFX<T>(), FXStyledCSSTrayMenu {

    override var regularStyleClass: String = "traymenu__regular"
    override var openAppStyleClass: String = "traymenu__open-app"
    override var closeAppStyleClass: String = "traymenu__close-app"
    override var styleClassForAll: String = "traymenu__all"

    constructor(loader: FXMLLoader, stage: Stage) : this() {
        this.setMenuContent(loader.load<T>())
        super.stage = stage
    }

    constructor(stage: Stage) : this() {
        super.stage = stage
    }

    override fun addStylesheet(stylesheet: String) = getMenuContent().stylesheets.add(stylesheet)

    override fun addElement(element: Node, action: FXAction) {
        applyStyle(element, regularStyleClass)
        super.addElement(element, action)
    }

    override fun addElement(index: Int, element: Node, action: FXAction) {
        applyStyle(element, regularStyleClass)
        super.addElement(index, element, action)
    }

    override fun addOpenAppElement(element: Node) {
        applyStyle(element, openAppStyleClass)
        super.addOpenAppElement(element)
    }

    override fun addCloseAppElement(element: Node) {
        applyStyle(element, closeAppStyleClass)
        super.addCloseAppElement(element)
    }

    override fun applyStyle(element: Node, styleClass: String) {
        element.styleClass.addAll(styleClass, styleClassForAll)
    }

    override fun applyRegularStyle(element: Node) =
        applyStyle(element, regularStyleClass)

    override fun applyOpenAppStyle(element: Node) =
        applyStyle(element, openAppStyleClass)

    override fun applyCloseAppStyle(element: Node) =
        applyStyle(element, closeAppStyleClass)

}