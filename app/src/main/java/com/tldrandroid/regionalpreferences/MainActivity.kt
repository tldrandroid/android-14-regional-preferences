package com.tldrandroid.regionalpreferences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tldrandroid.regionalpreferences.model.MainUiState
import com.tldrandroid.regionalpreferences.model.MainViewModel
import com.tldrandroid.regionalpreferences.ui.theme.RegionalPreferencesTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by mainViewModel.uiState.collectAsState()

            RegionalPreferencesTheme {
                Main(uiState)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Main(uiState: MainUiState) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Android 14 Regional Preferences") }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {
                DataRow(name = "The temperature is: ", value = uiState.temperature)
                DataRow(
                    name = "The first day of the week is: ",
                    value = uiState.firstDayOfWeek
                )
                DataRow(name = "The time is: ", value = uiState.time)
            }
        }
    }
}

@Composable
fun DataRow(name: String, value: String) {
    Row {
        Text(
            text = name,
            fontWeight = FontWeight.Bold
        )

        Text(text = value)
    }
}
