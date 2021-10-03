package com.etisalat.merchant.paginglibraryv3.domain.repository

import com.etisalat.merchant.paginglibraryv3.data.models.BaseResponse
import com.etisalat.merchant.paginglibraryv3.presentation.MartContent

/**
 * Created by Amir.jehangir on 26,September,2021
 */
interface MartRepository {
    suspend fun findAllMarts(): BaseResponse<MartContent>
}