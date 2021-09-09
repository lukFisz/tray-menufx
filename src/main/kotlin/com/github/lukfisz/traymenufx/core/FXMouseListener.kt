package com.github.lukfisz.traymenufx.core

import java.awt.event.MouseListener

interface FXMouseListener : MouseListener {
    val rightClickAction: FXShowMenuAction
    var leftClickAction: FXMouseEventAction?
    var middleClickAction: FXMouseEventAction?
    var doubleClickAction: FXMouseEventAction?
}