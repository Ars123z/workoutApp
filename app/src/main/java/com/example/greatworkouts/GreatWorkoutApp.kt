package com.example.greatworkouts

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.example.greatworkouts.ui.screen.ExerciseList
import com.example.greatworkouts.ui.screen.ExerciseScreen
import com.example.greatworkouts.ui.screen.Instruction
import com.example.greatworkouts.ui.screen.Plans
import com.example.greatworkouts.ui.screen.Profile
import com.example.greatworkouts.ui.screen.WorkoutCategory
import com.example.greatworkouts.ui.screen.WorkoutMain
import com.example.greatworkouts.ui.screen.Workouts

enum class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val Icon: Int? = null
) {
    Plan(
        route = "plan",
        title = "Plan",
        Icon = R.drawable.plan_grey
    ),
    WorkoutScreen(
        route = "workout_main",
        title = "Workout_main",
        Icon = R.drawable.dumbell_grey

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
        "profile",
        title = "Profile",
        Icon = R.drawable.profile_grey
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
        fun fromRoute(route: String?): BottomBarScreen =
            when (route?.substringBefore("/")) {
                Plan.route -> Plan
                WorkoutScreen.route -> WorkoutScreen
                WorkoutCategory.route -> WorkoutCategory
                Workout.route -> Workout
                Exercise.route -> Exercise
                ExerciseList.route -> ExerciseList
                Instructions.route -> Instructions
                Profile.route -> Profile
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
    val currentScreen = BottomBarScreen.fromRoute(
        backStackEntry?.destination?.route ?: BottomBarScreen.Plan.route
    )

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Plan.route,
            modifier = modifier
        ) {
            composable(
                route = BottomBarScreen.Plan.route,
                enterTransition = {
                    scaleIn(
                        initialScale = 0.5f,
                        animationSpec = tween(800, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(800))
                },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                Plans(
                    navigate = { navController.navigate(BottomBarScreen.WorkoutCategory.route) }
                )
            }

            composable(
                route = BottomBarScreen.WorkoutScreen.route,
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
                    navController.navigate(BottomBarScreen.WorkoutCategory.route)
                },
                    goToMenu = {
                        navController.navigate(BottomBarScreen.ExerciseList.route)
                    }
                )
            }

            composable(
                route = BottomBarScreen.WorkoutCategory.route,
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
                            BottomBarScreen.Workout.createRoute(
                                name
                            )
                        )
                    }
                )
            }

            composable(
                route = BottomBarScreen.Workout.route + "/{name}",
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
                    goToExerciseScreen = { name -> navController.navigate(BottomBarScreen.Exercise.route + "/$name") }
                )
            }

            composable(
                route = BottomBarScreen.Exercise.route + "/{name}",
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
                    goToInstructions = { name -> navController.navigate(BottomBarScreen.Instructions.route + "/$name") },
                    goToWorkout = { navController.popBackStack() }
                )
            }

            composable(
                route = BottomBarScreen.Instructions.route + "/{name}",
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
                route = BottomBarScreen.ExerciseList.route,
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
                route = BottomBarScreen.Profile.route,
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
                    navigate = { navController.navigate(BottomBarScreen.Plan.route) }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier.padding(vertical = 0.dp)
) {
    val bottomScreens = listOf(
        BottomBarScreen.Plan,
        BottomBarScreen.WorkoutScreen,
        BottomBarScreen.Profile
    )
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route

    val current: BottomBarScreen = BottomBarScreen.fromRoute(currentScreen)

    if (current == BottomBarScreen.Plan || current == BottomBarScreen.WorkoutScreen || current == BottomBarScreen.Profile) {
        NavigationBar(
            modifier = Modifier.height(50.dp)
                .padding(vertical= 0.dp),
            containerColor = Color.White,
        ) {
            bottomScreens.forEach { screen ->
                NavigationBarItem(
                    modifier = Modifier.padding(vertical= 0.dp)
                        .height(50.dp),
                    icon = {
                        if (screen.Icon != null) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(screen.Icon),
                                    contentDescription = screen.title,
                                    tint = if (currentScreen == screen.route) MaterialTheme.colorScheme.primary else Color.Gray,
                                    modifier = Modifier.size(if (currentScreen == screen.route) 30.dp else 25.dp)
                                        .padding(horizontal = 0.dp)
                                )
                                Text(
                                    text = screen.title,
                                    color = if (currentScreen == screen.route) MaterialTheme.colorScheme.primary else Color.Gray,
                                    style = if (currentScreen == screen.route) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(0.dp)
                                )
                            }
                        }
                    },
                    selected = currentScreen == screen.route,
                    onClick = { navController.navigate(screen.route) },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )

                )
            }
        }
    }
}
