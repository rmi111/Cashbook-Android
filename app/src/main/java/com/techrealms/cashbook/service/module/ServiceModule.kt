package com.techrealms.cashbook.service.module

import com.techrealms.cashbook.service.AccountService
import com.techrealms.cashbook.service.BusinessStorageService
import com.techrealms.cashbook.service.CashbookStorageService
import com.techrealms.cashbook.service.CategoryStorageService
import com.techrealms.cashbook.service.ConfigurationService
import com.techrealms.cashbook.service.LogService
import com.techrealms.cashbook.service.impl.AccountServiceImpl
import com.techrealms.cashbook.service.impl.BusinessStorageServiceImpl
import com.techrealms.cashbook.service.impl.CashbookStorageServiceImpl
import com.techrealms.cashbook.service.impl.CategoryStorageServiceImpl
import com.techrealms.cashbook.service.impl.ConfigurationServiceImpl
import com.techrealms.cashbook.service.impl.LogServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule
{
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService

    @Binds
    abstract fun provideCategoryStorageService(impl: CategoryStorageServiceImpl): CategoryStorageService

    @Binds
    abstract fun provideCashbookStorageService(impl: CashbookStorageServiceImpl): CashbookStorageService

    @Binds
    abstract fun provideBusinessStorageService(impl: BusinessStorageServiceImpl): BusinessStorageService
//
//    @Binds
//    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}