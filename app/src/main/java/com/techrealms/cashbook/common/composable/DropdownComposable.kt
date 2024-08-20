package com.techrealms.cashbook.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
@ExperimentalMaterial3Api
fun DropdownContextMenu(options: List<String>,
                        modifier: Modifier,
                        onActionClick: (String) -> Unit)
{
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded},
        modifier = modifier)
    {
        Icon(modifier = Modifier.padding(8.dp, 0.dp),
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More")

        ExposedDropdownMenu(
            modifier = Modifier.width(180.dp),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
            )
        {
            options.forEach{
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                    isExpanded = false
                    onActionClick(it)
                    })
            }
        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun DropdownSelector(@StringRes label: Int,
                     options: List<String>,
                     selection: String,
                     modifier: Modifier,
                     onNewValue: (String) -> Unit)
{
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded},
        modifier = modifier)
    {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly  = true,
            value = selection,
            onValueChange = {},
            label = { Text(stringResource(label)) },
            trailingIcon = { TrailingIcon(isExpanded)},
            colors = dropdownColors()
           )

        ExposedDropdownMenu(
            modifier = Modifier.width(180.dp),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        )
        {
            options.forEach{
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        isExpanded = false
                        onNewValue(it)
                    })
            }
        }
    }
}

@Composable
@ExperimentalMaterial3Api
private fun dropdownColors(): TextFieldColors
{
    return ExposedDropdownMenuDefaults.textFieldColors(
        //conte = MaterialTheme.colorScheme.onPrimary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.primary
    )
}