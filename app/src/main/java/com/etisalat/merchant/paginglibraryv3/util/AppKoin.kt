package com.etisalat.merchant.paginglibraryv3.util

import android.app.Application
import com.etisalat.merchant.paginglibraryv3.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Amir.jehangir on 26,September,2021
 */
class AppKoin {
    fun start(myApplication: Application) {
        startKoin {
            androidLogger()
            androidContext(myApplication)
            modules(
                listOf(
                    RepositoryModule,
                    MartContentModules,
                    NetworkModule,
                    databaseModule
                )
            )
        }
    }
}