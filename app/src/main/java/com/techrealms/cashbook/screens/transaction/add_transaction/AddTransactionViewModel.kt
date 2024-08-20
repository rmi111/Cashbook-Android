package com.techrealms.cashbook.screens.transaction.add_transaction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.techrealms.cashbook.BUSINESS_ID
import com.techrealms.cashbook.CASHBOOK_ID
import com.techrealms.cashbook.model.Category
import com.techrealms.cashbook.model.Transaction
import com.techrealms.cashbook.model.TransactionType
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.service.CategoryStorageService
import com.techrealms.cashbook.service.LogService
import com.techrealms.cashbook.service.TransactionStorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val transactionStorage: TransactionStorageService,
    private val categoryStorageService: CategoryStorageService,
): CashBookViewModel(logService)
{
    val transaction = mutableStateOf(Transaction("", Category(),"",0.0,"",TransactionType.IN))
    val categories = categoryStorageService.categories

    private val businessId: String?
    private val cashbookId: String?

    init{
        businessId = savedStateHandle.get<String>(BUSINESS_ID)
        cashbookId = savedStateHandle.get<String>(CASHBOOK_ID)
    }

    fun onRemarkChanged(newValue: String){
        transaction.value = transaction.value.copy(remarks = newValue)
    }

    fun onAmountChanged(newValue: String){
        transaction.value = transaction.value.copy(amount = newValue.toDouble())
    }

    fun onCategoryChanged(newValue: Category){
        transaction.value = transaction.value.copy(category = newValue)
    }

    fun onDoneClicked(popupScreen: () -> Unit)
    {
        launchCatching {
            val transaction = transaction.value
            if(transaction.id.isBlank())
            {
                if (businessId != null && cashbookId != null) {
                    transactionStorage.save(transaction,businessId,cashbookId)
                }
            }
        }
    }
}