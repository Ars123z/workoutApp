package com.example.greatworkouts

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.greatworkouts.ui.components.BottomBar
import com.example.greatworkouts.ui.screen.ExerciseList
import com.example.greatworkouts.ui.screen.ExerciseScreen
import com.example.greatworkouts.ui.screen.Food
import com.example.greatworkouts.ui.screen.Instruction
import com.example.greatworkouts.ui.screen.Plans
import com.example.greatworkouts.ui.screen.Profile
import com.example.greatworkouts.ui.screen.WorkoutCategory
import com.example.greatworkouts.ui.screen.WorkoutMain
import com.example.greatworkouts.ui.screen.Workouts

enum class Screens(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int? = null
) {
    Plan(
        route = "plan",
        title = "Plan",
        icon = R.drawable.plan_grey
    ),
    WorkoutScreen(
        route = "workout_main",
        title = "Workouts",
        icon = R.drawable.dumbell_grey

    ),
    WorkoutCategory(
        route = "workout_category",
        title = "Workout Category",
    ),
    Workout(
        route = "workout",
        title = "Workout"
    ),
    Exercise(
        route = "exercise",
        title = "Exercise"
    ),
    ExerciseList(
        route = "exercise_list",
        title = "Exercise List"
    ),
    Instructions(
        route = "instruction",
        title = "Instruction"
    ),
    Profile(
        route = "profile",
        title = "Profile",
        icon = R.drawable.profile_grey
    ),
    Food(
        route = "food",
        title = "Food",
        icon = R.drawable.restaurant
    );

    fun createRoute(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    companion object {
        fun fromRoute(route: String?): Screens =
            when (route?.substringBefore("/")) {
                Plan.route -> Plan
                WorkoutScreen.route -> WorkoutScreen
                WorkoutCategory.route -> WorkoutCategory
                Workout.route -> Workout
                Exercise.route -> Exercise
                ExerciseList.route -> ExerciseList
                Instructions.route -> Instructions
                Profile.route -> Profile
                Food.route -> Food
                null -> Plan
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }


}

@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GreatWorkouts(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    //Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    //Get the name of the current screen
    val currentScreen = Screens.fromRoute(
        backStackEntry?.destination?.route ?: Screens.Plan.route
    )

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Plan.route,
            modifier = modifier
        ) {
            composable(
                route = Screens.Plan.route,
                enterTransition = {
                    scaleIn(
                        initialScale = 0.5f,
                        animationSpec = tween(800, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(800))
                },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                Plans()
            }

            composable(
                route = Screens.WorkoutScreen.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Enters from the right
                        animationSpec = tween(
                            700,
                            easing = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
                        ) // Custom bounce motion
                    ) + scaleIn(
                        initialScale = 0.8f,
                        animationSpec = tween(800, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Moves out to the left
                        animationSpec = tween(700, easing = FastOutSlowInEasing)
                    ) + scaleOut(
                        targetScale = 1.2f,
                        animationSpec = tween(600)
                    ) + fadeOut(animationSpec = tween(500))
                }
            ) {
                WorkoutMain(
                    goToCategory = {
                        navController.navigate(Screens.WorkoutCategory.route)
                    },
                    goToMenu = {
                        navController.navigate(Screens.ExerciseList.route)
                    }
                )
            }

            composable(
                route = Screens.WorkoutCategory.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Slide from right
                        animationSpec = tween(700)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Slide to left
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            ) {
                WorkoutCategory(
                    title = "Strength",
                    description = "In the quiet embrace of twilight, shadows danced across the forgotten garden, where ancient roses intertwined with weathered stone pathways. Whispers of forgotten memories echoed through the leaves, carrying secrets from generations past. The wind rustled softly, revealing",
                    imagePath = "workout_main/torso_clean.png",
                    goToWorkout = { name ->
                        navController.navigate(
                            Screens.Workout.createRoute(
                                name
                            )
                        )
                    }
                )
            }

            composable(
                route = Screens.Workout.route + "/{name}",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Slide from right
                        animationSpec = tween(700)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Slide to left
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            ) {
                Workouts(
                    name = it.arguments?.getString("name"),
                    goToExerciseScreen = { name -> navController.navigate(Screens.Exercise.route + "/$name") }
                )
            }

            composable(
                route = Screens.Exercise.route + "/{name}",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Slide from right
                        animationSpec = tween(700)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Slide to left
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            ) {
                ExerciseScreen(
                    name = it.arguments?.getString("name"),
                    onExerciseCompleted = { navController.popBackStack() },
                    goToInstructions = { name -> navController.navigate(Screens.Instructions.route + "/$name") },
                    goToWorkout = { navController.popBackStack() }
                )
            }

            composable(
                route = Screens.Instructions.route + "/{name}",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Slide from right
                        animationSpec = tween(700)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Slide to left
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            ) {
                Instruction(
                    exerciseName = it.arguments?.getString("name")!!
                )
            }

            composable(
                route = Screens.ExerciseList.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Slide from right
                        animationSpec = tween(700)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Slide to left
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            ) {
                ExerciseList()
            }

            composable(
                route = Screens.Profile.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Slide from right
                        animationSpec = tween(700)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Slide to left
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            ) {
                Profile(
                    navigate = { navController.navigate(Screens.Plan.route) }
                )
            }
            composable(
                route = Screens.Food.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it }, // Slide from right
                        animationSpec = tween(700)
                    ) + fadeIn(animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it }, // Slide to left
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            ) {
                Food()
            }
        }
    }
}


