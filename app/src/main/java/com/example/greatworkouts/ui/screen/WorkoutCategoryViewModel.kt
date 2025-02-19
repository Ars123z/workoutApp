package com.example.greatworkouts.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greatworkouts.GreatWorkoutsApplication
import com.example.greatworkouts.data.Category
import com.example.greatworkouts.data.Workout
import com.example.greatworkouts.repositories.CategoryRepository
import com.example.greatworkouts.repositories.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class WorkoutCategoryViewModel(val workoutRepository: WorkoutRepository) : ViewModel() {

    val allWorkouts: Flow<List<Workout>> = workoutRepository.getAllWorkouts()

    fun getWorkoutsByName(name: String): Flow<Workout> {
        return workoutRepository.getWorkoutByName(name)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GreatWorkoutsApplication)
                val workoutRepository = application.container.workoutRepository
                WorkoutCategoryViewModel(workoutRepository = workoutRepository)
            }
        }
    }
}