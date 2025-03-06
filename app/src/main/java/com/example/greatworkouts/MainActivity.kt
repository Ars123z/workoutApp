package com.example.greatworkouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.greatworkouts.data.HealthConnectManager
import com.example.greatworkouts.ui.theme.GreatWorkoutsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreatWorkoutsTheme {
                val healthConnectManager = HealthConnectManager(this)
                Scaffold { padding ->
                    GreatWorkouts(
                        healthConnectManager = healthConnectManager,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}





