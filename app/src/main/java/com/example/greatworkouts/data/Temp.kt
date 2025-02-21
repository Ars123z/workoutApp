package com.example.greatworkouts.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Brightness2
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.DoNotStep
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.PrivateConnectivity
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.ui.graphics.vector.ImageVector

data class WorkoutSample(
    val name: String,
    val tag: List<String>,
    val coverImage: String,
    val duration: String = "5 weeks",
)

data class WorkoutCategory(
    val name: String,
    val description: String,
    val workouts: List<WorkoutSample>
)

data class Settings(
    val icon: ImageVector,
    val name: String,
)

val workoutCategories = listOf(
    WorkoutCategory(
        name = "Gain muscle",
        description = "Focus on muscle tone and strength with plans composed mostly of low repetition strength training.",
        workouts = listOf(
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/bikini_body_special.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/explosive_tone_up.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/fit_&_strong.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
        )
    ),
    WorkoutCategory(
        name = "Lose fat",
        description = "You need to get your heart rate up to burn calories, and that's where fat burning comes in.",
        workouts = listOf(
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/endurance_builder.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/fit_life_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/just_stay_fit.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
        )
    ),
    WorkoutCategory(
        name = "Get fitter",
        description = "Focus on muscle tone and strength with plans composed mostly of low repetition strength training.",
        workouts = listOf(
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/lean_&_toned.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/split_body_toning.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
            WorkoutSample(
                name = "Strength & Resistance Starter",
                tag = listOf("Cardio", "Strength"),
                coverImage = "plans/strength_&_resistance_starter.jpg",
            ),
        )
    )
)

val settingData = listOf(

    Settings(
        icon = Icons.Outlined.Brightness2,
        name= "Sleep"
    ),
    Settings(
        icon = Icons.Outlined.WaterDrop,
        name= "Glucose"
    ),
    Settings(
        icon = Icons.Outlined.Book,
        name= "Recipes"
    ),
    Settings(
        icon = Icons.Outlined.BarChart,
        name= "Progress"
    ),
    Settings(
        icon = Icons.Outlined.Cancel,
        name= "Goal"
    ),
    Settings(
        icon = Icons.Outlined.WaterDrop,
        name= "Nutrition"
    ),
    Settings(
        icon = Icons.Outlined.DoNotStep,
        name= "Steps"
    ),
    Settings(
        icon = Icons.Outlined.PrivateConnectivity,
        name= "Connect Apps"
    ),
    Settings(
        icon = Icons.Outlined.PrivacyTip,
        name= "Privacy Center"
    ),
    Settings(
        icon = Icons.Outlined.Help,
        name= "Help"
    ),
    Settings(
        icon = Icons.Outlined.Settings,
        name= "Settings"
    ),
    Settings(
        icon = Icons.Outlined.Sync,
        name= "Sync"

)
)