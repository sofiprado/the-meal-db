package data.api.model

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("strMeal")
    val title: String,
    @SerializedName("strCategory")
    val category: String
)