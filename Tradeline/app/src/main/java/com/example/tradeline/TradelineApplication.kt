package com.example.tradeline


import android.app.Application
import com.example.tradeline.data.AppContainer
import com.example.tradeline.data.AppDataContainer
import com.example.tradeline.ui.AppViewModelProvider

class TradelineApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)

        AppViewModelProvider.initialize(this)
    }
}