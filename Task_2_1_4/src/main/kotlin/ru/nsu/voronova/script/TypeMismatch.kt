package ru.nsu.voronova.script

import java.lang.Exception

class TypeMismatch(expectedType: String, actualType: String) :
    Exception("An object of the $expectedType type was expected, but an object of the $actualType type has been loaded from the script")