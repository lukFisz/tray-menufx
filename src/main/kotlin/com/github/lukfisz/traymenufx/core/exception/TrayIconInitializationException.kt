package com.github.lukfisz.traymenufx.core.exception

class TrayIconInitializationException(initialized: Boolean) :
    Exception(if (initialized) "Tray Icon already initialized." else "Initialize Tray Icon first.")