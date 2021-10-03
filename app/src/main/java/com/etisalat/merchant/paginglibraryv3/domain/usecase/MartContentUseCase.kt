package com.etisalat.merchant.paginglibraryv3.domain.usecase


import com.etisalat.merchant.paginglibraryv3.data.models.BaseResponse
import com.etisalat.merchant.paginglibraryv3.domain.repository.MartRepository
import com.etisalat.merchant.paginglibraryv3.domain.usecase.base.BaseUseCase
import com.etisalat.merchant.paginglibraryv3.presentation.MartContent

/**
 * Created by Amir.jehangir on 27,September,2021
 */
class MartContentUseCase (
    private val agentRepository: MartRepository
): BaseUseCase<BaseResponse<MartContent>, String>() {
    override suspend fun run(params: String): BaseResponse<MartContent> {
        return agentRepository.findAllMarts()
    }

}