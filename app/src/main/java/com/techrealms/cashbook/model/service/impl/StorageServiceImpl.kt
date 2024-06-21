package com.techrealms.cashbook.model.service.impl

import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.techrealms.cashbook.model.Priority
import com.techrealms.cashbook.model.Task
import com.techrealms.cashbook.model.service.AccountService
import com.techrealms.cashbook.model.service.StorageService
import com.techrealms.cashbook.model.service.trace
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService): StorageService
{

    private val collection get() =
        firestore.collection(TASK_COLLECTION).whereEqualTo(USER_ID_FIELD, auth.currentUserId)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val tasks:Flow<List<Task>>
        get() = auth.currentUser.flatMapLatest { user ->
        firestore.collection(TASK_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id)
            .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
            .dataObjects()
        }

    override suspend fun getTask(taskId: String): Task? =
        firestore.collection(TASK_COLLECTION).document(taskId).get().await().toObject()

    override suspend fun save(task: Task): String =
        trace(SAVE_TASK_TRACE){
            val updatedTask = task.copy(userId = auth.currentUserId)
            firestore.collection(TASK_COLLECTION).add(updatedTask).await().id
        }

    override suspend fun update(task: Task): Unit =
        trace(SAVE_TASK_TRACE) {
            firestore.collection(TASK_COLLECTION).document(task.id).set(task).await()
        }

    override suspend fun delete(taskId: String) {
        firestore.collection(TASK_COLLECTION).document(taskId).delete().await()
    }

    override suspend fun getCompletedTasksCount(): Int {
        val query = collection.whereEqualTo(COMPLETED_FIELD, true).count()
        return query.get(AggregateSource.SERVER).await().count.toInt()
    }

    override suspend fun getImportantCompletedTasksCount(): Int {
        val query = collection.where(
            Filter.and(
                Filter.equalTo(COMPLETED_FIELD, true),
                Filter.or(
                    Filter.equalTo(PRIORITY_FIELD, Priority.High.name),
                    Filter.equalTo(FLAG_FIELD, true)
                )
            )
        )

        return query.count().get(AggregateSource.SERVER).await().count.toInt()
    }

    override suspend fun getMediumHighTasksToCompleteCount(): Int {
        val query = collection
            .whereEqualTo(COMPLETED_FIELD, false)
            .whereIn(PRIORITY_FIELD, listOf(Priority.Medium.name, Priority.High.name)).count()

        return query.get(AggregateSource.SERVER).await().count.toInt()
    }


    companion object{
        private const val USER_ID_FIELD = "userId"
        private const val COMPLETED_FIELD = "completed"
        private const val PRIORITY_FIELD = "priority"
        private const val FLAG_FIELD = "flag"
        private const val CREATED_AT_FIELD = "createdAt"
        private const val TASK_COLLECTION = "tasks"
        private const val SAVE_TASK_TRACE = "saveTask"
        private const val UPDATE_TASK_TRACE = "updateTask"
    }
}