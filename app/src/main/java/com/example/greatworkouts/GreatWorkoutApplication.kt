package com.example.greatworkouts

import android.app.Application
import com.example.greatworkouts.repositories.AppContainer
import com.example.greatworkouts.repositories.DefaultAppContainer

class GreatWorkoutsApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}