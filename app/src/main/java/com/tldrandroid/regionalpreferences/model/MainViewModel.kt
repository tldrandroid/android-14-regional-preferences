package com.tldrandroid.regionalpreferences.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val viewModelState = MutableStateFlow(MainUiState())
    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            MainUiState()
        )

    init {
        refreshLocalePreferences()
    }

    fun refreshLocalePreferences() {
        viewModelState.update {
            it.copy(
                firstDayOfWeek = "Sun",
                temperature = "78 F",
                time = "04:00pm"
            )
        }
    }
}

data class MainUiState(
    val firstDayOfWeek: String = "",
    val temperature: String = "",
    val time: String = ""
)
