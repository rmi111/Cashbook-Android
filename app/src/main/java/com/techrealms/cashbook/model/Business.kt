package com.techrealms.cashbook.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Business(
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val title: String = "",
    val userId: String = ""
)