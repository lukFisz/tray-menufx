package com.github.lukfisz.traymenufx.menu

import javafx.scene.Node
import javafx.scene.layout.Pane

interface FXMenuStructureBuilder {
    fun add(element: Node)
    fun add(index: Int, element: Node)
    fun addTop(element: Node)
    fun addBottom(element: Node)
    fun <T : Pane> build(pane: T): T
}