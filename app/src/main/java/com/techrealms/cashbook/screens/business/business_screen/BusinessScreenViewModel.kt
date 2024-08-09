package com.techrealms.cashbook.screens.business.business_screen

import com.techrealms.cashbook.BUSINESS_ADD_SCREEN
import com.techrealms.cashbook.BUSINESS_ID
import com.techrealms.cashbook.CASHBOOK_SCREEN
import com.techrealms.cashbook.model.Business
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.service.BusinessStorageService
import com.techrealms.cashbook.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusinessScreenViewModel @Inject constructor(
    private val logService: LogService,
    private val businessStorageService: BusinessStorageService
): CashBookViewModel(logService)
{
      val business = businessStorageService.business

    fun onBusinessActionClick(openScreen: (String) -> Unit, business: Business, action: String) {
            when(BusinessActionOption.getByTitle(action)){
                BusinessActionOption.OpenBusiness -> openScreen("$CASHBOOK_SCREEN?$BUSINESS_ID=${business.id} ")
//                TaskActionOption.ToggleFlag -> onFlagClick(task)
//                TaskActionOption.DeleteTask -> onDeleteTaskClick(task)
            }
        }

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(BUSINESS_ADD_SCREEN)
    fun onBusinessClick(openScreen: (String) -> Unit, business: Business) = openScreen("$CASHBOOK_SCREEN?$BUSINESS_ID=${business.id}")
}