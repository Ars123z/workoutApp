package com.example.greatworkouts.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "tools")
data class Tool(
    @PrimaryKey val name: String,
)

@Dao
interface ToolDao {

    @Query("SELECT * FROM tools")
    fun getAllTools(): Flow<List<Tool>>

    @Query("SELECT * FROM tools WHERE name = :name")
    fun getToolByName(name: String): Flow<Tool>
}