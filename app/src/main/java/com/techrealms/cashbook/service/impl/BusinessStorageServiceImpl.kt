package com.techrealms.cashbook.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.techrealms.cashbook.model.Business
import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.BusinessStorageService
import com.techrealms.cashbook.service.trace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

sealed class Result<out T: Any> {
    data class SUCCESS<out T: Any>(val data: T) : Result<T>()
    data class ERROR(val e: Exception): Result<Nothing>()
    data object LOADING : Result<Nothing>()
}

class BusinessStorageServiceImpl @Inject constructor(
    private val auth: AccountService,
    private val firestore: FirebaseFirestore
): BusinessStorageService
{
    private val collection get() =
        firestore.collection(BUSINESS_COLLECTION).whereEqualTo(USER_ID_FIELD,auth.currentUserId)

    override val business: Flow<List<Business>>
        get() = auth.currentUser.flatMapLatest { user ->
            firestore
                .collection(BUSINESS_COLLECTION)
                .whereEqualTo(USER_ID_FIELD, user.id)
                //.orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                .dataObjects()
        }

    override suspend fun getBusiness(businessId: String): Business? =
        firestore.collection(BUSINESS_COLLECTION).document(businessId).get().await().toObject()

    override suspend fun save(business: Business): String =
        trace(SAVE_BUSINESS_TRACE)
        {
            val updatedBusiness = business.copy(userId = auth.currentUserId)
            firestore.collection(BUSINESS_COLLECTION).add(updatedBusiness).await().id
        }

    override suspend fun update(business: Business): Unit =
        trace(UPDATE_BUSINESS_TRACE)
        {
            firestore
                .collection(BUSINESS_COLLECTION)
                .document(business.id)
                .set(business)
                .await()
        }

    override suspend fun delete(businessId: String) {
        firestore
            .collection(BUSINESS_COLLECTION)
            .document(businessId)
            .delete()
            .await()
    }

   companion object{
       private const val BUSINESS_COLLECTION = "business"
       private const val USER_ID_FIELD = "userId"
       private const val CREATED_AT_FIELD = "createdId"
       private const val SAVE_BUSINESS_TRACE = "trace_save_business"
       private const val UPDATE_BUSINESS_TRACE = "trace_update_business"
   }
}