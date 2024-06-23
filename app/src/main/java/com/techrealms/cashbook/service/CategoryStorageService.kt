package com.techrealms.cashbook.service

import com.techrealms.cashbook.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryStorageService {
    val categories: Flow<List<Category>>

    suspend fun getCategory(taskId: String): Category?
    suspend fun save(category: Category): String
    suspend fun update(category:  Category)
    suspend fun delete(categoryId: String)
}