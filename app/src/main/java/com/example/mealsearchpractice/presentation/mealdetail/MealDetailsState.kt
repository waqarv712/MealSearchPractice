package com.example.mealsearchpractice.presentation.mealdetail

import com.example.mealsearchpractice.domain.model.MealDetail


data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetail? = null,
    val error: String = ""
) {
}