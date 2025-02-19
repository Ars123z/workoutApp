package com.example.greatworkouts.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greatworkouts.GreatWorkoutsApplication
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.data.ExerciseWorkoutCrossRef
import com.example.greatworkouts.data.Workout
import com.example.greatworkouts.repositories.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class WorkoutViewModel(val workoutRepository: WorkoutRepository) : ViewModel() {

    fun getWorkoutsByName(name: String): Flow<Workout> {
        return workoutRepository.getWorkoutByName(name)
    }
    fun getExercisesByWorkout(name: String): Flow<List<Exercise>> {
        return workoutRepository.getExercisesByWorkout(name)
    }
    fun getAllExercises(): Flow<List<Exercise>> {
        return workoutRepository.getAllExercises()
    }
    fun getAllCrossRefs(): Flow<List<ExerciseWorkoutCrossRef>> {
        return workoutRepository.getAllCrossRefs()
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GreatWorkoutsApplication)
                val workoutRepository = application.container.workoutRepository
                WorkoutViewModel(workoutRepository = workoutRepository)
            }
        }
    }
}