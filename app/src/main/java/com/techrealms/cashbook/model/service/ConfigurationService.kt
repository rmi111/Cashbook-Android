package com.techrealms.cashbook.model.service

interface ConfigurationService {
    fun fetchConfiguration()
    fun getShowTaskEditButtonConfig(): Boolean
}