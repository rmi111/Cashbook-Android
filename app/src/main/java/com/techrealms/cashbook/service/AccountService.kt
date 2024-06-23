package com.techrealms.cashbook.service

import com.techrealms.cashbook.model.User
import kotlinx.coroutines.flow.Flow


interface AccountService
{
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String) //, onResult: (Throwable?) -> Unit)

    suspend fun sendRecoveryEmail(email: String) //, onResult: (Throwable?) -> Unit)

    suspend fun createAnonymousAccount()

    suspend fun linkAccount(email: String, password: String) //, onResult: (Throwable?) -> Unit)

    suspend fun deleteAccount()

    suspend fun signOut()
}