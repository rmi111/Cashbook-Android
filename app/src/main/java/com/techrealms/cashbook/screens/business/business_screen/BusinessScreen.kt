package com.techrealms.cashbook.screens.business.business_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techrealms.cashbook.common.composable.ActionToolbar
import com.techrealms.cashbook.common.ext.smallSpacer
import com.techrealms.cashbook.common.ext.toolbarActions
import com.techrealms.cashbook.model.Business
import com.techrealms.cashbook.ui.theme.CashBookTheme
import com.techrealms.cashbook.R.drawable as AppIcon
import com.techrealms.cashbook.R.string as AppText

@Composable
@ExperimentalMaterial3Api
fun BusinessScreen(openScreen: (String) -> Unit,
                   viewModel: BusinessScreenViewModel = hiltViewModel())
{
    val business = viewModel.business.collectAsState(emptyList())

    BusinessScreenContent(
        business = business.value,
        onAddClick = viewModel::onAddClick,
        onBusinessClick = viewModel::onBusinessClick,
        openScreen = openScreen
    )

    //LaunchedEffect(viewModel){  }
}

@Composable
@ExperimentalMaterial3Api
fun BusinessScreenContent(
    modifier: Modifier = Modifier,
    business: List<Business>,
    onAddClick: ((String) -> Unit) -> Unit,
    onBusinessClick: ((String) -> Unit, Business) -> Unit,
    openScreen: (String) -> Unit)
{
        Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick(openScreen) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { it
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            ActionToolbar(
                modifier = Modifier.toolbarActions(),
                title = AppText.title,
                primaryActionIcon = AppIcon.ic_stats,
                primaryAction = { /*TODO*/ },
                secondaryAction = {})

            Spacer(modifier = Modifier.smallSpacer())

            LazyColumn{
                items(business, key = {it.id}){businessItem ->
                    BusinessItem(business = businessItem,
                        onBusinessClick = onBusinessClick,
                        openScreen = openScreen)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun TasksScreenPreview() {
    val business = Business(
        title = "Task title",
    )

   // val options = TaskActionOption.getOptions(hasEditOption = true)

    CashBookTheme {
        BusinessScreenContent(
            business = listOf(business),
            onAddClick = { },
            onBusinessClick = {
                function: (String)-> Unit, business ->
            }
        ) { }
    }
}