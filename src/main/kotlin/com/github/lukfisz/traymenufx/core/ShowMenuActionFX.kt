package com.github.lukfisz.traymenufx.core

import java.awt.Dimension
import java.awt.event.MouseEvent

internal class ShowMenuActionFX(
    override var beforeShowMenuAction: FXMouseEventAction? = null,
    override var afterShowMenuAction: FXMouseEventAction? = null
) : FXShowMenuAction {

    override var positionCalculator: IPositionCalculator =
        PositionCalculator()

    override fun doWork(e: MouseEvent?) {
        beforeShowMenuAction?.doWork(e)
        TrayMenuItemsFX.jfxPanel.isVisible = true
        TrayMenuItemsFX.jFrame.location = positionCalculator.calculate(
            TrayMenuItemsFX.jfxPanel.scene.width,
            TrayMenuItemsFX.jfxPanel.scene.height
        )
        TrayMenuItemsFX.jFrame.preferredSize = Dimension(
            TrayMenuItemsFX.jfxPanel.scene.width.toInt(),
            TrayMenuItemsFX.jfxPanel.scene.height.toInt()
        )
        TrayMenuItemsFX.jFrame.isVisible = true
        TrayMenuItemsFX.jFrame.pack()
        afterShowMenuAction?.doWork(e)
    }

}