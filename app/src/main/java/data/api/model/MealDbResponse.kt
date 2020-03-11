package data.api.model

import com.google.gson.annotations.SerializedName

data class MealDbResponse(
   @SerializedName("meals")
   val mealsResult : List<MealResponse>
)