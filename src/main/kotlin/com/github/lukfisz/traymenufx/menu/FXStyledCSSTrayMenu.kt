package com.github.lukfisz.traymenufx.menu

import javafx.scene.Node

interface FXStyledCSSTrayMenu {
    var regularStyleClass: String
    var openAppStyleClass: String
    var closeAppStyleClass: String
    var styleClassForAll: String
    fun addStylesheet(stylesheet: String): Boolean
    fun applyStyle(element: Node, styleClass: String)
    fun applyRegularStyle(element: Node)
    fun applyOpenAppStyle(element: Node)
    fun applyCloseAppStyle(element: Node)
}