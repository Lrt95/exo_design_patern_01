package org.example.webspark.models

abstract class Thing {
    enum class State {
        REACHABLE,
        UNREACHABLE
    }

    var name = ""
    var state = State.REACHABLE

    abstract fun getTypeName(): String
    abstract fun getDescription(): String

}