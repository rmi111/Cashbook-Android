package com.techrealms.cashbook.common.snackbar

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SnackbarManager {
    private val message: MutableStateFlow<SnackbarMessage?> = MutableStateFlow(null)
    val snackbarMessage: StateFlow<SnackbarMessage?> get() = message.asStateFlow()

    fun showMessage(@StringRes message: Int){
        this.message.value = SnackbarMessage.ResourceSnackbar(message)
    }

    fun showMessage(message: SnackbarMessage){
        this.message.value = message
    }
}