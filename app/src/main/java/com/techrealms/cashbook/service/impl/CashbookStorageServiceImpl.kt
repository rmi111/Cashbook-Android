package com.techrealms.cashbook.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.techrealms.cashbook.model.Cashbook
import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.CashbookStorageService
import com.techrealms.cashbook.service.trace
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CashbookStorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
): CashbookStorageService
{
    private val collection get() =
        firestore.collection(BOOK_COLLECTION).whereEqualTo(USER_ID_FIELD, auth.currentUserId)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val cashbook:Flow<List<Cashbook>>
        get() = auth.currentUser.flatMapLatest { user ->
        firestore
            .collection(BOOK_COLLECTION)
            .whereEqualTo(USER_ID_FIELD, user.id)
            .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
            .dataObjects()
        }

    override suspend fun getCashbook(cashbookId: String): Cashbook? =
        firestore.collection(BOOK_COLLECTION).document(cashbookId).get().await().toObject()

    override suspend fun save(cashbook: Cashbook): String =
        trace(SAVE_TASK_TRACE){
            val updatedTask = cashbook.copy(userId = auth.currentUserId)
            firestore
                .collection(BOOK_COLLECTION)
                .add(updatedTask)
                .await()
                .id
        }

    override suspend fun update(cashbook: Cashbook): Unit =
        trace(UPDATE_TASK_TRACE) {
            firestore
                .collection(BOOK_COLLECTION)
                .document(cashbook.id)
                .set(cashbook)
                .await()
        }

    override suspend fun delete(taskId: String) {
        firestore
            .collection(BOOK_COLLECTION)
            .document(taskId)
            .delete()
            .await()
    }

//    override suspend fun getCompletedTasksCount(): Int {
//        val query = collection.whereEqualTo(COMPLETED_FIELD, true).count()
//        return query.get(AggregateSource.SERVER).await().count.toInt()
//    }
//
//    override suspend fun getImportantCompletedTasksCount(): Int {
//        val query = collection.where(
//            Filter.and(
//                Filter.equalTo(COMPLETED_FIELD, true),
//                Filter.or(
//                    Filter.equalTo(PRIORITY_FIELD, Priority.High.name),
//                    Filter.equalTo(FLAG_FIELD, true)
//                )
//            )
//        )
//
//        return query.count().get(AggregateSource.SERVER).await().count.toInt()
//    }

//    override suspend fun getMediumHighTasksToCompleteCount(): Int {
//        val query = collection
//            .whereEqualTo(COMPLETED_FIELD, false)
//            .whereIn(PRIORITY_FIELD, listOf(Priority.Medium.name, Priority.High.name)).count()
//
//        return query.get(AggregateSource.SERVER).await().count.toInt()
//    }

    companion object{
        private const val USER_ID_FIELD = "userId"
        private const val COMPLETED_FIELD = "completed"
        private const val PRIORITY_FIELD = "priority"
        private const val FLAG_FIELD = "flag"
        private const val CREATED_AT_FIELD = "createdAt"
        private const val BOOK_COLLECTION = "cashbook"
        private const val CATEGORY_COLLECTION = "category"
        private const val SAVE_TASK_TRACE = "saveTask"
        private const val UPDATE_TASK_TRACE = "updateTask"
    }
}