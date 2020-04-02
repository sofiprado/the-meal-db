package domain.entities

import android.os.Parcelable
import data.api.model.MealResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
class Meal(
    val title: String,
    val category: String,
    val mealImgUrl: String,
    val recipeInstructions: String,
    val ingredient1: String,
    val ingredient2: String,
    val ingredient3: String,
    val ingredient4: String,
    val ingredient5: String,
    val ingredient6: String,
    val ingredient7: String,
    val ingredient8: String,
    val ingredient9: String,
    val ingredient10: String,
    val ingredient11: String,
    val ingredient12: String,
    val ingredient13: String,
    val strYoutube: String


    ): Parcelable{
    constructor(mealResponse: MealResponse): this(
        mealResponse.title,
        mealResponse.category,
        mealResponse.mealImgUrl,
        mealResponse.recipeInstructions,
        mealResponse.ingredient1,
        mealResponse.ingredient2,
        mealResponse.ingredient3,
        mealResponse.ingredient4,
        mealResponse.ingredient5,
        mealResponse.ingredient6,
        mealResponse.ingredient7,
        mealResponse.ingredient8,
        mealResponse.ingredient9,
        mealResponse.ingredient10,
        mealResponse.ingredient11,
        mealResponse.ingredient12,
        mealResponse.ingredient13,
        mealResponse.strYoutube
    )
}