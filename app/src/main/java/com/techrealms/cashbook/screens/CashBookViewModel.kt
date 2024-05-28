package com.techrealms.cashbook.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techrealms.cashbook.common.snackbar.SnackbarManager
import com.techrealms.cashbook.common.snackbar.toSnackbarMessage
import com.techrealms.cashbook.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class CashBookViewModel(private val logService: LogService): ViewModel()
{
//    open val showErrorExceptionHandler = CoroutineExceptionHandler {_, throwable ->
//        onError(throwable)
//    }
//
//    open val logErrorExceptionHandler = CoroutineExceptionHandler {_, throwable ->
//        logService.logNonFatalCrash(throwable)
//    }
//
//    open fun onError(error: Throwable){
//        SnackbarManager.showMessage(error.toSnackbarMessage())
//        logService.logNonFatalCrash(error)
//    }

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler {_, throwable ->
                if(snackbar){
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )

}