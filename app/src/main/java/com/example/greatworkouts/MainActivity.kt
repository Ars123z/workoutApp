package com.example.greatworkouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.greatworkouts.ui.theme.GreatWorkoutsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreatWorkoutsTheme {
                Scaffold { padding ->
                    GreatWorkouts(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}





