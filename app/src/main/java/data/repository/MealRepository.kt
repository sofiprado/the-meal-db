package data.repository

import data.api.MealApiManager
import domain.entities.Meal
import io.reactivex.Single

object MealRepository {

    fun getMeals(query: String): Single<List<Meal>> {
        return MealApiManager.getMeals(query).map {
            it.mealsResult.map { mealResponse ->  Meal(mealResponse) }
        }
    }
}