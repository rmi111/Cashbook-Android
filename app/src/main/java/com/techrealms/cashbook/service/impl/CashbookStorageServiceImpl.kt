package com.techrealms.cashbook.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.techrealms.cashbook.model.Cashbook
import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.CashbookStorageService
import com.techrealms.cashbook.service.trace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CashbookStorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
): CashbookStorageService
{
    override val cashbook:(businessDocId: String) -> Flow<List<Cashbook>> = { businessDocId ->
         auth.currentUser.flatMapLatest { user ->
             firestore
                 .collection("$BUSINESS_COLLECTION")
                 .document("$businessDocId")
                 .collection(BOOK_COLLECTION)
                 .whereEqualTo(USER_ID_FIELD, user.id)
                 .dataObjects()
        }
    }

    override suspend fun getCashbook(businessDocId: String,cashbookId: String): Cashbook? =
        firestore
            .collection("$BUSINESS_COLLECTION/$businessDocId")
            .document()
            .collection(BOOK_COLLECTION)
            .document(cashbookId)
            .get()
            .await()
            .toObject()

    override suspend fun save(cashbook: Cashbook,businessDocId:String): String =
        trace(SAVE_BUSINESS_TRACE)
        {
            val updatedTask = cashbook.copy(userId = auth.currentUserId)
            firestore
                .collection(BUSINESS_COLLECTION)
                .document("$businessDocId")
                .collection(BOOK_COLLECTION)
                .add(updatedTask)
                .await()
                .id
        }

    override suspend fun update(cashbook: Cashbook): Unit =
        trace(UPDATE_BUSINESS_TRACE) {
            firestore
                .collection(BOOK_COLLECTION)
                .document()
                .collection(BOOK_COLLECTION)
                .document(cashbook.id)
                .set(cashbook)
                .await()
        }

    override suspend fun delete(cashbookId: String) {
        firestore
            .collection(BOOK_COLLECTION)
            .document()
            .collection(BOOK_COLLECTION)
            .document(cashbookId)
            .delete()
            .await()
    }

    companion object{
        private const val USER_ID_FIELD = "userId"
        private const val COMPLETED_FIELD = "completed"
        private const val PRIORITY_FIELD = "priority"
        private const val FLAG_FIELD = "flag"
        private const val CREATED_AT_FIELD = "createdAt"
        private const val BOOK_COLLECTION = "cashbook"
        private const val BUSINESS_COLLECTION = "business"
        private const val CATEGORY_COLLECTION = "category"
        private const val SAVE_BUSINESS_TRACE = "saveTask"
        private const val UPDATE_BUSINESS_TRACE = "updateTask"
    }
}