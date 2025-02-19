package com.example.greatworkouts.repositories

import com.example.greatworkouts.data.Tool
import com.example.greatworkouts.data.ToolDao
import kotlinx.coroutines.flow.Flow

interface ToolRepository {
    fun getAllTools(): Flow<List<Tool>>
    fun getToolByName(name: String): Flow<Tool>
}

class OfflineToolRepository(private val toolDao: ToolDao): ToolRepository {

    override fun getAllTools(): Flow<List<Tool>> = toolDao.getAllTools()
    override fun getToolByName(name: String): Flow<Tool> = toolDao.getToolByName(name)

}