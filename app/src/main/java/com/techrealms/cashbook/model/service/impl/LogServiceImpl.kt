package com.techrealms.cashbook.model.service.impl

import com.techrealms.cashbook.model.service.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor():LogService {
    override fun logNonFatalCrash(throwable: Throwable?) {

        if(throwable != null)
            TODO("ADD Firebase Crashlytic")
            return Unit;
            //Firebase.crashltyics.recordException(throwable)
    }
}