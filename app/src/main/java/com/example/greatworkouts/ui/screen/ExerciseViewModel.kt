package com.example.greatworkouts.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greatworkouts.GreatWorkoutsApplication
import com.example.greatworkouts.data.Category
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.data.Tool
import com.example.greatworkouts.repositories.CategoryRepository
import com.example.greatworkouts.repositories.ExerciseRepository
import com.example.greatworkouts.repositories.ToolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ExerciseViewModel(val exerciseRepository: ExerciseRepository, val toolRepository: ToolRepository, val categoryRepository: CategoryRepository) : ViewModel() {
    fun getExerciseCategories(exerciseName: String): Flow<List<Category>> {
        return exerciseRepository.getExerciseCategories(exerciseName)
    }

    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseRepository.getAllExercises()
    }

    fun getExerciseByName(exerciseName: String): Flow<Exercise> {
        return exerciseRepository.getExerciseByName(exerciseName)
    }

    fun getAllCategories(): Flow<List<Category>> {
        return categoryRepository.getAllCategories()
    }

    fun getAllTools(): Flow<List<Tool>> {
        return toolRepository.getAllTools()
    }

    fun getExerciseList(
        searchText: String? = null,
        categoryName: List<String>? = null,
        toolName: List<String>? = null,
        standing: Boolean? = null,
        difficulty: List<String> = listOf("easy", "medium", "hard"),
        noise: Boolean? = null
    ): Flow<List<Exercise>> {
        println("Difficulty: $difficulty")
        val exerciseList: Flow<List<Exercise>> = when {
            toolName != null && categoryName == null -> exerciseRepository.getExercisesByTools(toolName)
            categoryName != null && toolName == null -> exerciseRepository.getExerciseByCategories(categoryName)
            toolName != null && categoryName != null -> {
                val exerciseListByTool: Flow<List<Exercise>> = exerciseRepository.getExercisesByTools(toolName)
                val exerciseListByCategory: Flow<List<Exercise>> = exerciseRepository.getExerciseByCategories(categoryName)
                combine(exerciseListByTool, exerciseListByCategory) { toolList, categoryList ->
                    val combinedList = (toolList + categoryList).distinct()
                    println("Combined List: $combinedList")
                    combinedList
                }
            }
            else -> {
                val list = exerciseRepository.getAllExercises()
                list.onEach { exercises ->
                    println("List: $exercises")
                }
                list
            }
        }

        val exerciseListByDifficulty: Flow<List<Exercise>> = exerciseRepository.getExerciseByDifficulty(difficulty)


        val finalExerciseList: Flow<List<Exercise>> = combine(exerciseList,exerciseListByDifficulty) {
                list1, list2 ->
            println("List1: ${list1.size}")
            println("List2: ${list2.size}")
            val intersectionList = list1.intersect(list2.toSet()).toList()
            println("Intersection List: $intersectionList")
            intersectionList
        }


        return finalExerciseList.map { exercises ->
            exercises.filter { exercise ->
                (searchText == null || exercise.name.contains(searchText, ignoreCase = true)) &&
                        (standing == null || exercise.standing == standing) &&
                        (noise == null || exercise.quietWorkout == noise)
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GreatWorkoutsApplication)
                val exerciseRepository = application.container.exerciseRepository
                val toolRepository = application.container.toolRepository
                val categoryRepository = application.container.categoryRepository
                ExerciseViewModel(exerciseRepository, toolRepository, categoryRepository)
            }
        }
    }
}
