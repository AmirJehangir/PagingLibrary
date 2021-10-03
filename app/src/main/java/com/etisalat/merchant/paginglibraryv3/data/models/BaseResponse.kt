package com.etisalat.merchant.paginglibraryv3.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Amir.jehangir on 26,September,2021
 */
data class BaseResponse<T>(
    @SerializedName("status")
    var status: String?,
    @SerializedName("errorCode")
    var errorCode: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("data")
    var data: T?
)