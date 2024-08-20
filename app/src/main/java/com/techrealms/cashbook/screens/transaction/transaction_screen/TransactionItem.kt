package com.techrealms.cashbook.screens.transaction.transaction_screen;


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techrealms.cashbook.model.Category
import com.techrealms.cashbook.model.Transaction
import com.techrealms.cashbook.model.TransactionType
import com.techrealms.cashbook.ui.theme.CashBookTheme

@Composable
@ExperimentalMaterial3Api
fun TransactionItem(transaction: Transaction,
                 onTransactionClick: (((String)-> Unit) , Transaction)-> Unit,
                 openScreen: (String)-> Unit)
{
        Card(
                colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
                        .clickable{
                                onTransactionClick(openScreen,transaction)
                        }
        )
        {
                Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()){
                        Checkbox(checked = false, onCheckedChange = {})
                        Column(modifier = Modifier.weight(1f)){
                                Text(text = transaction.remarks, style = MaterialTheme.typography.titleMedium)
                        }
                }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CashbookItemPreview(){
        CashBookTheme {
                TransactionItem(
                        transaction = Transaction("", Category(),"",0.0,"",TransactionType.IN),
                        openScreen = {},
                        onTransactionClick = { function: (String) -> Unit, transaction: Transaction -> })
        }
}