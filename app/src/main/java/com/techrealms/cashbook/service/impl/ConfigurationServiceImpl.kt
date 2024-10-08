package com.techrealms.cashbook.service.impl

import com.google.firebase.BuildConfig
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.get
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.techrealms.cashbook.service.ConfigurationService
import com.techrealms.cashbook.service.trace
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.techrealms.cashbook.R.xml as AppConfig

class ConfigurationServiceImpl @Inject constructor(): ConfigurationService {
    private val remoteConfig
        get() = Firebase.remoteConfig

    init{
        if(BuildConfig.DEBUG){
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
            remoteConfig.setConfigSettingsAsync(configSettings)
        }

        remoteConfig.setDefaultsAsync(AppConfig.remote_config_defaults)
    }

    override suspend fun fetchConfiguration():Boolean =
        trace(FETCH_CONFIG_TRACE){
            remoteConfig.fetchAndActivate().await()
        }

    override val isShowTaskEditButtonConfig: Boolean
        get() = remoteConfig[SHOW_TASK_EDIT_BUTTON_KEY].asBoolean()

    companion object{
        private const val FETCH_CONFIG_TRACE = "fetchConfig"
        private const val SHOW_TASK_EDIT_BUTTON_KEY = "show_task_edit_button"
    }
}