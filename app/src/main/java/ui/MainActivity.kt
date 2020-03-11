package com.sofiaprado.themeal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sofiaprado.themeal.R
import data.api.MealService
import data.api.model.MealDbResponse
import data.api.RetrofitApiClient
import data.repository.MealRepository
import domain.entities.Meal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MealViewModel
    private lateinit var mealRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        viewModel.mealLiveData.observe(this, Observer { handleMealResponse(it) })

        setRecyclerView()
        getMeals()

    }

    private fun handleMealResponse(response: MealViewModel.MealRequestResponse) {
        when(response) {
            is MealViewModel.MealRequestResponse.Success -> { response.data }
            is MealViewModel.MealRequestResponse.Error -> { response.error }
        }
    }

    private fun setRecyclerView() {
        mealRecyclerView = findViewById(R.id.activity_meal_recycler_view)
    }

    private fun getMeals() {

        viewModel.getMealByQuery("")
        /*retrofitClient.getMeals("a").enqueue(object: Callback<MealDbResponse> {
            override fun onResponse(
                call: Call<MealDbResponse>,
                response: Response<MealDbResponse>
            ) {
                if (response.isSuccessful){
                    val mealsList= response.body()?.mealsResult?.map { mealResponse ->  Meal(mealResponse) }
                    showMeals(mealsList)
                    Log.i("check", response.toString() )
                } else {
                    errorLog()
                }
            }
            override fun onFailure(call: Call<MealDbResponse>, t: Throwable) {
            errorLog()
            }
        })*/
    }

    private fun showMeals(mealsList: List<Meal>?) {
    if(mealsList.isNullOrEmpty())
        return

        val adapter= MealAdapter(mealsList)
        adapter.listener = object: MealAdapter.MealListener{
            override fun onItemClick(meal: Meal) {
                showMealDetail(meal)
            }
        }

        
    }
    
    private fun errorLog() {
        Log.e("error", "error en getMeals")
    }
    
    private fun showMealDetail(meal: Meal) {
        val intent = Intent(this, MealDetailActivity::class.java)
        intent.putExtra(MealDetailActivity.MEAL_DETAIL, meal)
        startActivity(intent)
    }

}
