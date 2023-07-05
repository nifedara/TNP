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


//class TradelineApplication : Application() {
//
//    /**
//     * AppContainer instance used by the rest of the classes to obtain dependencies
//     */
//    lateinit var container: AppContainer
//
//    override fun onCreate() {
//        super.onCreate()
//        container = AppDataContainer()
//    }
//
//    companion object {
//        private lateinit var instance: TradelineApplication
//
//        fun getInstance(): TradelineApplication {
//            return instance
//        }
//    }
//}

//class TradelineApplication : Application() {
//
//    /**
//     * AppContainer instance used by the rest of classes to obtain dependencies
//     */
//    lateinit var container: AppContainer
//    override fun onCreate() {
//        super.onCreate()
//        container = AppDataContainer(this)
//    }
//}
