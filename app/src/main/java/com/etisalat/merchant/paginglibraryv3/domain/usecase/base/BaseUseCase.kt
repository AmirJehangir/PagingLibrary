package com.etisalat.merchant.paginglibraryv3.domain.usecase.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

abstract class BaseUseCase<Response, in Params> where Response : Any {

    abstract suspend fun run(params: Params): Response

    open fun start(
        showLoader: Boolean,
        scope: CoroutineScope,
        params: Params
    ): MutableLiveData<Outcome<Response>> {
        val resultLiveData = MutableLiveData<Outcome<Response>>()
        resultLiveData.value = Outcome.Start()
        val backgroundJob = scope.async {
            run(params)
        }
        scope.launch {
            try {
                backgroundJob.await().let {

                    resultLiveData.value = Outcome.Success(it)

                }
            } catch (e: HttpException) {
                resultLiveData.value = Outcome.NetworkError(e)
            } catch (e: UnknownHostException) {
                resultLiveData.value = Outcome.NetworkError(e)
            } catch (e: SocketTimeoutException) {
                resultLiveData.value = Outcome.NetworkError(e)
            } catch (e: SSLHandshakeException) {
                resultLiveData.value = Outcome.NetworkError(e)
            } catch (ex: Exception) {
                resultLiveData.value = Outcome.Failure(ex)
            } finally {
                resultLiveData.value = Outcome.End()
            }
        }
        return resultLiveData
    }

    open fun start(
        showLoader: Boolean,
        scope: CoroutineScope,
        params: Params,
        onResult: (UseCaseResponse<Response>)
    ) {
        onResult.onResponse(Outcome.Start())
        val backgroundJob = scope.async {
            run(params)
        }
        scope.launch {
            backgroundJob.await().let {
                try {
                    onResult.onResponse(
                        Outcome.Success(
                            it
                        )
                    )
                } catch (e: HttpException) {
                    onResult.onResponse(
                        Outcome.Failure(
                            e
                        )
                    )
                } catch (ex: Exception) {
                    onResult.onResponse(
                        Outcome.Failure(
                            ex
                        )
                    )
                } finally {
                    onResult.onResponse(Outcome.End())
                }
            }
        }
    }
}