package com.techrealms.cashbook.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.techrealms.cashbook.common.composable.BasicToolbar
import com.techrealms.cashbook.ui.theme.CashBookTheme
import com.techrealms.cashbook.R.string as AppText

@Composable
fun LoginScreen(
                openAndPopUp: (String,String) -> Unit,
                viewModel: LoginViewModel = hiltViewModel()
                )
{
//    val uiState by viewModel.uiState
//
//    LoginScreenContent()
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
)
{
    BasicToolbar(AppText.login_details)

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState())
        )
    {

    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    val uiState = LoginUiState(email = "email@test.com")

    CashBookTheme {
        LoginScreenContent(
            uiState = uiState,
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {},
            onForgotPasswordClick = {})
    }
}