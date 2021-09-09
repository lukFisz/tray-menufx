package com.github.lukfisz.traymenufx.core

import java.awt.Point

interface IPositionCalculator {
    fun calculate(windowWidth: Double, windowHeight: Double): Point
}