//package com.techrealms.cashbook.screens.tasks
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.techrealms.cashbook.common.composable.ActionToolbar
//import com.techrealms.cashbook.common.ext.smallSpacer
//import com.techrealms.cashbook.common.ext.toolbarActions
//import com.techrealms.cashbook.model.Task
//import com.techrealms.cashbook.ui.theme.CashBookTheme
//import com.techrealms.cashbook.R.drawable as AppIcon
//import com.techrealms.cashbook.R.string as AppText
//
//@Composable
//@ExperimentalMaterial3Api
//fun TasksScreen(
//    openScreen: (String) -> Unit,
//    viewModel: TasksViewModel = hiltViewModel()
//) {
//    val tasks = viewModel.tasks.collectAsState(emptyList())
//    val options by viewModel.options
//
//    TasksScreenContent(
//        tasks = tasks.value,
//        options = options,
//        onAddClick = viewModel::onAddClick,
//        onStatsClick = viewModel::onStatsClick,
//        onSettingsClick = viewModel::onSettingsClick,
//        onTaskCheckChange = viewModel::onTaskCheckChange,
//        onTaskActionClick = viewModel::onTaskActionClick,
//        openScreen = openScreen
//    )
//
//    LaunchedEffect(viewModel) { viewModel.loadTaskOptions() }
//}
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//@ExperimentalMaterial3Api
//fun TasksScreenContent(
//    modifier: Modifier = Modifier,
//    tasks: List<Task>,
//    options: List<String>,
//    onAddClick: ((String) -> Unit) -> Unit,
//    onStatsClick: ((String) -> Unit) -> Unit,
//    onSettingsClick: ((String) -> Unit) -> Unit,
//    onTaskCheckChange: (Task) -> Unit,
//    onTaskActionClick: ((String) -> Unit, Task, String) -> Unit,
//    openScreen: (String) -> Unit
//) {
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { onAddClick(openScreen) },
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary,
//                modifier = modifier.padding(16.dp)
//            ) {
//                Icon(Icons.Filled.Add, "Add")
//            }
//        }
//    ) { it
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()) {
//            ActionToolbar(
//                title = AppText.tasks,
//                modifier = Modifier.toolbarActions(),
//                primaryActionIcon = AppIcon.ic_stats,
//                primaryAction = { onStatsClick(openScreen) },
//                secondaryActionIcon = AppIcon.ic_settings,
//                secondaryAction = { onSettingsClick(openScreen) }
//            )
//
//            Spacer(modifier = Modifier.smallSpacer())
//
//            LazyColumn {
//                items(tasks, key = { it.id }) { taskItem ->
//                    TaskItem(
//                        task = taskItem,
//                        options = options,
//                        onCheckChange = { onTaskCheckChange(taskItem) },
//                        onActionClick = { action -> onTaskActionClick(openScreen, taskItem, action) }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@ExperimentalMaterial3Api
//@Composable
//fun TasksScreenPreview() {
//    val task = Task(
//        title = "Task title",
//        flag = true,
//        completed = true
//    )
//
//    val options = TaskActionOption.getOptions(hasEditOption = true)
//
//    CashBookTheme {
//        TasksScreenContent(
//            tasks = listOf(task),
//            options = options,
//            onAddClick = { },
//            onStatsClick = { },
//            onSettingsClick = { },
//            onTaskCheckChange = { },
//            onTaskActionClick = { _, _, _ -> },
//            openScreen = { }
//        )
//    }
//}