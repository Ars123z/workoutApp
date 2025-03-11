package com.example.greatworkouts.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.example.greatworkouts.data.ExerciseCategoryCrossRef
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "exercises",
    foreignKeys = [ForeignKey(
        entity = Tool::class,
        parentColumns = ["name"],
        childColumns = ["tool"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Exercise(
    @PrimaryKey val name: String,
    val hints: String,
    val breathing: String,
    val videoPath: String,
    val muscleFocus: String,
    val quietWorkout: Boolean,
    val duration: Int,
    val tool: String,
    val standing: Boolean,
    val difficulty: String,
    val calories60: Int,
    val thumbnail: String
)

@Entity(
    tableName = "exercise_category_cross_ref",
    primaryKeys = ["categoryName", "exerciseName"]
)
data class ExerciseCategoryCrossRef(
    val categoryName: String,
    val exerciseName: String
)

@Entity(
    tableName = "exercise_workout_cross_ref",
    primaryKeys = ["exerciseName", "workoutName"]
)
data class ExerciseWorkoutCrossRef(
    val exerciseName: String,
    val workoutName: String
)

data class ExerciseWithCategories(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "name",
        entityColumn = "name",
        associateBy = Junction(
            value = ExerciseCategoryCrossRef::class,
            parentColumn = "categoryName",
            entityColumn = "exerciseName"
        )
    )
    val categories: List<Category>
)

data class ExerciseWithTool(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "tool",
        entityColumn = "name"
    )
    val tool: Tool?
)

data class ExerciseWithWorkouts(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "name",
        entityColumn = "name",
        associateBy = Junction(
            value = ExerciseWorkoutCrossRef::class,
            parentColumn = "exerciseName",
            entityColumn = "workoutName"
        )
    )
    val workouts: List<Workout>
)

data class WorkoutWithExercises(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "name",
        entityColumn = "name",
        associateBy = Junction(
            value = ExerciseWorkoutCrossRef::class,
            parentColumn = "workoutName",
            entityColumn = "exerciseName"
        )
    )
    val exercises: List<Exercise>
)

data class CategoryWithExercises(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "name",
        entityColumn = "name",
        associateBy = Junction(
            value = ExerciseCategoryCrossRef::class,
            parentColumn = "categoryName",
            entityColumn = "exerciseName"
        )
    )
    val exercises: List<Exercise>
)

data class ToolWithExercises(
    @Embedded val tool: Tool,
    @Relation(
        parentColumn = "name",
        entityColumn = "tool"
    )
    val exercises: List<Exercise>
)

@Dao
interface ExerciseDao {

//    Get all exercises
    @Transaction
    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<Exercise>>

//    Get exercise by name
    @Query("SELECT * FROM exercises WHERE name = :name")
    fun getExerciseByName(name: String): Flow<Exercise>

//    Get exercises by tool name
    @Transaction
    @Query("SELECT * FROM exercises WHERE tool IN (:tool)")
    fun getExercisesByTool(tool: List<String>): Flow<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM exercises WHERE name IN (SELECT exerciseName FROM exercise_category_cross_ref WHERE categoryName IN (:categoryName))")
    fun getExercisesByCategory(categoryName: List<String>): Flow<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM exercises WHERE difficulty IN (:difficulty)")
    fun getExercisesByDifficulty(difficulty: List<String>): Flow<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM exercises WHERE name IN (SELECT exerciseName FROM exercise_workout_cross_ref WHERE workoutName = :workoutName)")
    fun getExercisesByWorkout(workoutName: String): Flow<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM categories WHERE name IN (SELECT categoryName FROM exercise_category_cross_ref WHERE exerciseName = :exerciseName)")
    fun getExerciseCategories(exerciseName: String): Flow<List<Category>>

    @Query("SELECT * FROM exercise_workout_cross_ref")
    fun getAllCrossRefs(): Flow<List<ExerciseWorkoutCrossRef>>
}




