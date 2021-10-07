package com.amir.mart.paginglibraryv3.domain.usecase

import com.amir.mart.paginglibraryv3.data.models.BaseResponse
import com.amir.mart.paginglibraryv3.domain.repository.MartRepository
import com.amir.mart.paginglibraryv3.domain.usecase.base.BaseUseCase
import com.amir.mart.paginglibraryv3.presentation.Mart
import com.amir.mart.paginglibraryv3.presentation.MartContent

/**
 * Created by Amir.jehangir on 07,October,2021
 */
class MartLocleContentUseCase(
    private val agentRepository: MartRepository
): BaseUseCase<List<Mart>, String>() {
    override suspend fun run(params: String): List<Mart> {
        return agentRepository.fetchLocalUser()
    }

}