package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {
    suspend fun <T> getResult(
        Call: suspend ()-> Response<T>,
        Dispatcher: CoroutineDispatcher
    ): Flow<ApiResponse<T>>{
        return flow {
            try {
                val response = Call()
                if (response.isSuccessful){
                    val responseBody = response.body()
                    emit(ApiResponse.Success(responseBody!!))
                }else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", toString())
            }
        }.flowOn(Dispatcher)
    }
}