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

data class Food(
    val name: String,
    val calories: String,
    val cookingTime: String,
    val coverImage: String,
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
        name= "Report"
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
    ),
)

val foodData = listOf(
    Food(
        name= "Baked Parmesan Zucchini chips with black beans",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/baked_parmesan_zucchini_chips_with_black_beans_salsa.png"
    ),
    Food(
        name= "Broccoli and Ricotta Pizza",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/broccoli_and_ricotta_pizza.png"
    ),
    Food(
        name= "Chicken Peas Broccoli Salad Wraps",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/chicken_pea_broccoli_salad_wraps.png"
    ),
    Food(
        name= "Fajita Roast Chicken",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/fajita_roast_chicken.png"
    ),
    Food(
        name= "Instant Pot Minestrone",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/Instant_pot_minestrone.png"
    ),
    Food(
        name= "Kale and Black Bean Salad With Avocado",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/kale_and_black_bean_salad_with_avocado.png"
    ),
    Food(
        name= "Mango Mandarin Smoothie with Turmeric",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/mango_mandarin_smoothie_with_turmeric.png"
    ),
    Food(
        name= "Quick Skillet Peach Cobbler",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/quick_skillet_peach_cobbler.png"
    ),
    Food(
        name= "Spring Beans Salad with Toasted Fennel Vinaigrette",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/spring_bean_salad_with_toasted_fennel_vinaigrette.png"
    ),
    Food(
        name= "Turkey Bolognese with Zucchini Noddles",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/turkey_bolognese_with_zucchini_noodles.png"
    ),
    Food(
        name= "Turkey Barley Soup",
        calories= "696 kcal",
        cookingTime= "30 min",
        coverImage= "food/turkey_barley_soup.png"
    ),

)


