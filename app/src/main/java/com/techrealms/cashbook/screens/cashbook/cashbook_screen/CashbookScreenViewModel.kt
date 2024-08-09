package com.techrealms.cashbook.screens.cashbook.cashbook_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.techrealms.cashbook.BUSINESS_ID
import com.techrealms.cashbook.CASHBOOK_ADD_SCREEN
import com.techrealms.cashbook.CASHBOOK_ID
import com.techrealms.cashbook.CASHBOOK_SCREEN
import com.techrealms.cashbook.model.Cashbook
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.service.CashbookStorageService
import com.techrealms.cashbook.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


@HiltViewModel
class CashbookScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val logService: LogService,
    private val cashbookStorageService: CashbookStorageService
): CashBookViewModel(logService)
{
    var cashbook:Flow<List<Cashbook>> = emptyFlow()
    private val businessId: String?
    init{
        businessId = savedStateHandle.get<String>(BUSINESS_ID);

        if(businessId != null){
            cashbook = cashbookStorageService.cashbook(businessId)
            Log.d("Cashbook", cashbook.toString())
        }
    }

    fun onAddClick(openScreen: (String) -> Unit) = openScreen("$CASHBOOK_ADD_SCREEN?$BUSINESS_ID=${businessId}")

    fun onCashbookActionClick(openScreen: (String) -> Unit, cashbook: Cashbook, action: String) {
        when(CashbookActionOption.getByTitle(action)){
            CashbookActionOption.OpenCashbook -> openScreen("$CASHBOOK_SCREEN?$CASHBOOK_ID={${cashbook.id}} ")
        }
    }

}