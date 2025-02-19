package com.example.greatworkouts.repositories

import android.content.Context
import com.example.greatworkouts.data.GreatWorkoutsDatabase

interface AppContainer {
    val categoryRepository: CategoryRepository
    val exerciseRepository: ExerciseRepository
    val workoutRepository: WorkoutRepository
    val toolRepository: ToolRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val categoryRepository: CategoryRepository by lazy {
        OfflineCategoryRepository(GreatWorkoutsDatabase.getDatabase(context).categoryDao())
    }
    override val exerciseRepository: ExerciseRepository by lazy {
        OfflineExerciseRepository(GreatWorkoutsDatabase.getDatabase(context).exerciseDao())
    }
    override val workoutRepository: WorkoutRepository by lazy {
        OfflineWorkoutRepository(GreatWorkoutsDatabase.getDatabase(context).workoutDao(), GreatWorkoutsDatabase.getDatabase(context).exerciseDao())
    }
    override val toolRepository: ToolRepository by lazy {
        OfflineToolRepository(GreatWorkoutsDatabase.getDatabase(context).toolDao())
    }

}