package com.etisalat.merchant.paginglibraryv3.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.etisalat.merchant.paginglibraryv3.data.source.db.Converters.Converter
import com.etisalat.merchant.paginglibraryv3.data.source.db.Dao.MartDao
import com.etisalat.merchant.paginglibraryv3.presentation.Mart

/**
 * Created by Amir.jehangir on 26,September,2021
 */
@Database(
    entities = [Mart::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converter::class)
abstract class MartDataBase : RoomDatabase() {
    abstract val martDao: MartDao
}