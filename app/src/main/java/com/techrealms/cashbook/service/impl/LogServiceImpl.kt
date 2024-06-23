package com.techrealms.cashbook.service.impl

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.techrealms.cashbook.service.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor() : LogService {
    override fun logNonFatalCrash(throwable: Throwable) =
        Firebase.crashlytics.recordException(throwable)
}