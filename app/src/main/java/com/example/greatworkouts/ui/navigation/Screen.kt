package com.example.greatworkouts.ui.navigation

import androidx.annotation.DrawableRes
import com.example.greatworkouts.R

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
    ExerciseByCategory(
        route = "exercise_by_category",
        title = "Exercise By Category"
    ),
    ExerciseByTool(
        route = "exercise_by_tool",
        title = "Exercise By Tool"
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
        route = "Food",
        title = "food",
        icon = R.drawable.restaurant
    ),
    Dashboard(
        route = "dashboard",
        title = "dashboard",
        icon = R.drawable.dashboard
    ),
//    Profile Screens
    Sleep(
        route = "sleep",
        title = "Sleep",
    ),
    Glucose(
        route = "glucose",
        title = "Glucose",
    ),
    Goal(
        route = "goal",
        title = "Goal",
    ),
    Steps(
        route = "steps",
        title = "Steps",
    ),
    Report(
        route = "report",
        title = "Report",
    ),
    Nutrition(
        route = "nutrition",
        title = "Nutrition",
    ),
    PersonalInfo(
        route = "personal_info",
        title = "Personal Info",
    ),
    Help(
        route = "help",
        title = "Help",
    ),
    PrivacyCenter(
        route = "privacy_center",
        title = "Privacy",
    )
    ;

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
                ExerciseByCategory.route -> ExerciseByCategory
                ExerciseByTool.route -> ExerciseByTool
                Instructions.route -> Instructions
                Food.route -> Food
                Dashboard.route -> Dashboard
                Profile.route -> Profile
                Nutrition.route -> Nutrition
                Sleep.route -> Sleep
                Glucose.route -> Glucose
                Goal.route -> Goal
                Steps.route -> Steps
                Report.route -> Report
                PersonalInfo.route -> PersonalInfo
                Help.route -> Help
                PrivacyCenter.route -> PrivacyCenter
                null -> Plan
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}