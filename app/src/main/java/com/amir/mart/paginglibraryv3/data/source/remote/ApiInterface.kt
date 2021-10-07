package com.amir.mart.paginglibraryv3.data.source.remote

import com.amir.mart.paginglibraryv3.data.models.BaseResponse
import com.amir.mart.paginglibraryv3.presentation.MartContent
import retrofit2.http.POST

/**
 * Created by Amir.jehangir on 26,September,2021
 */
interface ApiInterface {
    @POST("/wMart/mobileMart/findAllMarts")
    suspend fun getAllMartData(): BaseResponse<MartContent>
}