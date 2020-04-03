package com.sofiaprado.themeal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sofiaprado.themeal.R
import com.squareup.picasso.Picasso
import domain.entities.Meal

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MealViewModel
    private lateinit var mealRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        viewModel.mealLiveData.observe(this, Observer { handleMealResponse(it) })
        viewModel.randomMealLiveData.observe(this, Observer { handleRandomMealResponse(it)})

        setRecyclerView()
        getMeals()
        getRandomMeal()

        val searchTextView: EditText = findViewById(R.id.edit_text_main_search)

        searchTextView.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getMealByQuery(s.toString())
            }
        })
    }


    private fun handleMealResponse(response: MealViewModel.MealRequestResponse) {

        when(response) {
            is MealViewModel.MealRequestResponse.Success -> {
                setAdapter(response.data)
            }
            is MealViewModel.MealRequestResponse.Error -> { response.error }
        }
    }

    private fun handleRandomMealResponse(response: MealViewModel.RandomMealRequestResponse) {

        when(response) {
            is MealViewModel.RandomMealRequestResponse.Success -> {
                setRandomImage(response.data)
            }
            is MealViewModel.RandomMealRequestResponse.Error -> { response.error }
        }
    }


    private fun setRecyclerView() {
        mealRecyclerView = findViewById(R.id.activity_meal_recycler_view)
        mealRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun getMeals() {
        viewModel.getMeals("")
    }

    private fun getMealByQuery(query: String) {
        viewModel.getMeals(query)
    }

    private fun getRandomMeal(){
      viewModel.getRandomMeal()

    }

    private fun setRandomImage(randomMeal: List<Meal>){

        val url: String = randomMeal[0].mealImgUrl
        val randomImageView: ImageView = findViewById(R.id.random_image)

        Picasso.get()
            .load(url)
            .into(randomImageView)

    }

    private fun setAdapter(lista: List<Meal>) {
        mealRecyclerView.adapter = MealAdapter(lista,
            object: MealAdapter.onItemClickListener {
                override fun invoke(meal: Meal) {
                        showMealDetail(meal)
                }
            })
    }

    private fun showMealDetail(meal: Meal) {
        val intent = Intent(this, MealDetailActivity::class.java)
        intent.putExtra(MealDetailActivity.MEAL_DETAIL, meal)
        startActivity(intent)
    }
}
