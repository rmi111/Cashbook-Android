package com.techrealms.cashbook.service

import com.techrealms.cashbook.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionStorageService
{
    val transaction:(businessId: String, cashbookId: String) -> Flow<List<Transaction>>

    suspend fun getTransaction(businessDocId: String,cashbookId: String,transactionId: String): Transaction?
    suspend fun save(transaction: Transaction, businessDocId: String,cashbookId: String): String
    suspend fun update(transaction: Transaction,businessDocId: String,cashbookId: String)
    suspend fun delete(businessDocId: String,cashbookId: String,transactionId: String)
}