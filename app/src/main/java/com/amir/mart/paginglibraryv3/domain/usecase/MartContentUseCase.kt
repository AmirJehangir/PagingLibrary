package com.amir.mart.paginglibraryv3.domain.usecase


import com.amir.mart.paginglibraryv3.data.models.BaseResponse
import com.amir.mart.paginglibraryv3.domain.repository.MartRepository
import com.amir.mart.paginglibraryv3.domain.usecase.base.BaseUseCase
import com.amir.mart.paginglibraryv3.presentation.MartContent

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