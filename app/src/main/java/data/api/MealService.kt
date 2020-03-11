package data.api

import data.api.model.MealDbResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET("search.php")
    fun getMeals(@Query("s") s: String): Single<MealDbResponse>

    @GET("lookup.php")
    fun getMealById(@Query("i") i: Int): Single<MealDbResponse>

    @GET("random.php")
    fun getRandomMeal(): Single<MealDbResponse>
}
