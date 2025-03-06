package com.example.greatworkouts.ui.screen.main.exerciselist

import androidx.lifecycle.ViewModel
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.repositories.ExerciseRepository
import com.example.greatworkouts.repositories.ToolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class ExerciseListViewModel(
    val exerciseRepository: ExerciseRepository,
    val toolRepository: ToolRepository) : ViewModel()  {

        fun getExerciseByCategoryToolAndStanding(
            toolName: String,
            categoryName: String,
            standing: Boolean
        ): Flow<List<Exercise>> {
            val exerciseList = exerciseRepository.getExerciseByCategory(categoryName)
            return exerciseList.map { exercises ->
                exercises.filter { exercise ->
                    exercise.tool == toolName && exercise.standing == standing
                }
            }
        }
}