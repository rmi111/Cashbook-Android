package com.techrealms.cashbook.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException
import com.techrealms.cashbook.SPLASH_SCREEN
import com.techrealms.cashbook.TASKS_SCREEN
import com.techrealms.cashbook.model.service.AccountService
import com.techrealms.cashbook.model.service.ConfigurationService
import com.techrealms.cashbook.model.service.LogService
import com.techrealms.cashbook.screens.CashBookViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService): CashBookViewModel(logService)
{
    val showError = mutableStateOf(false)

    init{
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopup: (String, String) -> Unit){
        showError.value = false
        //createAnonymousAccount(openAndPopup)
        if(accountService.hasUser)
            openAndPopup(TASKS_SCREEN, SPLASH_SCREEN)
        else
        {
            createAnonymousAccount(openAndPopup)
        }
    }

    private fun createAnonymousAccount(openAndPopup: (String, String) -> Unit){
        launchCatching(snackbar = false) {
            try{
                accountService.createAnonymousAccount()
            }catch(ex: FirebaseAuthException){
                showError.value = true
                throw ex
            }

            openAndPopup(TASKS_SCREEN, SPLASH_SCREEN)
        }
    }
}