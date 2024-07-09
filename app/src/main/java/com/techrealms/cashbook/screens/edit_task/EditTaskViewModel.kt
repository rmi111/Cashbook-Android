//package com.techrealms.cashbook.screens.edit_task
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.SavedStateHandle
//import com.techrealms.cashbook.TASK_ID
//import com.techrealms.cashbook.model.Task
//import com.techrealms.cashbook.service.LogService
//import com.techrealms.cashbook.service.StorageService
//import com.techrealms.cashbook.screens.CashBookViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import idFromParameter
//import java.text.SimpleDateFormat
//import java.util.Calendar
//import java.util.Locale
//import java.util.TimeZone
//import javax.inject.Inject
//
//@HiltViewModel
//class EditTaskViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
//    logService: LogService,
//    private val storageService: StorageService,
//) : CashBookViewModel(logService) {
//    val task = mutableStateOf(Task())
//
//    init {
//        val taskId = savedStateHandle.get<String>(TASK_ID)
//        if (taskId != null) {
//            launchCatching {
//                task.value = storageService.getTask(taskId.idFromParameter()) ?: Task()
//            }
//        }
//    }
//
//    fun onTitleChange(newValue: String) {
//        task.value = task.value.copy(title = newValue)
//    }
//
//    fun onDescriptionChange(newValue: String) {
//        task.value = task.value.copy(description = newValue)
//    }
//
//    fun onUrlChange(newValue: String) {
//        task.value = task.value.copy(url = newValue)
//    }
//
//    fun onDateChange(newValue: Long) {
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC))
//        calendar.timeInMillis = newValue
//        val newDueDate = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(calendar.time)
//        task.value = task.value.copy(dueDate = newDueDate)
//    }
//
//    fun onTimeChange(hour: Int, minute: Int) {
//        val newDueTime = "${hour.toClockPattern()}:${minute.toClockPattern()}"
//        task.value = task.value.copy(dueTime = newDueTime)
//    }
//
//    fun onFlagToggle(newValue: String) {
//        val newFlagOption = EditFlagOption.getBooleanValue(newValue)
//        task.value = task.value.copy(flag = newFlagOption)
//    }
//
//    fun onPriorityChange(newValue: String) {
//        task.value = task.value.copy(priority = newValue)
//    }
//
//    fun onDoneClick(popUpScreen: () -> Unit) {
//        launchCatching {
//            val editedTask = task.value
//            if (editedTask.id.isBlank()) {
//                storageService.save(editedTask)
//            } else {
//                storageService.update(editedTask)
//            }
//            popUpScreen()
//        }
//    }
//
//    private fun Int.toClockPattern(): String {
//        return if (this < 10) "0$this" else "$this"
//    }
//
//    companion object {
//        private const val UTC = "UTC"
//        private const val DATE_FORMAT = "EEE, d MMM yyyy"
//    }
//}