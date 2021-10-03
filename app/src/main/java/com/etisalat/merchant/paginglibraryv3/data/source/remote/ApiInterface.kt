package com.etisalat.merchant.paginglibraryv3.data.source.remote

import com.etisalat.merchant.paginglibraryv3.data.models.BaseResponse
import com.etisalat.merchant.paginglibraryv3.presentation.Mart
import com.etisalat.merchant.paginglibraryv3.presentation.MartContent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Amir.jehangir on 26,September,2021
 */
interface ApiInterface {
    @POST("/wMart/mobileMart/findAllMarts")
    suspend fun getAllMartData(): BaseResponse<MartContent>
}