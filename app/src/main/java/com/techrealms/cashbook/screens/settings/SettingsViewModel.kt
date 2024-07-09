package com.techrealms.cashbook.screens.settings


import com.techrealms.cashbook.LOGIN_SCREEN
import com.techrealms.cashbook.SIGN_UP_SCREEN
import com.techrealms.cashbook.SPLASH_SCREEN
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.CashbookStorageService
import com.techrealms.cashbook.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
    private val storageService: CashbookStorageService
) : CashBookViewModel(logService) {
    val uiState = accountService.currentUser.map { SettingsUiState(it.isAnonymous) }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }

    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.deleteAccount()
            restartApp(SPLASH_SCREEN)
        }
    }
}