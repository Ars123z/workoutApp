package com.example.greatworkouts

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.greatworkouts.data.HealthConnectManager
import com.example.greatworkouts.ui.navigation.GreatWorkoutNavigation


@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GreatWorkouts(
    healthConnectManager: HealthConnectManager,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    GreatWorkoutNavigation(
        healthConnectManager,
        navController,
        modifier
    )
}


