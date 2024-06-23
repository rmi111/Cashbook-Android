package com.techrealms.cashbook.model

import com.google.firebase.firestore.DocumentId


enum class TransactionType{
    IN,
    OUT
}

data class Transaction (
    @DocumentId val id: String = "",
    val category: Category,
    val remarks: String = "",
    val amount: Double = 0.0,
    val userId: String = "",
    val transactionType: TransactionType
)
