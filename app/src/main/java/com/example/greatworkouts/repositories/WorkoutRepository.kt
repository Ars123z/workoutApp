package com.example.greatworkouts.repositories

import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.data.ExerciseDao
import com.example.greatworkouts.data.ExerciseWithWorkouts
import com.example.greatworkouts.data.ExerciseWorkoutCrossRef
import com.example.greatworkouts.data.Workout
import com.example.greatworkouts.data.WorkoutDao
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
fun getAllWorkouts(): Flow<List<Workout>>
fun getWorkoutByName(name: String): Flow<Workout>
fun getExercisesByWorkout(name: String): Flow<List<Exercise>>
fun getAllExercises(): Flow<List<Exercise>>
fun getAllCrossRefs(): Flow<List<ExerciseWorkoutCrossRef>>

}

class OfflineWorkoutRepository(private val workoutDao: WorkoutDao, private val exerciseDao: ExerciseDao) : WorkoutRepository {

    override fun getAllWorkouts(): Flow<List<Workout>> = workoutDao.getAllWorkouts()

    override fun getWorkoutByName(name: String): Flow<Workout> = workoutDao.getWorkoutByName(name)

    override fun getExercisesByWorkout(name: String): Flow<List<Exercise>> = exerciseDao.getExercisesByWorkout(name)

    override fun getAllExercises(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    override fun getAllCrossRefs(): Flow<List<ExerciseWorkoutCrossRef>> = exerciseDao.getAllCrossRefs()



}