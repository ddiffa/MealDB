package com.hellodiffa.themealdb.utils

import androidx.lifecycle.SavedStateHandle
import com.hellodiffa.themealdb.common.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <L, R> resultFlow(
    databaseQuery: suspend () -> L,
    networkCall: suspend () -> ResultState<R>,
    saveCallResult: suspend (R) -> Unit
): Flow<ResultState<L>> =
    flow {
        var meal = databaseQuery.invoke()

        emit(ResultState.loading<L>())
        delay(1_500)

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == ResultState.Status.SUCCESS) {
            responseStatus.data?.let { saveCallResult(it) }
            emit(ResultState.success(meal))
        } else if (responseStatus.status == ResultState.Status.ERROR) {
            if (responseStatus.message != null) {
                emit(ResultState.error<L>(responseStatus.message))
            }
            emit(ResultState.success(meal))
        }
    }
