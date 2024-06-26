//package com.techrealms.cashbook

import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.techrealms.cashbook.CashBookAppState
import com.techrealms.cashbook.EDIT_TASK_SCREEN
import com.techrealms.cashbook.LOGIN_SCREEN
import com.techrealms.cashbook.SETTINGS_SCREEN
import com.techrealms.cashbook.SIGN_UP_SCREEN
import com.techrealms.cashbook.SPLASH_SCREEN
import com.techrealms.cashbook.STATS_SCREEN
import com.techrealms.cashbook.TASKS_SCREEN
import com.techrealms.cashbook.TASK_ID
import com.techrealms.cashbook.TASK_ID_ARG
import com.techrealms.cashbook.common.snackbar.SnackbarManager
import com.techrealms.cashbook.screens.edit_task.EditTaskScreen
import com.techrealms.cashbook.screens.login.LoginScreen
import com.techrealms.cashbook.screens.settings.SettingsScreen
import com.techrealms.cashbook.screens.sign_up.SignUpScreen
import com.techrealms.cashbook.screens.splash.SplashScreen
import com.techrealms.cashbook.screens.stats.StatsScreen
import com.techrealms.cashbook.screens.tasks.TasksScreen
import com.techrealms.cashbook.ui.theme.CashBookTheme
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterial3Api
fun CashBookApp(){
    CashBookTheme {
        Surface(color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()) {
            val appState = rememberAppState()

            Scaffold(snackbarHost = {
                SnackbarHost (hostState = appState.snackbarHost,
                    modifier = Modifier.padding(8.dp),
                    snackbar = {snackbarData ->
                        Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                    }
                )
            }) {innerPadding ->
                NavHost(navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPadding)){
                    cashBookGraph(appState)
                }
            }
        }
    }
}

//fun RequestNotificationPermissionDialog(val permissionState: rememberPermissionState())

@Composable
fun rememberAppState(snackbarHost: SnackbarHostState = remember { SnackbarHostState() },
                     navController: NavHostController = rememberNavController(),
                     snackbarManager: SnackbarManager = SnackbarManager,
                     resources: Resources = resources(),
                     coroutineScope: CoroutineScope = rememberCoroutineScope()
                     ) =
    remember(snackbarHost, navController, snackbarManager, resources, coroutineScope)
{
    CashBookAppState(snackbarHost,navController,snackbarManager, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources{
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterial3Api
fun NavGraphBuilder.cashBookGraph(appState: CashBookAppState){
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopup = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(STATS_SCREEN) {
        StatsScreen()
    }

    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopup = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(TASKS_SCREEN) { TasksScreen(openScreen = { route -> appState.navigate(route) }) }

    composable(
        route = "$EDIT_TASK_SCREEN$TASK_ID_ARG",
        arguments = listOf(navArgument(TASK_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        EditTaskScreen(
            popUpScreen = { appState.popUp() }
        )
    }
}

