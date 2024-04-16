package com.techrealms.cashbook.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable?)
}
