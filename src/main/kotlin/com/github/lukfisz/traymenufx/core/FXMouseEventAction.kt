package com.github.lukfisz.traymenufx.core

import java.awt.event.MouseEvent

@FunctionalInterface
fun interface FXMouseEventAction {
    fun doWork(e: MouseEvent?): Unit
}