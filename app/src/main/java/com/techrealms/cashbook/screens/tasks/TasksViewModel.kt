package com.techrealms.cashbook.screens.tasks

import androidx.compose.runtime.mutableStateOf
import com.techrealms.cashbook.EDIT_TASK_SCREEN
import com.techrealms.cashbook.SETTINGS_SCREEN
import com.techrealms.cashbook.TASK_ID
import com.techrealms.cashbook.model.Task
import com.techrealms.cashbook.service.ConfigurationService
import com.techrealms.cashbook.service.LogService
import com.techrealms.cashbook.service.StorageService
import com.techrealms.cashbook.screens.CashBookViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(logService: LogService,
                                         private val storageService: StorageService,
                                         private val configurationService: ConfigurationService
): CashBookViewModel(logService)
{
        val options = mutableStateOf<List<String>>(listOf())
        val tasks = storageService.tasks

        fun loadTaskOptions(){
            val hasEditOption = configurationService.isShowTaskEditButtonConfig
            options.value = TaskActionOption.getOptions(hasEditOption)
        }

        fun onTaskCheckChange(task: Task){
            launchCatching {
                storageService.update(task.copy(completed = !task.completed))
            }
        }

        fun onAddClick(openScreen: (String) -> Unit) = openScreen(EDIT_TASK_SCREEN)
        fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

        fun onStatsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

        fun onTaskActionClick(openScreen: (String) -> Unit, task: Task, action: String) {
            when(TaskActionOption.getByTitle(action)){
                TaskActionOption.EditTask -> openScreen("$EDIT_TASK_SCREEN?$TASK_ID={${task.id}} ")
                TaskActionOption.ToggleFlag -> onFlagClick(task)
                TaskActionOption.DeleteTask -> onDeleteTaskClick(task)
            }
        }

        private fun onFlagClick(task: Task){
            launchCatching { storageService.update(task.copy(flag = !task.flag)) }
        }

        private fun onDeleteTaskClick(task: Task){
            launchCatching { storageService.delete(task.id) }
        }
}