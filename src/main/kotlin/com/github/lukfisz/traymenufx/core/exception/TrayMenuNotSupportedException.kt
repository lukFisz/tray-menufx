package com.github.lukfisz.traymenufx.core.exception

class TrayMenuNotSupportedException :
    Throwable("Tray icon is not suppoerted on current system: ${System.getProperty("os.name")}")
