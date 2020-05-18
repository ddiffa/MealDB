//package com.hellodiffa.themealdb.utils
//
//import androidx.lifecycle.MutableLiveData
//import com.hellodiffa.themealdb.common.ResultState
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.withContext
//
//suspend fun <L, R> resultMutableLiveData(
//    databaseQuery: suspend () -> List<L>,
//    networkCall: suspend () -> ResultState<List<R>>,
//    saveCallResult: suspend (List<R>) -> Unit
//) =
//    withContext(Dispatchers.IO) {
//        val liveData = MutableLiveData<ResultState<List<L>>>()
//        liveData.postValue(ResultState.loading<List<L>>())
//        delay(1_500)
//        val meal = databaseQuery.invoke()
//        val response = networkCall.invoke()
//        if (meal.isEmpty() && (response.status == ResultState.Status.SUCCESS)) {
//            response.data?.let { saveCallResult(it) }
//            liveData.postValue(response)
//        } else {
//            if (response.message != null) {
//
//            }
//        }
//        liveData.apply {
//            postValue(ResultState.success(meal))
//        }
//    }