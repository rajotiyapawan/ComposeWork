package com.til.mb.sitevisit_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

/**
 * Created by Pawan Rajotiya on 06-03-2025.
 */
class SiteVisitFlowViewModel: ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SiteVisitFlowViewModel()
            }
        }
    }
}