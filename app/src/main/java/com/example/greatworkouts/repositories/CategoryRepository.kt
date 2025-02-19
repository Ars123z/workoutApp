package com.example.greatworkouts.repositories

import com.example.greatworkouts.data.Category
import com.example.greatworkouts.data.CategoryDao
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
    fun getCategoryByName(name: String): Flow<Category>
}

class OfflineCategoryRepository(private val categoryDao: CategoryDao) : CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()

    override fun getCategoryByName(name: String): Flow<Category> = categoryDao.getCategoryByName(name)
}