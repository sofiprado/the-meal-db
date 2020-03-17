package domain.entities

import android.os.Parcelable
import data.api.model.MealResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
class Meal(
    val title: String,
    val category: String,
    val mealImgUrl: String

): Parcelable{
    constructor(mealResponse: MealResponse): this(
        mealResponse.title,
        mealResponse.category,
        mealResponse.mealImgUrl
    )
}