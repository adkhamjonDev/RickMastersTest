package com.adkhamjon.rikmasterstest.data

import com.adkhamjon.rikmasterstest.data.remote.dto.MainResponse
import com.adkhamjon.rikmasterstest.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

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
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        "Error"
                    )
                )
                offlineMode.invoke(this)
            }
        }
    }
}