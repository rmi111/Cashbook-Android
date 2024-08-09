package com.techrealms.cashbook.service

import com.techrealms.cashbook.model.Cashbook
import kotlinx.coroutines.flow.Flow

interface CashbookStorageService
{
    val cashbook:(businessDocId: String) -> Flow<List<Cashbook>>

    suspend fun getCashbook(businessDocId: String, cashbookId: String): Cashbook?
    suspend fun save(cashbook: Cashbook,businessDocId: String): String
    suspend fun update(cashbook: Cashbook)
    suspend fun delete(cashbookId: String)
}