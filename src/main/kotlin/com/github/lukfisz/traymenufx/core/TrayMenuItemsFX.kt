package com.github.lukfisz.traymenufx.core

import com.github.lukfisz.traymenufx.core.exception.TrayIconInitializationException
import javafx.embed.swing.JFXPanel
import javafx.scene.Parent
import javafx.scene.Scene
import java.awt.*
import java.awt.event.FocusListener
import java.util.*
import javax.swing.JFrame

internal object TrayMenuItemsFX {

    internal var trayIcon: TrayIcon? = null
    internal val jFrame: JFrame = JFrame()
    internal val jfxPanel = JFXPanel()

    init {
        jFrame.type = Window.Type.UTILITY
        jFrame.isUndecorated = true
        jFrame.isResizable = false
        jFrame.isAlwaysOnTop = true
    }

    @Throws(TrayIconInitializationException::class)
    fun init(image: Image, tooltip: String?) {
        if (trayIcon != null)
            throw TrayIconInitializationException(true)
        trayIcon = TrayIcon(image, tooltip)
        initContent()
    }

    @Throws(TrayIconInitializationException::class)
    fun init(image: Image) {
        if (trayIcon != null)
            throw TrayIconInitializationException(true)
        trayIcon = TrayIcon(image)
        initContent()
    }

    @Throws(TrayIconInitializationException::class)
    fun setTrayIconImage(image: Image) = throwIfTrayIconNotInitialized {
        it.image = image
    }

    @Throws(TrayIconInitializationException::class)
    fun setTrayIconTooltip(tooltip: String) = throwIfTrayIconNotInitialized {
        it.toolTip = tooltip
    }

    fun setFocusListener(focusListener: FocusListener) {
        jfxPanel.addFocusListener(focusListener)
    }

    @Throws(TrayIconInitializationException::class)
    fun setMouseListener(mouseListenerFX: FXMouseListener) =
        throwIfTrayIconNotInitialized {
            it.addMouseListener(mouseListenerFX)
        }

    fun setContent(parent: Parent) {
        jfxPanel.scene = Scene(parent)
    }

    fun kill() {
        SystemTray.getSystemTray().remove(trayIcon)
        jFrame.dispose()
        jFrame.removeAll()
        jfxPanel.removeAll()
        trayIcon = null
    }

    private fun initContent() {
        jFrame.contentPane.add(jfxPanel)
    }

    @Throws(TrayIconInitializationException::class)
    private fun throwIfTrayIconNotInitialized(func: (trayIcon: TrayIcon) -> Unit) {
        Optional.ofNullable(trayIcon).ifPresentOrElse(
            { func(it) },
            { throw TrayIconInitializationException(false) }
        )
    }

}