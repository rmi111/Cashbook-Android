package com.techrealms.cashbook

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.techrealms.cashbook.common.snackbar.SnackbarManager
import com.techrealms.cashbook.common.snackbar.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class CashBookAppState(val snackbarHost: SnackbarHostState,
                       val navController: NavHostController,
                       private val snackbarManager: SnackbarManager,
                       private val resources: Resources,
                       coroutineScope: CoroutineScope
    )
{
    init{
        coroutineScope.launch {
            snackbarManager.snackbarMessage.filterNotNull().collect()
            { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                snackbarHost.showSnackbar(text)
            }
        }
    }

    fun popUp(){
        navController.popBackStack()
    }

    fun navigate(route: String){
        navController.navigate(route){
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUp:String){
        navController.navigate(route){
            launchSingleTop = true
            popUpTo(popUp){ inclusive = true }
        }
    }

    fun clearAndNavigate(route: String){
        navController.navigate(route){
            launchSingleTop = true
            popUpTo(0) {inclusive = true}
        }
    }
}