package com.rajotiya.mytestapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Pawan Rajotiya on 29-01-2026.
 */

abstract class BaseViewModelMVI<S, I : UiIntent,E: UiEffect>(initialState: S) : ViewModel() {

    private val history = StateHistory<S>()

    /* -------------------- STATE -------------------- */
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState

    protected fun setState(reducer: S.() -> S) {
        val newState = _uiState.value.reducer()
        _uiState.value = newState
        history.push(newState)
    }

    fun undoState() {
        history.undo()?.let {
            _uiState.value = it
        }
    }

    fun redoState() {
        history.redo()?.let {
            _uiState.value = it
        }
    }

    /* -------------------- EFFECT -------------------- */

    private val _effect = MutableSharedFlow<E>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val effect = _effect.asSharedFlow()

    protected fun sendEffect(builder: () -> E) {
        _effect.tryEmit(builder())
    }

    /* ---------------- INTENT ---------------- */

    private val middlewares = mutableListOf<Middleware<I, S>>()

    protected fun addMiddleware(middleware: Middleware<I, S>) {
        middlewares += middleware
    }

    fun processIntent(intent: I) {
        launch {
            executeMiddlewareChain(0, intent)
        }
    }

    private suspend fun executeMiddlewareChain(
        index: Int,
        intent: I
    ) {
        if (index < middlewares.size) {
            middlewares[index].process(
                intent = intent,
                state = _uiState.value
            ) {
                executeMiddlewareChain(index + 1, it)
            }
        } else {
            handleIntent(intent)
        }
    }

    protected abstract suspend fun handleIntent(intent: I)

    /* -------------------- ERROR HANDLING -------------------- */

    protected open fun onError(throwable: Throwable) {
        // Default global error handling
        throwable.printStackTrace()

        // Override in child VM if needed
        // Example: sendEffect { CommonEffect.ShowSnackBar("Something went wrong") }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    /* -------------------- COROUTINE LAUNCHERS -------------------- */

    protected fun launch(
        dispatcher: CoroutineContext = Dispatchers.Main,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(dispatcher + coroutineExceptionHandler) {
            block()
        }
    }

    protected fun launchIO(
        block: suspend CoroutineScope.() -> Unit
    ) {
        launch(Dispatchers.IO, block)
    }

    protected fun launchMain(
        block: suspend CoroutineScope.() -> Unit
    ) {
        launch(Dispatchers.Main, block)
    }

}
