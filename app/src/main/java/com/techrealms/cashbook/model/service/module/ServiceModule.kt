package com.techrealms.cashbook.model.service.module

import com.techrealms.cashbook.model.service.AccountService
import com.techrealms.cashbook.model.service.impl.AccountServiceImpl
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
}