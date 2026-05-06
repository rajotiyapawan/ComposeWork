package com.rajotiya.mytestapp.base

/**
 * Created by Pawan Rajotiya on 30-01-2026.
 */

interface Middleware<I : UiIntent, S> {
    suspend fun process(
        intent: I,
        state: S,
        next: suspend (I) -> Unit
    )
}