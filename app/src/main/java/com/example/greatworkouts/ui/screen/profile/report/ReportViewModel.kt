package com.example.greatworkouts.ui.screen.profile.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greatworkouts.GreatWorkoutsApplication
import com.example.greatworkouts.data.HealthConnectManager

class ReportViewModel(healthConnectManager: HealthConnectManager): ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GreatWorkoutsApplication)
                val healthConnectManager = application.healthConnectManager
                ReportViewModel(healthConnectManager)
            }
        }
    }
}