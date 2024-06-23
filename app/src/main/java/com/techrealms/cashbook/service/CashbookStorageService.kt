package com.techrealms.cashbook.service

import com.techrealms.cashbook.model.Cashbook
import kotlinx.coroutines.flow.Flow

interface CashbookStorageService
{
    val cashbook: Flow<List<Cashbook>>

    suspend fun getCashbook(cashbookId: String): Cashbook?
    suspend fun save(cashbook: Cashbook): String
    suspend fun update(cashbook: Cashbook)
    suspend fun delete(cashbookId: String)
//    suspend fun getCompletedTasksCount(): Int
//    suspend fun getImportantCompletedTasksCount(): Int
//    suspend fun getMediumHighTasksToCompleteCount(): Int
}