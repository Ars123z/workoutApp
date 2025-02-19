package com.example.greatworkouts.data

import androidx.collection.mutableIntListOf
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "workouts")
data class Workout(
    val coverImage: String,
    @PrimaryKey val name: String,
    val category: String,
    val pro: Boolean,
    val subCategory: String?,
    val description: String,
)

@Dao
interface WorkoutDao {

    @Transaction
    @Query("SELECT * FROM workouts WHERE name = :workoutName")
    fun getWorkoutByName(workoutName: String): Flow<Workout>

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): Flow<List<Workout>>


}