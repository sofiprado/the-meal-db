package com.sofiaprado.themeal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sofiaprado.themeal.R
import domain.entities.Meal

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

    //dentro del when, en response.data, va el método que va a decir qué hacer con esa respuesta

    private fun handleMealResponse(response: MealViewModel.MealRequestResponse) {

        when(response) {
            is MealViewModel.MealRequestResponse.Success -> {prueba(response.data) }
            is MealViewModel.MealRequestResponse.Error -> { response.error }
        }
    }

    fun prueba(lista : List<Meal>) {
    lista.elementAt(4)
    }

    private fun setRecyclerView() {
        mealRecyclerView = findViewById(R.id.activity_meal_recycler_view)
    }

    private fun getMeals() {
        viewModel.getMealByQuery("")
        Log.d("prueba", "prueba")
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
    
    private fun showMealDetail(meal: Meal) {
        val intent = Intent(this, MealDetailActivity::class.java)
        intent.putExtra(MealDetailActivity.MEAL_DETAIL, meal)
        startActivity(intent)
    }
}
