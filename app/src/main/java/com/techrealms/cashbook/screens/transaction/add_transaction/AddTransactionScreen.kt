package com.techrealms.cashbook.screens.transaction.add_transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techrealms.cashbook.R
import com.techrealms.cashbook.common.composable.ActionToolbar
import com.techrealms.cashbook.common.composable.BasicField
import com.techrealms.cashbook.common.composable.BasicNumberField
import com.techrealms.cashbook.common.ext.fieldModifier
import com.techrealms.cashbook.common.ext.spacer
import com.techrealms.cashbook.common.ext.toolbarActions
import com.techrealms.cashbook.model.Category
import com.techrealms.cashbook.model.Transaction
import com.techrealms.cashbook.model.TransactionType
import com.techrealms.cashbook.screens.categories.CategoryItem

@Composable
@ExperimentalMaterial3Api
fun AddTransactionScreen(
    popupScreen: () -> Unit,
    viewModel: AddTransactionViewModel = hiltViewModel()
)
{
    val transaction by viewModel.transaction
    val categories = viewModel.categories.collectAsState(emptyList())

    AddTransactionScreenContent(transaction = transaction,
        categories = categories.value,
        onDoneClick = {viewModel.onDoneClicked(popupScreen)},
        onRemarkChange = viewModel::onRemarkChanged,
        onAmountChange = viewModel::onAmountChanged,
        onCategoryChange = viewModel::onCategoryChanged)
}

@Composable
@ExperimentalMaterial3Api
fun AddTransactionScreenContent(
    modifier: Modifier = Modifier,
    onDoneClick: () -> Unit,
    onRemarkChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onCategoryChange: (Category) -> Unit,
    transaction: Transaction,
    categories: List<Category>
)
{
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

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
        BasicField(R.string.title, transaction.remarks, onRemarkChange, fieldModifier)
        BasicNumberField(R.string.title, transaction.amount.toString(), onAmountChange, fieldModifier)
        //DropdownSelector(label = 0, options = listOf("A","B","C"), selection = "",modifier = Modifier, onNewValue = {})

        Button(
            onClick = { showBottomSheet = true }
        ) {
            Text("Select Category")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp)
                ) {
                    items(categories.count()) { index ->
                        val category = categories[index]

                        CategoryItem(category = category, onCategoryClick = onCategoryChange)
                    }
                }
            }
        }
       // BasicField(R.string.title, transaction.remarks, onRemarkChange, fieldModifier)
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterial3Api
fun AddCashbookScreenPreview()
{
    AddTransactionScreenContent(transaction = Transaction("", Category(),"",0.0,"", TransactionType.IN),
        categories = emptyList(),
        onDoneClick = {},
        onRemarkChange = {},
        onAmountChange = {},
        onCategoryChange = {})
}