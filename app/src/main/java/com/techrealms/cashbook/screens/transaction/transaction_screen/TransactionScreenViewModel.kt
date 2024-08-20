package com.techrealms.cashbook.screens.transaction.transaction_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.techrealms.cashbook.BUSINESS_ID
import com.techrealms.cashbook.CASHBOOK_ADD_SCREEN
import com.techrealms.cashbook.CASHBOOK_ID
import com.techrealms.cashbook.CASHBOOK_SCREEN
import com.techrealms.cashbook.model.Cashbook
import com.techrealms.cashbook.model.Transaction
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.screens.cashbook.cashbook_screen.CashbookActionOption
import com.techrealms.cashbook.service.LogService
import com.techrealms.cashbook.service.TransactionStorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


@HiltViewModel
class TransactionScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val logService: LogService,
    private val transactionStorageService: TransactionStorageService
): CashBookViewModel(logService)
{
    var transaction:Flow<List<Transaction>> = emptyFlow()

    private val businessId: String?
    private val cashbookId: String?

    init
    {
        businessId = savedStateHandle.get<String>(BUSINESS_ID)
        cashbookId = savedStateHandle.get<String>(CASHBOOK_ID)

        if(businessId != null && cashbookId != null)
        {
            transaction = transactionStorageService.transaction(businessId,cashbookId)
            Log.d("Transaction", transaction.toString())
        }
    }

    fun onAddClick(openScreen: (String) -> Unit) = openScreen("$CASHBOOK_ADD_SCREEN?$BUSINESS_ID=${businessId}")

    fun onCashbookActionClick(openScreen: (String) -> Unit, cashbook: Cashbook, action: String)
    {
        when(CashbookActionOption.getByTitle(action))
        {
            CashbookActionOption.OpenCashbook -> openScreen("$CASHBOOK_SCREEN?$CASHBOOK_ID={${cashbook.id}} ")
        }
    }
}