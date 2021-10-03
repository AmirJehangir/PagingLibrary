package com.etisalat.merchant.paginglibraryv3.domain.usecase.base

sealed class Outcome<T> {
    class Start<T> : Outcome<T>()
    class End<T> : Outcome<T>()
    class Success<T>(var data: T) : Outcome<T>()
    class Failure<T>(val e: Throwable?) : Outcome<T>()
    class NetworkError<T>(val e: Throwable?) : Outcome<T>()
}