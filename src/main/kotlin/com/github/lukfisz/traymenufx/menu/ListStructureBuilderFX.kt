package com.github.lukfisz.traymenufx.menu

import javafx.scene.Node
import javafx.scene.layout.Pane

class ListStructureBuilderFX : FXMenuStructureBuilder {

    private val list: MutableList<Node> = mutableListOf()
    private var openAppButton: Node? = null
    private var closeAppButton: Node? = null

    override fun add(element: Node) {
        if (list.size == 0) {
            list.add(element)
            return
        }
        list.add(list.size, element)
    }

    override fun add(index: Int, element: Node) {
        list.add(index, element)
    }

    override fun addTop(element: Node) {
        this.openAppButton = element
    }

    override fun addBottom(element: Node) {
        this.closeAppButton = element
    }

    override fun <T : Pane> build(pane: T): T {
        this.closeAppButton?.apply {
            list.add(this)
        }
        this.openAppButton?.apply {
            list.add(0, this)
        }
        pane.children.addAll(list)
        list.clear()
        return pane
    }

}