package com.example.physicswalla_assignment.utils

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
            NetworkResult.Error(response.message())
        }
    } catch (e: Exception) {
        NetworkResult.Error(e.message ?: "An unknown error occurred")
    }
}