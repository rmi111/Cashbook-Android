package com.techrealms.cashbook.common.composable

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techrealms.cashbook.R
import com.techrealms.cashbook.ui.theme.CashBookTheme
import com.techrealms.cashbook.R.drawable as AppIcon
import com.techrealms.cashbook.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicField(@StringRes text: Int,
               value: String,
               onNewValue: (String) -> Unit,
               modifier: Modifier = Modifier){
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = onNewValue,
        placeholder = { Text("")})
}

@Composable
@ExperimentalMaterial3Api
fun BasicTextInputField(placeholder: String,
                        value: String,
                        onNewValue: (String) -> Unit,
                        leadingIcon: Int,
                        trailingIcon: Int)
{
    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            modifier = Modifier
                .defaultMinSize(minHeight = 8.dp)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor = Color(0xFF37ABFF),
                focusedContainerColor = Color(0xFFF5F6F7),
                unfocusedContainerColor =  Color(0xFFF5F6F7),
                disabledContainerColor = Color(0xFFF5F6F7)),
            value = value,
            placeholder = {
                Text(placeholder,
                    fontSize = 16.sp,
                    color = Color(0xFF9BA1A8),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ))
            },
            onValueChange = onNewValue,
            shape = RoundedCornerShape(18.dp),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.padding(start = 8.dp),onClick = {  })
                {
                    Icon(
                        painterResource(leadingIcon),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                if(trailingIcon == -1) {null} else {
                    IconButton(modifier = Modifier.padding(end = 8.dp),onClick = {  })
                    {
                        Icon(
                            painterResource(trailingIcon),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }

            },
        )
    }
}


@Composable
@ExperimentalMaterial3Api
fun BasicTextInputField(value:String)
{
    Column(modifier = Modifier.padding(8.dp)) {
        var textState by remember { mutableStateOf("") }
        val maxLength = 110

        OutlinedTextField(
            modifier = Modifier
                .defaultMinSize(minHeight = 8.dp)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor = Color(0xFF37ABFF),
                focusedContainerColor = Color(0xFFF5F6F7),
                unfocusedContainerColor =  Color(0xFFF5F6F7),
                disabledContainerColor = Color(0xFFF5F6F7)),
            value = textState,
            placeholder = {
                Text("Example",
                    fontSize = 16.sp,
                    color = Color(0xFF9BA1A8),
                    modifier = Modifier.wrapContentSize(unbounded = true),
                    style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ))

            },
            onValueChange = {
                if (it.length <= maxLength) textState = it
            },
            shape = RoundedCornerShape(18.dp),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { textState = "" }) {
                    Icon(
                        painterResource(R.drawable.profile),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            },

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicNumberField(@StringRes text: Int,
               value: String,
               onNewValue: (String) -> Unit,
               modifier: Modifier = Modifier){
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = value,
        onValueChange = onNewValue,
        placeholder = { "0.0" })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(AppText.email)) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(value, AppText.password, onNewValue, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatPasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PasswordField(value, AppText.repeat_password, onNewValue, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
    value: String,
    @StringRes placeholder: Int,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    val icon =
        if (isVisible) painterResource(AppIcon.eye_hidden)
        else painterResource(AppIcon.eye)

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedBorderColor = Color(0xFF37ABFF),
            focusedContainerColor = Color(0xFFF5F6F7),
            unfocusedContainerColor =  Color(0xFFF5F6F7),
            disabledContainerColor = Color(0xFFF5F6F7)),
        value = value,
        shape = RoundedCornerShape(18.dp),
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text = stringResource(placeholder),color = Color(0xFF9BA1A8)) },
        leadingIcon = { IconButton(modifier = Modifier.padding(start = 8.dp),
            onClick = {  })
        {
            Icon(painterResource(R.drawable.lock), contentDescription = "Lock")
        }},
        trailingIcon = {
            IconButton(modifier = Modifier.padding(end = 8.dp),onClick = { isVisible = !isVisible }) {
                Icon(painter = icon, contentDescription = "Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}

@ExperimentalMaterial3Api
@Preview(name = "Light Mode",showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun TextFieldPreview() {
    CashBookTheme {
        BasicTextInputField(value = "Hello",
            placeholder = "",
            onNewValue = {},
            leadingIcon = R.drawable.profile,
            trailingIcon = -1)
    }
}

