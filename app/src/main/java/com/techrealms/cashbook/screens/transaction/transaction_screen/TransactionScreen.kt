package com.techrealms.cashbook.screens.transaction.transaction_screen

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
import com.techrealms.cashbook.model.Transaction
import com.techrealms.cashbook.ui.theme.CashBookTheme
import com.techrealms.cashbook.R.drawable as AppIcon
import com.techrealms.cashbook.R.string as AppText

@Composable
@ExperimentalMaterial3Api
fun TransactionScreen(
    openScreen: (String) -> Unit,
    viewModel: TransactionScreenViewModel = hiltViewModel()
)
{
    val transaction = viewModel.transaction.collectAsState(emptyList())

    TransactionScreenContent(transaction = transaction.value,
        openScreen = openScreen,
        onAddClicked = viewModel::onAddClick)
}

@Composable
@ExperimentalMaterial3Api
fun TransactionScreenContent(transaction: List<Transaction>,
                          modifier: Modifier = Modifier,
                          onAddClicked: ((String)->(Unit))-> Unit,
                          openScreen: (String) -> Unit)
{
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAddClicked(openScreen)
            },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier.padding(8.dp))
            {
                Icon(Icons.Filled.Add, "Add")
            }
        }){ it
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight())
        {
            ActionToolbar(
                modifier = Modifier.toolbarActions(),
                title = AppText.title,
                primaryActionIcon = AppIcon.ic_check,
                primaryAction = { /*TODO*/ })

            Spacer(modifier = Modifier.smallSpacer())

            LazyColumn{
                items(transaction, key = {it.id}){transactionItem ->
                    TransactionItem(transaction = transactionItem,
                        onTransactionClick = { function: (String) -> Unit, transaction: Transaction -> },
                        openScreen = openScreen)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CashbookScreenPreview(){
    CashBookTheme {
        TransactionScreen(openScreen = {})
    }
}