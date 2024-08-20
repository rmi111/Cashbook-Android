package com.techrealms.cashbook.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.techrealms.cashbook.model.Transaction
import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.TransactionStorageService
import com.techrealms.cashbook.service.trace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TransactionStorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
): TransactionStorageService
{
    override val transaction:(businessId: String, cashbookId: String) -> Flow<List<Transaction>> = { businessDocId, cashbookId ->
        auth.currentUser.flatMapLatest { user ->
            firestore
                .collection("$BUSINESS_COLLECTION")
                .document("$businessDocId")
                .collection(BOOK_COLLECTION)
                .document("$cashbookId")
                .collection("$TRANSACTION_COLLECTION")
                .whereEqualTo(USER_ID_FIELD, user.id)
                .dataObjects()
        }
    }

    override suspend fun getTransaction(businessDocId: String,cashbookId: String,transactionId: String): Transaction? =
        firestore
            .collection("$BUSINESS_COLLECTION")
            .document()
            .collection(BOOK_COLLECTION)
            .document(cashbookId)
            .collection("$TRANSACTION_COLLECTION")
            .document("$transactionId")
            .get()
            .await()
            .toObject()

    override suspend fun save(transaction: Transaction,businessDocId: String,cashbookId: String): String =
        trace(SAVE_TRANSACTION_TRACE)
        {
            val updatedTask = transaction.copy(userId = auth.currentUserId)
            firestore
                .collection(BUSINESS_COLLECTION)
                .document("$businessDocId")
                .collection(BOOK_COLLECTION)
                .document(cashbookId)
                .collection("$TRANSACTION_COLLECTION")
                .add(updatedTask)
                .await()
                .id
        }

    override suspend fun update(transaction: Transaction,businessDocId: String,cashbookId: String): Unit =
        trace(UPDATE_TRANSACTION_TRACE) {
            firestore
                .collection(BUSINESS_COLLECTION)
                .document("$businessDocId")
                .collection(BOOK_COLLECTION)
                .document(cashbookId)
                .collection("$TRANSACTION_COLLECTION")
                .document(transaction.id)
                .set(transaction)
                .await()
        }

    override suspend fun delete(businessDocId: String,cashbookId: String,transactionId: String) {
        firestore
            .collection(BUSINESS_COLLECTION)
            .document("$businessDocId")
            .collection(BOOK_COLLECTION)
            .document(cashbookId)
            .collection("$TRANSACTION_COLLECTION")
            .document(transactionId)
            .delete()
            .await()
    }

    companion object{
        private const val USER_ID_FIELD = "userId"
        private const val BOOK_COLLECTION = "cashbook"
        private const val TRANSACTION_COLLECTION = "transaction"
        private const val BUSINESS_COLLECTION = "business"
        private const val CATEGORY_COLLECTION = "category"
        private const val SAVE_TRANSACTION_TRACE = "saveTask"
        private const val UPDATE_TRANSACTION_TRACE = "updateTask"
    }
}