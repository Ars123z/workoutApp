package com.example.greatworkouts

import android.app.Application
import com.example.greatworkouts.repositories.AppContainer
import com.example.greatworkouts.repositories.DefaultAppContainer
import com.example.greatworkouts.data.HealthConnectManager

class GreatWorkoutsApplication: Application() {
    lateinit var container: AppContainer
    lateinit var healthConnectManager: HealthConnectManager
    override fun onCreate() {
        super.onCreate()
        healthConnectManager = HealthConnectManager(this)
        container = DefaultAppContainer(this)
    }
}