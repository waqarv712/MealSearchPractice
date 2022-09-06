package com.example.mealsearchpractice.data.model


import com.example.mealsearchpractice.domain.model.Meal
import com.example.mealsearchpractice.domain.model.MealDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.mealsearchpractice.base.BaseResponse

@JsonClass(generateAdapter = true)
data class MealDTO(
    @Json(name = "dateModified")
    val dateModified: String?,
    @Json(name = "idMeal")
    val idMeal: String?,
    @Json(name = "strArea")
    val strArea: String?,
    @Json(name = "strCategory")
    val strCategory: String?,
    @Json(name = "strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String?,
    @Json(name = "strDrinkAlternate")
    val strDrinkAlternate: String?,
    @Json(name = "strImageSource")
    val strImageSource: String?,
    @Json(name = "strIngredient1")
    val strIngredient1: String?,
    @Json(name = "strIngredient10")
    val strIngredient10: String?,
    @Json(name = "strIngredient11")
    val strIngredient11: String?,
    @Json(name = "strIngredient12")
    val strIngredient12: String?,
    @Json(name = "strIngredient13")
    val strIngredient13: String?,
    @Json(name = "strIngredient14")
    val strIngredient14: String?,
    @Json(name = "strIngredient15")
    val strIngredient15: String?,
    @Json(name = "strIngredient16")
    val strIngredient16: String?,
    @Json(name = "strIngredient17")
    val strIngredient17: String?,
    @Json(name = "strIngredient18")
    val strIngredient18: String?,
    @Json(name = "strIngredient19")
    val strIngredient19: String?,
    @Json(name = "strIngredient2")
    val strIngredient2: String?,
    @Json(name = "strIngredient20")
    val strIngredient20: String?,
    @Json(name = "strIngredient3")
    val strIngredient3: String?,
    @Json(name = "strIngredient4")
    val strIngredient4: String?,
    @Json(name = "strIngredient5")
    val strIngredient5: String?,
    @Json(name = "strIngredient6")
    val strIngredient6: String?,
    @Json(name = "strIngredient7")
    val strIngredient7: String?,
    @Json(name = "strIngredient8")
    val strIngredient8: String?,
    @Json(name = "strIngredient9")
    val strIngredient9: String?,
    @Json(name = "strInstructions")
    val strInstructions: String?,
    @Json(name = "strMeal")
    val strMeal: String?,
    @Json(name = "strMealThumb")
    val strMealThumb: String?,
    @Json(name = "strMeasure1")
    val strMeasure1: String?,
    @Json(name = "strMeasure10")
    val strMeasure10: String?,
    @Json(name = "strMeasure11")
    val strMeasure11: String?,
    @Json(name = "strMeasure12")
    val strMeasure12: String?,
    @Json(name = "strMeasure13")
    val strMeasure13: String?,
    @Json(name = "strMeasure14")
    val strMeasure14: String?,
    @Json(name = "strMeasure15")
    val strMeasure15: String?,
    @Json(name = "strMeasure16")
    val strMeasure16: String?,
    @Json(name = "strMeasure17")
    val strMeasure17: String?,
    @Json(name = "strMeasure18")
    val strMeasure18: String?,
    @Json(name = "strMeasure19")
    val strMeasure19: String?,
    @Json(name = "strMeasure2")
    val strMeasure2: String?,
    @Json(name = "strMeasure20")
    val strMeasure20: String?,
    @Json(name = "strMeasure3")
    val strMeasure3: String?,
    @Json(name = "strMeasure4")
    val strMeasure4: String?,
    @Json(name = "strMeasure5")
    val strMeasure5: String?,
    @Json(name = "strMeasure6")
    val strMeasure6: String?,
    @Json(name = "strMeasure7")
    val strMeasure7: String?,
    @Json(name = "strMeasure8")
    val strMeasure8: String?,
    @Json(name = "strMeasure9")
    val strMeasure9: String?,
    @Json(name = "strSource")
    val strSource: String?,
    @Json(name = "strTags")
    val strTags: String?,
    @Json(name = "strYoutube")
    val strYoutube: String?
) : BaseResponse()

fun MealDTO.toDomainMeal(): Meal {
    return Meal(
        mealId = this.idMeal ?: "",
        name = this.strMeal ?: "",
        image = this.strMealThumb ?: ""
    )
}

fun MealDTO.toDomainMealDetails(): MealDetail {
    return MealDetail(
        name = this.strMeal ?: "",
        image = this.strMealThumb ?: "",
        instructions = this.strInstructions ?: "",
        category = this.strCategory ?: "",


        ingredient1 = this.strIngredient1 ?: "",
        ingredient2 = this.strIngredient2 ?: "",
        ingredient3 = this.strIngredient3 ?: "",
        ingredient4 = this.strIngredient4 ?: "",
        ingredient5 = this.strIngredient5 ?: "",
        ingredient6 = this.strIngredient6 ?: "",
        ingredient7 = this.strIngredient7 ?: "",
        ingredient8 = this.strIngredient8 ?: "",
        ingredient9 = this.strIngredient9 ?: "",
        ingredient10 = this.strIngredient10 ?: "",
        ingredient11 = this.strIngredient11 ?: "",
        ingredient12 = this.strIngredient12 ?: "",
        ingredient13 = this.strIngredient13 ?: "",
        ingredient14 = this.strIngredient14 ?: "",
        ingredient15 = this.strIngredient15 ?: "",
        ingredient16 = this.strIngredient16 ?: "",
        ingredient17 = this.strIngredient17 ?: "",
        ingredient18 = this.strIngredient18 ?: "",
        ingredient19 = this.strIngredient19 ?: "",
        ingredient20 = this.strIngredient20 ?: "",

        measure1 = this.strMeasure1 ?: "",
        measure2 = this.strMeasure2 ?: "",
        measure3 = this.strMeasure3 ?: "",
        measure4 = this.strMeasure4 ?: "",
        measure5 = this.strMeasure5 ?: "",
        measure6 = this.strMeasure6 ?: "",
        measure7 = this.strMeasure7 ?: "",
        measure8 = this.strMeasure8 ?: "",
        measure9 = this.strMeasure9 ?: "",
        measure10 = this.strMeasure10 ?: "",
        measure11 = this.strMeasure11 ?: "",
        measure12 = this.strMeasure12 ?: "",
        measure13 = this.strMeasure13 ?: "",
        measure14 = this.strMeasure14 ?: "",
        measure15 = this.strMeasure15 ?: "",
        measure16 = this.strMeasure16 ?: "",
        measure17 = this.strMeasure17 ?: "",
        measure18 = this.strMeasure18 ?: "",
        measure19 = this.strMeasure19 ?: "",
        measure20 = this.strMeasure20 ?: ""
    )
}