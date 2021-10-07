package com.amir.mart.paginglibraryv3.modules

import android.app.Application
import androidx.room.Room
import com.amir.mart.paginglibraryv3.data.source.db.Dao.MartDao
import com.amir.mart.paginglibraryv3.data.source.db.MartDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Amir.jehangir on 26,September,2021
 */
val databaseModule = module {

    fun provideDatabase(application: Application): MartDataBase {
        return Room.databaseBuilder(application, MartDataBase::class.java, "mart")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMartDao(database: MartDataBase): MartDao{
        return  database.martDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideMartDao(get()) }


}