package com.techrealms.cashbook.screens.cashbook.add_cashbook

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.techrealms.cashbook.BUSINESS_ID
import com.techrealms.cashbook.model.Cashbook
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.service.CashbookStorageService
import com.techrealms.cashbook.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCashbookViewModel @Inject constructor(
        savedStateHandle: SavedStateHandle,
        logService: LogService,
        private val cashbookStorage: CashbookStorageService,
): CashBookViewModel(logService)
{
    val cashbook = mutableStateOf(Cashbook())
    private val businessId: String?

    init{
         businessId = savedStateHandle.get<String>(BUSINESS_ID);
    }

    fun onTitleChanged(newValue: String){
        cashbook.value = cashbook.value.copy(title = newValue)
    }

    fun onDoneClicked(popupScreen: () -> Unit)
    {
        launchCatching {
            val cashbook = cashbook.value
            if(cashbook.id.isBlank())
            {
                if (businessId != null) {
                    cashbookStorage.save(cashbook,businessId)
                }
            }
        }
    }
}