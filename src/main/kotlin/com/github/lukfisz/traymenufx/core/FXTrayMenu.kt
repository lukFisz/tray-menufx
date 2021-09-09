package com.github.lukfisz.traymenufx.core

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import java.awt.Image
import java.net.URL

interface FXTrayMenu<T : Parent> {
    fun setImage(url: URL)
    fun setMenuContent(parent: T)
    fun setMenuContent(fxmlLoader: FXMLLoader)
    fun remove()
    fun exit()
    fun beforeShowMenuAction(beforeShowMenuAction: FXMouseEventAction)
    fun afterShowMenuAction(afterShowMenuAction: FXMouseEventAction)
    fun beforeHideMenuAction(beforeHideMenuAction: FXFocusAction)
    fun afterHideMenuAction(afterHideMenuAction: FXFocusAction)
    fun leftClickAction(leftClickAction: FXMouseEventAction)
    fun doubleClickAction(doubleClickAction: FXMouseEventAction)
    fun middleClickAction(middleClickAction: FXMouseEventAction)
    fun init(image: Image, parent: T)
    fun init()
    fun init(image: Image, parent: T, tooltip: String)
    fun getMenuContent(): T
    fun hide()
    fun message(text: String)
    fun message(caption: String?, text: String)
    fun info(caption: String?, text: String)
    fun warning(caption: String?, text: String)
    fun error(caption: String?, text: String)
    fun isSupported(): Boolean
    fun info(text: String)
    fun warning(text: String)
    fun error(text: String)
    fun setPositionCalculator(positionCalculator: com.github.lukfisz.traymenufx.core.IPositionCalculator)
}