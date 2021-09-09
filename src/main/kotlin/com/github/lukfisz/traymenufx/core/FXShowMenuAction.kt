package com.github.lukfisz.traymenufx.core

interface FXShowMenuAction : FXMouseEventAction {
    var beforeShowMenuAction: FXMouseEventAction?
    var afterShowMenuAction: FXMouseEventAction?
    var positionCalculator: IPositionCalculator
}