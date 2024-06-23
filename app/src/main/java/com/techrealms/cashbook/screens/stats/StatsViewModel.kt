package com.techrealms.cashbook.screens.stats

import androidx.compose.runtime.mutableStateOf
import com.techrealms.cashbook.service.LogService
import com.techrealms.cashbook.service.StorageService
import com.techrealms.cashbook.screens.CashBookViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService
) : CashBookViewModel(logService) {
    val uiState = mutableStateOf(StatsUiState())

    init {
        launchCatching { loadStats() }
    }

    private suspend fun loadStats() {
        val updatedUiState = StatsUiState(
            completedTasksCount = storageService.getCompletedTasksCount(),
            importantCompletedTasksCount = storageService.getImportantCompletedTasksCount(),
            mediumHighTasksToCompleteCount = storageService.getMediumHighTasksToCompleteCount()
        )

        uiState.value = updatedUiState
    }
}