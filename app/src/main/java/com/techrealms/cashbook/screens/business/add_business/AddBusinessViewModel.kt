package com.techrealms.cashbook.screens.business.add_business

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.techrealms.cashbook.model.Business
import com.techrealms.cashbook.screens.CashBookViewModel
import com.techrealms.cashbook.service.BusinessStorageService
import com.techrealms.cashbook.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddBusinessViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val businessStorageService: BusinessStorageService,
    ): CashBookViewModel(logService)
{
    val business = mutableStateOf(Business())

    fun onTitleChanged(newValue: String){
        business.value = business.value.copy(title = newValue)
    }

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

    fun onDoneClicked(popupScreen: () -> Unit)
    {
        launchCatching {
            val business = business.value
            if(business.id.isBlank())
            {
                businessStorageService.save(business)
            }
        }
    }
}