@file:OptIn(ExperimentalMaterial3Api::class)

package com.techrealms.cashbook.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicToolbar(@StringRes title: Int)
{
    TopAppBar(title = { Text(stringResource(title))}, colors = toolbarColor())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionToolbar(modifier: Modifier,
                  @StringRes title: Int,
                  @DrawableRes primaryActionIcon: Int,
                  primaryAction: () -> Unit,
                  @DrawableRes secondaryActionIcon: Int? = null,
                  secondaryAction: (() -> Unit)? = null){
    TopAppBar(
        title = { Text(stringResource(title))},
        colors = toolbarColor(),
        actions = {
            Box(modifier){
              Row(modifier = Modifier.wrapContentSize()){
                  IconButton(onClick = primaryAction) {
                      Icon(painter = painterResource(id = primaryActionIcon), contentDescription = "Primary Action")
                  }

                  if(secondaryAction != null && secondaryActionIcon != null){
                      IconButton(onClick = secondaryAction) {
                          Icon(painter = painterResource(id = secondaryActionIcon), contentDescription = "Secondary Action")
                      }
                  }
              }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()) : TopAppBarColors {
    return if (darkTheme) TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.secondary) else TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary)
}