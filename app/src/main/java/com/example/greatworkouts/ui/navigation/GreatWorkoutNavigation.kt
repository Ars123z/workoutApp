package com.example.greatworkouts.ui.navigation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.greatworkouts.data.HealthConnectManager
import com.example.greatworkouts.ui.components.BottomBar
import com.example.greatworkouts.ui.screen.main.dashboard.Dashboard
import com.example.greatworkouts.ui.screen.main.exerciselist.ExerciseList
import com.example.greatworkouts.ui.screen.main.exercise.ExerciseScreen
import com.example.greatworkouts.ui.screen.main.exerciselist.ExerciseByCategoryScreen
import com.example.greatworkouts.ui.screen.main.exerciselist.ExerciseByToolScreen
import com.example.greatworkouts.ui.screen.main.food.Food
import com.example.greatworkouts.ui.screen.main.instruction.Instruction
import com.example.greatworkouts.ui.screen.main.home.Plans
import com.example.greatworkouts.ui.screen.profile.main.Profile
import com.example.greatworkouts.ui.screen.main.workoutcategory.WorkoutCategory
import com.example.greatworkouts.ui.screen.main.workoutmain.WorkoutMain
import com.example.greatworkouts.ui.screen.main.workout.Workouts
import com.example.greatworkouts.ui.screen.profile.glucose.GlucoseScreen
import com.example.greatworkouts.ui.screen.profile.nutrition.NutritionScreen
import com.example.greatworkouts.ui.screen.profile.privacycenter.PrivacyCenterScreen
import com.example.greatworkouts.ui.screen.profile.sleep.SleepScreen
import com.example.greatworkouts.ui.screen.profile.steps.StepsScreen

@Suppress("Un")
@Composable
fun GreatWorkoutNavigation(
    healthConnectManager: HealthConnectManager,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val availability by healthConnectManager.availability
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    //Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    //Get the name of the current screen
    val currentScreen = Screens.fromRoute(
        backStackEntry?.destination?.route ?: Screens.Plan.route
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                Plans(
                    snackbarHostState = snackbarHostState,
                    scope = coroutineScope
                )
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
                    goToWorkout = { navController.popBackStack() },
                    goToExerciseList = { navController.navigate(Screens.ExerciseList.route) }
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
                ExerciseList(
                    goBack = { navController.popBackStack() },
                    goToInstructions = { name -> navController.navigate(Screens.Instructions.route + "/$name") },
                    goToCategory = { category -> navController.navigate(Screens.ExerciseByCategory.route + "/$category") },
                    goToTool = { tool -> navController.navigate(Screens.ExerciseByTool.route + "/$tool") }
                )
            }
            composable(
                route = Screens.ExerciseByCategory.route + "/{category}",
                arguments = listOf(
                    navArgument("category") { type = NavType.StringType }
                ),
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
                ExerciseByCategoryScreen(
                    categoryName = it.arguments?.getString("category")!!,
                    goBack = { navController.popBackStack() },
                    goToInstructions = { name -> navController.navigate(Screens.Instructions.route + "/$name") }
                )
            }
            composable(
                route = Screens.ExerciseByTool.route + "/{tool}",
                arguments = listOf(
                    navArgument("tool") { type = NavType.StringType }
                ),
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
                ExerciseByToolScreen(
                    toolName = it.arguments?.getString("tool")!!,
                    goBack = { navController.popBackStack() },
                    goToInstructions = { name -> navController.navigate(Screens.Instructions.route + "/$name") }
                )
            }
            composable(
                route = Screens.Dashboard.route,
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
                Dashboard(
                goToSleep = { navController.navigate(Screens.Sleep.route) },
                goToGlucose = { navController.navigate(Screens.Glucose.route) },
                goToWorkout = { navController.navigate(Screens.WorkoutScreen.route) },
                goToNutrition = { navController.navigate(Screens.Nutrition.route) },
                goToSteps = { navController.navigate(Screens.Steps.route) },
                goToFood = { navController.navigate(Screens.Food.route) },
                goToBloodPressure = { navController.navigate(Screens.Glucose.route) },
                )
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
                    onLogout = { navController.navigate(Screens.Plan.route) },
                    goToSleep = { navController.navigate(Screens.Sleep.route) },
                    goToGlucose = { navController.navigate(Screens.Glucose.route) },
                    goToNutrition = { navController.navigate(Screens.Nutrition.route) },
                    goToSteps = { navController.navigate(Screens.Steps.route) },
                    goToPrivacyCenter = { navController.navigate(Screens.PrivacyCenter.route) },
                    goToFood = { navController.navigate(Screens.Food.route) }
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
//            Profile Screens
            composable(
                route = Screens.Sleep.route,
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
                SleepScreen(
                    healthConnectAvailability = availability,
                    onResumeAvailabilityCheck = { healthConnectManager.checkAvailability() }
                )
            }
            composable(
                route = Screens.Glucose.route,
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
                GlucoseScreen(
                    healthConnectAvailability = availability,
                    onResumeAvailabilityCheck = { healthConnectManager.checkAvailability() }
                )
            }
            composable(
                route = Screens.Nutrition.route,
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
                NutritionScreen(
                    healthConnectAvailability = availability,
                    onResumeAvailabilityCheck = { healthConnectManager.checkAvailability() }
                )
            }
            composable(
                route = Screens.Steps.route,
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
                StepsScreen(
                    healthConnectAvailability = availability,
                    onResumeAvailabilityCheck = { healthConnectManager.checkAvailability() }
                )
            }
            composable(
                route = Screens.PrivacyCenter.route,
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
                PrivacyCenterScreen(
                    goToTermsOfService = {},
                    goToPrivacyPolicy = {},
                    goBack = { navController.popBackStack() }
                )
            }
        }
    }
}