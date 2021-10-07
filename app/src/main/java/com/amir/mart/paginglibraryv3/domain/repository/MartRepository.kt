package com.amir.mart.paginglibraryv3.domain.repository

import com.amir.mart.paginglibraryv3.data.models.BaseResponse
import com.amir.mart.paginglibraryv3.presentation.Mart
import com.amir.mart.paginglibraryv3.presentation.MartContent

/**
 * Created by Amir.jehangir on 26,September,2021
 */
interface MartRepository {
    suspend fun findAllMarts(): BaseResponse<MartContent>
    suspend fun fetchLocalUser(): List<Mart>
}