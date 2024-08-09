package com.techrealms.cashbook.screens.cashbook.add_cashbook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.techrealms.cashbook.R
import com.techrealms.cashbook.common.composable.ActionToolbar
import com.techrealms.cashbook.common.composable.BasicField
import com.techrealms.cashbook.common.ext.fieldModifier
import com.techrealms.cashbook.common.ext.spacer
import com.techrealms.cashbook.common.ext.toolbarActions
import com.techrealms.cashbook.model.Cashbook

@Composable
@ExperimentalMaterial3Api
fun AddCashbookScreen(
    popupScreen: () -> Unit,
    viewModel: AddCashbookViewModel = hiltViewModel()
)
{
    val cashbook by viewModel.cashbook

    AddCashbookScreenContent(cashbook = cashbook,
        onDoneClick = {viewModel.onDoneClicked(popupScreen)},
        onTitleChange = viewModel::onTitleChanged)
}

@Composable
@ExperimentalMaterial3Api
fun AddCashbookScreenContent(
    modifier: Modifier = Modifier,
    onDoneClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    cashbook: Cashbook,
)
{
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
    )
    {
        ActionToolbar(modifier = Modifier.toolbarActions(),
            title = R.string.edit_task,
            primaryActionIcon = R.drawable.ic_check,
            primaryAction = { onDoneClick() })

        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(R.string.title, cashbook.title, onTitleChange, fieldModifier)
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterial3Api
fun AddCashbookScreenPreview()
{
    AddCashbookScreenContent(cashbook = Cashbook(),
        onDoneClick = {},
        onTitleChange = {})
}