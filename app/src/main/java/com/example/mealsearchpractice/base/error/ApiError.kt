package com.example.mealsearchpractice.base.error

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "code") var statusCode: Int,
    @Json(name = "message") var message: String = ""
)