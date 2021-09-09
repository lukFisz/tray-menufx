package com.github.lukfisz.traymenufx.menu

import com.github.lukfisz.traymenufx.core.FXTrayMenu
import javafx.scene.Parent

interface FXBasicComponentTrayMenu<T : Parent> : FXStyledCSSTrayMenu, FXTrayMenu<T> {
    fun addButton(label: String, action: FXAction)
    fun addButton(index: Int, label: String, action: FXAction)
    fun addOpenAppButton(label: String)
    fun addCloseAppButton(label: String)
    fun addCheckbox(label: String, isSelected: Boolean, action: FXAction)
}