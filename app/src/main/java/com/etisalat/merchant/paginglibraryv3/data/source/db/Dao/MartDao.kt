package com.etisalat.merchant.paginglibraryv3.data.source.db.Dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.etisalat.merchant.paginglibraryv3.presentation.Mart

/**
 * Created by Amir.jehangir on 26,September,2021
 */
@Dao
interface MartDao {

    @Query("SELECT * FROM Mart")
    fun findAll():  List<Mart>

    @Query("SELECT * FROM Mart")
    fun findAllMartData(): DataSource.Factory<Int, Mart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<Mart>?)

    @Query("DELETE FROM Mart")
    fun deleteMarttData()
}