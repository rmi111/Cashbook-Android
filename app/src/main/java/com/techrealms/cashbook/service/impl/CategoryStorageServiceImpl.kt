package com.techrealms.cashbook.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.techrealms.cashbook.model.Category
import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.CategoryStorageService
import com.techrealms.cashbook.service.trace
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoryStorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService)
    : CategoryStorageService
{
    private val collection
        get() =
            firestore.collection(CATEGORY_COLLECTION).whereEqualTo(USER_ID_FIELD, auth.currentUser)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val categories: Flow<List<Category>>
        get() = auth.currentUser.flatMapLatest { user ->
            firestore.collection(CATEGORY_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id)
                .orderBy(CREATED_AT_FIELD, Query.Direction.ASCENDING)
                .dataObjects()
        }

    override suspend fun getCategory(categoryId: String): Category? =
        firestore.collection(CATEGORY_COLLECTION).document(categoryId).get().await().toObject()

    override suspend fun save(category: Category):String =
        trace(SAVE_CATEGORY_TRACE){
            val updatedCategory = category.copy(userId = auth.currentUserId)
            firestore.collection(CATEGORY_COLLECTION).add(updatedCategory).await().id
        }

    override suspend fun update(category: Category): Unit =
        trace(UPDATED_CATEGORY_TRACE)
        {
            firestore.collection(CATEGORY_COLLECTION).document(category.id).set(category).await()
        }

    override suspend fun delete(categoryId: String) {
        firestore.collection(CATEGORY_COLLECTION).document(categoryId).delete().await()
    }

    companion object {
        private const val CATEGORY_COLLECTION = "category"
        private const val USER_ID_FIELD = "userId"
        private const val CATEGORY_TITLE_FIELD = "categoryTitle"
        private const val CREATED_AT_FIELD = "createdAt"
        private const val UPDATED_CATEGORY_TRACE = "updateCategory"
        private const val SAVE_CATEGORY_TRACE = "saveCategory"
    }
}