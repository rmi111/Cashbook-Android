package com.techrealms.cashbook.common.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes
import com.techrealms.cashbook.R

sealed class SnackbarMessage {
    class StringSnackBar(val message: String): SnackbarMessage()
    class ResourceSnackbar(@StringRes val message: Int): SnackbarMessage()

    companion object{

    }



}

fun SnackbarMessage.toMessage(resource: Resources): String{
    return when(this){
        is SnackbarMessage.StringSnackBar -> this.message
        is SnackbarMessage.ResourceSnackbar -> resource.getString(this.message)
    }
}

fun Throwable.toSnackbarMessage(): SnackbarMessage{
    val message = this.message.orEmpty()
    return if(message.isNotBlank()) SnackbarMessage.StringSnackBar(message)
    else SnackbarMessage.ResourceSnackbar(R.string.app_name)
}