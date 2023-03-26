package com.tldrandroid.regionalpreferences.model

import androidx.core.text.util.LocalePreferences
import androidx.core.text.util.LocalePreferences.HourCycle
import androidx.core.text.util.LocalePreferences.TemperatureUnit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.Locale

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
        val firstDayOfWeekSelection = LocalePreferences.getFirstDayOfWeek()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

        val firstDayOfWeek = firstDayOfWeekSelection.ifBlank {
            "Sun"
        }

        val temperature = when (LocalePreferences.getTemperatureUnit()) {
            TemperatureUnit.CELSIUS -> "21 C"
            TemperatureUnit.FAHRENHEIT -> "78 F"
            TemperatureUnit.KELVIN -> "299 K"
            else -> "78 F"
        }

        val time = when (LocalePreferences.getHourCycle()) {
            HourCycle.H11 -> "04:00pm"
            HourCycle.H12 -> "04:00pm"
            HourCycle.H23 -> "16:00"
            HourCycle.H24 -> "16:00"
            else -> "04:00pm"
        }

        viewModelState.update {
            it.copy(
                firstDayOfWeek = firstDayOfWeek,
                temperature = temperature,
                time = time
            )
        }
    }
}

data class MainUiState(
    val firstDayOfWeek: String = "",
    val temperature: String = "",
    val time: String = ""
)
