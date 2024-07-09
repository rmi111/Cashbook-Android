package com.techrealms.cashbook.screens.business.add_business

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
import com.techrealms.cashbook.common.composable.ActionToolbar
import com.techrealms.cashbook.common.composable.BasicField
import com.techrealms.cashbook.common.ext.fieldModifier
import com.techrealms.cashbook.common.ext.spacer
import com.techrealms.cashbook.common.ext.toolbarActions
import com.techrealms.cashbook.model.Business
import com.techrealms.cashbook.R.drawable as AppIcon
import com.techrealms.cashbook.R.string as AppText

@Composable
@ExperimentalMaterial3Api
fun AddBusinessScreen(
                      popupScreen: () -> Unit,
                      viewModel: AddBusinessViewModel = hiltViewModel()
                     )
{
    val business by viewModel.business

    AddBusinessScreenContent(business = business,
        onDoneClick = {viewModel.onDoneClicked(popupScreen)},
        onTitleChange = viewModel::onTitleChanged)
}

@Composable
@ExperimentalMaterial3Api
fun AddBusinessScreenContent(
    modifier: Modifier = Modifier,
    onDoneClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    business: Business,
    )
{
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
    )
    {
        ActionToolbar(modifier = Modifier.toolbarActions(),
            title = AppText.edit_task,
            primaryActionIcon = AppIcon.ic_check,
            primaryAction = { onDoneClick() })

        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(AppText.title, business.title, onTitleChange, fieldModifier)
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterial3Api
fun AddBusinessScreenPreview()
{
    AddBusinessScreenContent(business = Business(),
        onDoneClick = {},
        onTitleChange = {})
}