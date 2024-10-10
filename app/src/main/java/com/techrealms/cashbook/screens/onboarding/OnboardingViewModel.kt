package com.techrealms.cashbook.screens.onboarding

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException
import com.techrealms.cashbook.BUSINESS_SCREEN
import com.techrealms.cashbook.LOGIN_SCREEN
import com.techrealms.cashbook.ONBOARDING_SCREEN
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.ConfigurationService
import com.techrealms.cashbook.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(configurationService: ConfigurationService,
                                          private val accountService: AccountService,
                                          logService: LogService
): CashBookViewModel(logService)
{
    val showError = mutableStateOf(false)

    init{
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopup: (String, String) -> Unit){
        showError.value = false
        //createAnonymousAccount(openAndPopup)
        openAndPopup(LOGIN_SCREEN, ONBOARDING_SCREEN)
//        if(accountService.hasUser)
//        {
//            openAndPopup(BUSINESS_SCREEN, ONBOARDING_SCREEN)
//        }
//        else
//        {
//            createAnonymousAccount(openAndPopup)
//        }
    }

    private fun createAnonymousAccount(openAndPopup: (String, String) -> Unit){
        launchCatching(snackbar = false) {
            try{
                accountService.createAnonymousAccount()
            }catch(ex: FirebaseAuthException){
                showError.value = true
                throw ex
            }

            openAndPopup(BUSINESS_SCREEN, ONBOARDING_SCREEN)
        }
    }
}