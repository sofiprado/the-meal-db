package data.api

import data.api.model.MealDbResponse
import io.reactivex.Single

object MealApiManager {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofitClient: MealService=
        RetrofitApiClient(BASE_URL).createService(MealService::class.java)

    fun getMeals(query: String): Single<MealDbResponse> = retrofitClient.getMeals(query)

    fun getMealById(query: Int): Single<MealDbResponse> = retrofitClient.getMealById(query)

    fun getRandomMeal(): Single<MealDbResponse> = retrofitClient.getRandomMeal()

}