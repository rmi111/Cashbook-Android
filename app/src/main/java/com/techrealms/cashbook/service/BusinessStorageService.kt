package com.techrealms.cashbook.service

import com.techrealms.cashbook.model.Business
import kotlinx.coroutines.flow.Flow

interface BusinessStorageService
{
    val business: Flow<List<Business>>
    suspend fun getBusiness(businessId: String): Business?
    suspend fun save(business: Business): String
    suspend fun update(business: Business)
    suspend fun delete(businessId: String)
}