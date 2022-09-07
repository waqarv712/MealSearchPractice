package com.example.mealsearchpractice.base.network

import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
}