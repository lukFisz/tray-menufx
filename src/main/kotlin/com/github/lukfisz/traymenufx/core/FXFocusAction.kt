package com.github.lukfisz.traymenufx.core

import java.awt.event.FocusEvent

@FunctionalInterface
fun interface FXFocusAction {
    fun doWork(e: FocusEvent?): Unit
}