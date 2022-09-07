package com.example.mealsearchpractice.presentation.mealsearch

import com.example.mealsearchpractice.domain.model.Meal

data class MealSearchState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading: Boolean = false
) {
}