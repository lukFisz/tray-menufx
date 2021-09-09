package com.github.lukfisz.traymenufx.core

import javafx.scene.input.MouseButton
import java.awt.event.MouseEvent
import javax.swing.SwingUtilities

internal class MouseListenerFX(
    override val rightClickAction: FXShowMenuAction,
    override var leftClickAction: FXMouseEventAction? = null,
    override var middleClickAction: FXMouseEventAction? = null,
    override var doubleClickAction: FXMouseEventAction? = null
) : FXMouseListener {
    override fun mouseClicked(e: MouseEvent?) = SwingUtilities.invokeLater {
        if (e?.button == MouseButton.PRIMARY.ordinal && e.clickCount >= 2) {
            doubleClickAction?.doWork(e)
        }
        when (e?.button) {
            MouseButton.PRIMARY.ordinal -> leftClickAction?.doWork(e)
            MouseButton.SECONDARY.ordinal -> rightClickAction.doWork(e)
            MouseButton.MIDDLE.ordinal -> middleClickAction?.doWork(e)
            else -> Unit
        }
        e?.consume()
    }

    override fun mousePressed(e: MouseEvent?) {}
    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
}