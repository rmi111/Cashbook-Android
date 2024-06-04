package com.techrealms.cashbook.model.service

interface ConfigurationService {
    suspend fun fetchConfiguration(): Boolean
    val isShowTaskEditButton: Boolean

}