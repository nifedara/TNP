package com.example.tradeline


import android.app.Application
import com.example.tradeline.data.AppContainer
import com.example.tradeline.data.AppDataContainer

class TradelineApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
