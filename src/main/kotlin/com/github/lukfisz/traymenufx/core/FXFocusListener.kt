package com.github.lukfisz.traymenufx.core

import java.awt.event.FocusListener

interface FXFocusListener : FocusListener {
    var beforeHideMenuAction: FXFocusAction?
    var afterHideMenuAction: FXFocusAction?
}