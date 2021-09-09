package com.github.lukfisz.traymenufx.core

import java.awt.MouseInfo
import java.awt.Point

class PositionCalculator : IPositionCalculator {

    // todo consider other positions
    override fun calculate(windowWidth: Double, windowHeight: Double): Point {
        val point = Point()
        val x: Int = MouseInfo.getPointerInfo().location.x - windowWidth.toInt()
        val y: Int = MouseInfo.getPointerInfo().location.y - windowHeight.toInt()
        point.x = x
        point.y = y
        return point
    }

}