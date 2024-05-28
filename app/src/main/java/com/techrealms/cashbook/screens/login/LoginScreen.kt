package com.techrealms.cashbook.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.techrealms.cashbook.common.composable.BasicButton
import com.techrealms.cashbook.common.composable.BasicTextButton
import com.techrealms.cashbook.common.composable.BasicToolbar
import com.techrealms.cashbook.common.composable.EmailField
import com.techrealms.cashbook.common.composable.PasswordField
import com.techrealms.cashbook.common.ext.basicButton
import com.techrealms.cashbook.common.ext.fieldModifier
import com.techrealms.cashbook.common.ext.textButton
import com.techrealms.cashbook.ui.theme.CashBookTheme
import com.techrealms.cashbook.R.string as AppText

@Composable
fun LoginScreen(
                //openAndPopUp: (String,String) -> Unit,
                viewModel: LoginViewModel = hiltViewModel()
                )
{
    val uiState by viewModel.uiState

    LoginScreenContent(uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { },//viewModel.onSignInClick(openAndPopUp)},
        onForgotPasswordClick = viewModel::onForgotPasswordClick
        )
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
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        )
    {
        EmailField(uiState.email, onEmailChange, Modifier.fieldModifier())
        PasswordField(value = uiState.password, onNewValue = onPasswordChange,
            Modifier.fieldModifier())

        BasicButton(AppText.sign_in, Modifier.basicButton()){ onSignInClick() }

        BasicTextButton(text = AppText.forgot_password, modifier = Modifier.textButton()) {
            onForgotPasswordClick()
        }
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