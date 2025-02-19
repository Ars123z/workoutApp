package com.example.greatworkouts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Exercise::class, Category::class, Tool::class, Workout::class, ExerciseCategoryCrossRef::class, ExerciseWorkoutCrossRef::class],
    version = 1,
    exportSchema = true
)
abstract class GreatWorkoutsDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun toolDao(): ToolDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var Instance: GreatWorkoutsDatabase? = null

        fun getDatabase(context: Context): GreatWorkoutsDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GreatWorkoutsDatabase::class.java,
                    "great_workouts_database"
                )
                    .createFromAsset("database/great_workouts.db")
                    .build()
                    Instance = instance
                    instance
            }
        }
    }
}