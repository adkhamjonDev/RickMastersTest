package com.adkhamjon.rikmasterstest.data.common

import com.adkhamjon.rikmasterstest.data.remote.dto.MainResponse
import com.adkhamjon.rikmasterstest.domain.Resource
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class ResultHandler {
    protected suspend fun <T, K> loadResult(
        requestSource: suspend () -> MainResponse<T>,
        successBody: suspend (T, FlowCollector<Resource<K>>) -> Unit,
        offlineMode: suspend (FlowCollector<Resource<K>>) -> Unit = { Unit }
    ): Flow<Resource<K>> = withContext(Dispatchers.IO) {
        flow {
            try {
                emit(Resource.Loading())
                val data = requestSource.invoke()
                if (data.success) {
                    successBody.invoke(data.data, this)
                } else {
                    emit(Resource.Error(data.toString()))
                }
            } catch (e: ClientRequestException) {
                emit(
                    Resource.Error(
                        "CLIENT ERROR"
                    )
                )
            } catch (e: RedirectResponseException) {
                emit(
                    Resource.Error(
                        "REDIRECT ERROR"
                    )
                )
            } catch (e: ServerResponseException) {
                emit(
                    Resource.Error(
                        "SERVER ERROR"
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        "INTERNET ERROR"
                    )
                )
                offlineMode.invoke(this)
            }
        }
    }
}