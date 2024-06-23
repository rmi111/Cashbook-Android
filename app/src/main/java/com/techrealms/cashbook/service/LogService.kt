package com.techrealms.cashbook.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
