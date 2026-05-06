package com.rajotiya.mytestapp.base

/**
 * Created by Pawan Rajotiya on 30-01-2026.
 */

class StateHistory<S>(private val maxSize: Int = 50) {

    private val states = mutableListOf<S>()
    private var index = -1

    fun push(state: S) {
        if (states.size == maxSize) states.removeAt(0)
        states.add(state)
        index = states.lastIndex
    }

    fun undo(): S? =
        if (index > 0) states[--index] else null

    fun redo(): S? =
        if (index < states.lastIndex) states[++index] else null
}