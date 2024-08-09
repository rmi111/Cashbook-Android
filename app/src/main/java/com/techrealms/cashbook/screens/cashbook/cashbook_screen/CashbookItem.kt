package com.techrealms.cashbook.screens.cashbook.cashbook_screen;


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
import com.techrealms.cashbook.model.Cashbook
import com.techrealms.cashbook.ui.theme.CashBookTheme

@Composable
@ExperimentalMaterial3Api
fun CashbookItem(cashbook: Cashbook,
                 onCashbookClick: (((String)-> Unit) , Cashbook)-> Unit,
                 openScreen: (String)-> Unit)
{
        Card(
                colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
                        .clickable{
                                onCashbookClick(openScreen,cashbook)
                        }
        )
        {
                Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()){
                        Checkbox(checked = false, onCheckedChange = {})
                        Column(modifier = Modifier.weight(1f)){
                                Text(text = cashbook.title, style = MaterialTheme.typography.titleMedium)
                        }
                }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CashbookItemPreview(){
        CashBookTheme {
                CashbookItem(
                        cashbook = Cashbook(),
                        openScreen = {},
                        onCashbookClick = { function: (String) -> Unit, cashbook: Cashbook -> })
        }
}