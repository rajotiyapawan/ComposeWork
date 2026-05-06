package com.rajotiya.mytestapp.base

/**
 * Created by Pawan Rajotiya on 30-01-2026.
 */

/**
 * Marker interface for one-time UI effects
 */
interface UiEffect

sealed interface CommonEffect : UiEffect {
    data class ShowSnackBar(val message: String) : CommonEffect
    object NavigateBack : CommonEffect
}