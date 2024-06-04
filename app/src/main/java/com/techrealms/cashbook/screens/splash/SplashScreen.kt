package com.techrealms.cashbook.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.techrealms.cashbook.common.composable.BasicButton
import com.techrealms.cashbook.common.ext.basicButton
import com.techrealms.cashbook.ui.theme.CashBookTheme
import kotlinx.coroutines.delay
import com.techrealms.cashbook.R.string as AppText

private const val SPLASH_TIMEOUT = 1000L

@Composable
fun SplashScreen(openAndPopup: (String,String) -> Unit,
                 viewModel: SplashViewModel = hiltViewModel())
{
    SplashScreenContent(onAppStart = { viewModel.onAppStart(openAndPopup) },
        shouldShowError = viewModel.showError.value)
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    onAppStart: () -> Unit,
    shouldShowError:Boolean)
{
    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = MaterialTheme.colorScheme.background)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        if(shouldShowError)
        {
            Text(text = stringResource(id = AppText.generic_error))
            BasicButton(AppText.try_again, Modifier.basicButton()){ onAppStart() }
        }
        else{
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
        }

        LaunchedEffect(true){
            delay(SPLASH_TIMEOUT)
            onAppStart()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenView(){
    CashBookTheme {
        SplashScreenContent(onAppStart = { /*TODO*/ }, shouldShowError = true)
    }
}