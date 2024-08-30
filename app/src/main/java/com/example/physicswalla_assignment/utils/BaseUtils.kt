package com.example.physicswalla_assignment.utils

import android.util.Log
import retrofit2.Response

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful && response.body() != null) {
            NetworkResult.Success(response.body()!!)
        } else if (response.isSuccessful && response.body() == null) {

            NetworkResult.Error("Response body is null")
        } else {
            Log.d("checkingResponseSafeE",response.message())
            NetworkResult.Error(response.message())
        }
    } catch (e: Exception) {
        Log.d("checkingResponseSafeC",e.toString())
        NetworkResult.Error(e.message ?: "An unknown error occurred")
    }
}