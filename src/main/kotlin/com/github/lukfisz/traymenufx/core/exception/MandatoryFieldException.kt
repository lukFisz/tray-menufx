package com.github.lukfisz.traymenufx.core.exception

class MandatoryFieldException(fieldName: String) : RuntimeException("$fieldName is null. Initialize it first.")