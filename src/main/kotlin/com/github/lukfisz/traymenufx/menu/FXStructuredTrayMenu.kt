package com.github.lukfisz.traymenufx.menu

import com.github.lukfisz.traymenufx.core.FXTrayMenu
import javafx.scene.Node
import javafx.scene.Parent
import java.awt.Image
import java.net.URL

interface FXStructuredTrayMenu<T : Parent> : FXTrayMenu<T> {
    var menuStructureBuilder: FXMenuStructureBuilder?
    fun addElement(element: Node, action: FXAction)
    fun addElement(index: Int, element: Node, action: FXAction)
    fun addOpenAppElement(element: Node)
    fun addCloseAppElement(element: Node)
    fun init(image: Image, tooltip: String)
    fun init(image: Image)
    fun init(image: URL, tooltip: String)
}