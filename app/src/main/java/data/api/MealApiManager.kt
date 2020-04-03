package data.api

import data.api.model.MealDbResponse
import io.reactivex.Single

object MealApiManager {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofitClient = RetrofitApiClient(BASE_URL).createService(MealService::class.java)

    fun getMeals(query: String): Single<MealDbResponse> = retrofitClient.getMeals(query)

    fun getRandomMeal(): Single<MealDbResponse> = retrofitClient.getRandomMeal()

    //fun getMealByQuery(query: String): Single<MealDbResponse> = retrofitClient.getMealByQuery(query)

    //fun getMealById(query: Int): Single<MealDbResponse> = retrofitClient.getMealById(query)


}