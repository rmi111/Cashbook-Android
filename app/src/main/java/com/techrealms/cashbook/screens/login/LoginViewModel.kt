package com.techrealms.cashbook.screens.login

import androidx.compose.runtime.mutableStateOf
import com.techrealms.cashbook.LOGIN_SCREEN
import com.techrealms.cashbook.SETTINGS_SCREEN
import com.techrealms.cashbook.common.snackbar.SnackbarManager
import com.techrealms.cashbook.model.service.AccountService
import com.techrealms.cashbook.model.service.LogService
import com.techrealms.cashbook.screens.CashBookViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import isValidEmail
import javax.inject.Inject
import com.techrealms.cashbook.R.string as AppText


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    private val logService: LogService):
    CashBookViewModel(logService)
{
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String){
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String){
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String,String)-> Unit){
        if(!email.isValidEmail())
        {
            SnackbarManager.showMessage(AppText.app_name)
            return
        }

        if(password.isBlank())
        {
            SnackbarManager.showMessage(AppText.app_name)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
        }
    }

    fun onForgotPasswordClick(){
        if(!email.isValidEmail())
        {
            SnackbarManager.showMessage(AppText.app_name)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.app_name)
        }
    }
}