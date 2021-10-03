package com.etisalat.merchant.paginglibraryv3.data.repositoryimpl

import android.util.Log
import com.etisalat.merchant.paginglibraryv3.data.models.BaseResponse
import com.etisalat.merchant.paginglibraryv3.data.source.db.MartDataBase
import com.etisalat.merchant.paginglibraryv3.data.source.remote.ApiInterface
import com.etisalat.merchant.paginglibraryv3.domain.repository.MartRepository
import com.etisalat.merchant.paginglibraryv3.presentation.Mart
import com.etisalat.merchant.paginglibraryv3.presentation.MartContent
import com.etisalat.merchant.paginglibraryv3.util.utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream

/**
 * Created by Amir.jehangir on 26,September,2021
 */
class MartRepositoryImpl(private var apiInterface: ApiInterface,
                         private var martDataBase: MartDataBase): MartRepository {
    override suspend fun findAllMarts(): BaseResponse<MartContent> {
        var agentResult =  apiInterface.getAllMartData()
        withContext(Dispatchers.IO) {

            Log.d("Room DB","Delete Data")
            martDataBase.martDao.deleteMarttData()
            Log.d("Room DB","Insert Data")
            martDataBase.martDao.add(agentResult.data?.content)


        }
        return agentResult

    }




}