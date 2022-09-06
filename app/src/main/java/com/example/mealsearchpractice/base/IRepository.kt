package com.example.mealsearchpractice.base

import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
}