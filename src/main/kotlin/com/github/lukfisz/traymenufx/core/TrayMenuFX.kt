package com.github.lukfisz.traymenufx.core

import com.github.lukfisz.traymenufx.core.exception.MandatoryFieldException
import com.github.lukfisz.traymenufx.core.exception.TrayMenuNotSupportedException
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import java.awt.Image
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import java.net.URL
import javax.swing.SwingUtilities

open class TrayMenuFX<T : Parent>() : FXTrayMenu<T> {

    var image: Image? = null
    var tooltip: String? = null
    var isImageAutoSize = true
    private var parent: T? = null
    private var focusListenerFX: FXFocusListener = FocusListenerFX()
    private var mouseListenerFX: FXMouseListener = MouseListenerFX(ShowMenuActionFX())

    constructor(parent: T) : this() {
        setMenuContent(parent)
    }

    final override fun setImage(url: URL) {
        this.image = Toolkit.getDefaultToolkit().getImage(url)
    }

    final override fun setMenuContent(parent: T) {
        this.parent = parent
    }

    final override fun setPositionCalculator(positionCalculator: IPositionCalculator) {
        mouseListenerFX.rightClickAction.positionCalculator = positionCalculator
    }

    override fun setMenuContent(fxmlLoader: FXMLLoader) {
        fxmlLoader.setControllerFactory { classs ->
            classs.declaredFields.forEach {
                if (it.isAnnotationPresent(com.github.lukfisz.traymenufx.core.TrayMenu::class.java)) {
                    val obj = classs.getDeclaredConstructor().newInstance()
                    it.isAccessible = true
                    it.set(obj, this)
                    return@setControllerFactory obj
                }
            }
            return@setControllerFactory classs.getDeclaredConstructor().newInstance()
        }
        this.parent = fxmlLoader.load()
    }

    final override fun getMenuContent(): T {
        return this.parent as T
    }

    final override fun beforeShowMenuAction(beforeShowMenuAction: FXMouseEventAction) {
        mouseListenerFX.rightClickAction.beforeShowMenuAction = beforeShowMenuAction
    }

    final override fun afterShowMenuAction(afterShowMenuAction: FXMouseEventAction) {
        mouseListenerFX.rightClickAction.afterShowMenuAction = afterShowMenuAction
    }

    final override fun beforeHideMenuAction(beforeHideMenuAction: FXFocusAction) {
        focusListenerFX.beforeHideMenuAction = beforeHideMenuAction
    }

    final override fun afterHideMenuAction(afterHideMenuAction: FXFocusAction) {
        focusListenerFX.afterHideMenuAction = afterHideMenuAction
    }

    final override fun leftClickAction(leftClickAction: FXMouseEventAction) {
        mouseListenerFX.leftClickAction = leftClickAction
    }

    final override fun doubleClickAction(doubleClickAction: FXMouseEventAction) {
        mouseListenerFX.doubleClickAction = doubleClickAction
    }

    final override fun middleClickAction(middleClickAction: FXMouseEventAction) {
        mouseListenerFX.middleClickAction = middleClickAction
    }

    override fun init(image: Image, parent: T) = if (isSupported())
        SwingUtilities.invokeLater {
            val tray = SystemTray.getSystemTray()
            TrayMenuItemsFX.setContent(parent)
            TrayMenuItemsFX.init(image, this.tooltip)
            TrayMenuItemsFX.trayIcon?.isImageAutoSize = this.isImageAutoSize
            TrayMenuItemsFX.setFocusListener(focusListenerFX)
            TrayMenuItemsFX.setMouseListener(mouseListenerFX)
            tray.add(TrayMenuItemsFX.trayIcon)
        }
    else throw TrayMenuNotSupportedException()

    override fun init() {
        val image = this.image ?: throw MandatoryFieldException("Image")
        val parent = this.parent ?: throw MandatoryFieldException("MenuContent")
        this.init(image, parent)
    }

    override fun init(image: Image, parent: T, tooltip: String) {
        this.tooltip = tooltip
        this.init(image, parent)
    }

    final override fun remove() = TrayMenuItemsFX.kill()

    final override fun exit() {
        remove()
        Platform.exit()
    }

    final override fun hide() =
        focusListenerFX.focusLost(null)

    override fun message(text: String) {
        this.message(tooltip, text)
    }

    override fun message(caption: String?, text: String) {
        TrayMenuItemsFX.trayIcon?.displayMessage(
            caption,
            text,
            TrayIcon.MessageType.NONE
        )
    }

    override fun info(caption: String?, text: String) {
        TrayMenuItemsFX.trayIcon?.displayMessage(
            caption,
            text,
            TrayIcon.MessageType.INFO
        )
    }

    override fun warning(caption: String?, text: String) {
        TrayMenuItemsFX.trayIcon?.displayMessage(
            caption,
            text,
            TrayIcon.MessageType.WARNING
        )
    }

    override fun error(caption: String?, text: String) {
        TrayMenuItemsFX.trayIcon?.displayMessage(
            caption,
            text,
            TrayIcon.MessageType.ERROR
        )
    }

    override fun info(text: String) {
        this.info(tooltip, text)
    }

    override fun warning(text: String) {
        this.warning(tooltip, text)
    }

    override fun error(text: String) {
        this.error(tooltip, text)
    }

    override fun isSupported() = SystemTray.isSupported()
}

