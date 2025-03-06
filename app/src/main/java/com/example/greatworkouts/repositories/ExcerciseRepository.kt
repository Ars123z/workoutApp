package com.example.greatworkouts.repositories

import android.util.Log
import com.example.greatworkouts.data.Category
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.data.ExerciseDao
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    fun getExerciseByName(name: String): Flow<Exercise>
    fun getExercisesByTools(tool: List<String>): Flow<List<Exercise>>
    fun getExerciseByCategories(category: List<String>): Flow<List<Exercise>>
    fun getExerciseCategories(exerciseName: String): Flow<List<Category>>
    fun getExerciseByDifficulty(difficulty: List<String>): Flow<List<Exercise>>
    fun getExerciseByCategory(category: String): Flow<List<Exercise>>
}

class OfflineExerciseRepository(private val exerciseDao: ExerciseDao) : ExerciseRepository {

    override fun getAllExercises(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    override fun getExerciseByName(name: String): Flow<Exercise> = exerciseDao.getExerciseByName(name)

    override fun getExercisesByTools(tools: List<String>): Flow<List<Exercise>>  {
        val result=exerciseDao.getExercisesByTool(tools)
        return result
    }

    override fun getExerciseByCategories(categories: List<String>): Flow<List<Exercise>> {
        val result = exerciseDao.getExercisesByCategory(categories)
        return result
    }

    override fun getExerciseByDifficulty(difficulty: List<String>): Flow<List<Exercise>> {
        val result =exerciseDao.getExercisesByDifficulty(difficulty)
        return result
    }

    override fun getExerciseCategories(exerciseName: String): Flow<List<Category>> = exerciseDao.getExerciseCategories(exerciseName)

    override fun getExerciseByCategory(category: String): Flow<List<Exercise>> = exerciseDao.getExercisesByCategory(listOf(category))


}

