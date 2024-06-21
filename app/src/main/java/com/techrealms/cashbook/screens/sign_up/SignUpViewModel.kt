package com.techrealms.cashbook.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.techrealms.cashbook.SETTINGS_SCREEN
import com.techrealms.cashbook.SIGN_UP_SCREEN
import com.techrealms.cashbook.common.snackbar.SnackbarManager
import com.techrealms.cashbook.model.service.AccountService
import com.techrealms.cashbook.model.service.LogService
import com.techrealms.cashbook.screens.CashBookViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import isValidEmail
import isValidPassword
import passwordMatches
import javax.inject.Inject
import com.techrealms.cashbook.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : CashBookViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            accountService.linkAccount(email, password)
            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
        }
    }
}