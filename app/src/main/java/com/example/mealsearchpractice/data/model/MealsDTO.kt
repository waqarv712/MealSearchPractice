package com.example.mealsearchpractice.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsDTO(
    @Json(name = "meals")
    val meals: List<MealDTO?>?
)