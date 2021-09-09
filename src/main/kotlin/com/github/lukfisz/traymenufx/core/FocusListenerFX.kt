package com.github.lukfisz.traymenufx.core

import java.awt.event.FocusEvent

internal class FocusListenerFX(
    override var beforeHideMenuAction: FXFocusAction? = null,
    override var afterHideMenuAction: FXFocusAction? = null
) : FXFocusListener {
    override fun focusGained(e: FocusEvent?) {
        TrayMenuItemsFX.jfxPanel.isVisible = true
        TrayMenuItemsFX.jFrame.isVisible = true
    }

    override fun focusLost(e: FocusEvent?) {
        beforeHideMenuAction?.doWork(e)
        TrayMenuItemsFX.jfxPanel.isVisible = false
        TrayMenuItemsFX.jFrame.isVisible = false
        afterHideMenuAction?.doWork(e)
    }
}