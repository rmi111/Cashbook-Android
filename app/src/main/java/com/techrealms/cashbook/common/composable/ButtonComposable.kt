package com.techrealms.cashbook.common.composable

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techrealms.cashbook.R
import com.techrealms.cashbook.ui.theme.CashBookTheme

@Composable
fun BasicTextButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    TextButton(onClick = action, modifier = modifier) { Text(text = stringResource(text)) }
}

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier, //modifier.fillMaxWidth().padding(4.dp).height(48.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(32),
        colors =
        ButtonDefaults.buttonColors(
            containerColor =  MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = stringResource(text), fontSize = 20.sp)
    }

//    OutlinedButton(
//        onClick = { },
//        border = BorderStroke(1.dp, Color.Red),
//        shape = RoundedCornerShape(16), // = 50% percent
//        // or shape = CircleShape
//        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red
//        , containerColor = MaterialTheme.colorScheme.onPrimary)
//    ){
//        Text( text = "Save" )
//    }
}

@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun DialogCancelButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun DefaultPreview() {
    CashBookTheme {
//        BasicTextButton(text = R.string.app_name, modifier = Modifier) {
//
//        }

        BasicButton(text = R.string.app_name, modifier = Modifier, action = {})
    }
}