package com.techrealms.cashbook.screens.tasks


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techrealms.cashbook.common.composable.DropdownContextMenu
import com.techrealms.cashbook.common.ext.contextMenu
import com.techrealms.cashbook.common.ext.hasDueDate
import com.techrealms.cashbook.common.ext.hasDueTime
import com.techrealms.cashbook.model.Task
import com.techrealms.cashbook.ui.theme.CashBookTheme
import java.util.Date
import com.techrealms.cashbook.R.drawable as AppIcon

@Composable
@ExperimentalMaterial3Api
fun TaskItem(task: Task,
             options: List<String>,
             onCheckChange: ()-> Unit,
             onActionClick: (String)-> Unit)
{
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.padding(8.dp, 0.dp,8.dp, 8.dp ))
    {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth())
        {
            Checkbox(checked = task.completed,
                onCheckedChange = { onCheckChange() },
                modifier = Modifier.padding(9.dp,0.dp))

            Column(modifier = Modifier.weight(1f)){
                Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                    Text(text = getDueDateAndTime(task), fontSize = 12.sp)
                }
            }

            if(task.flag)
            {
                Icon(painter = painterResource(id = AppIcon.ic_flag),
                    contentDescription = "Flag")
            }

            DropdownContextMenu(options, Modifier.contextMenu(), onActionClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TaskItemPreview(){
    CashBookTheme {
        TaskItem(task = Task(
            "", Date(), "","","","","false", "false",false
        ),
            onActionClick = {},
            onCheckChange ={},
            options = listOf()
        )
    }
}

private fun getDueDateAndTime(task: Task): String {
    val stringBuilder = StringBuilder("")

    if (task.hasDueDate()) {
        stringBuilder.append(task.dueDate)
        stringBuilder.append(" ")
    }

    if (task.hasDueTime()) {
        stringBuilder.append("at ")
        stringBuilder.append(task.dueTime)
    }

    return stringBuilder.toString()
}