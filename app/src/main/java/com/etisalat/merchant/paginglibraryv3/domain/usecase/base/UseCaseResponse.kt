package com.etisalat.merchant.paginglibraryv3.domain.usecase.base

interface UseCaseResponse<T> {
    fun onResponse(outcome: Outcome<T>)
}