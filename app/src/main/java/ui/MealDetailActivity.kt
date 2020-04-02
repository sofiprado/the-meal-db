package com.sofiaprado.themeal.ui

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.sofiaprado.themeal.R
import com.squareup.picasso.Picasso
import domain.entities.Meal

class MealDetailActivity : AppCompatActivity(){
    companion object{
        const val MEAL_DETAIL= "MEAL_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)

        val mealDetail: Meal= intent.extras?.getParcelable<Meal>(MEAL_DETAIL)  as Meal

        showMealDetail(mealDetail)
    }

    private fun showMealDetail(mealDetail: Meal) {

        val title: TextView = findViewById(R.id.meal_detail_title)
        title.text = mealDetail.title
        val category: TextView = findViewById(R.id.meal_detail_category)
        category.text = mealDetail.category
        val recipeInstructions: TextView = findViewById(R.id.meal_detail_instructions)
        recipeInstructions.text = mealDetail.recipeInstructions

        val videoView: VideoView = findViewById(R.id.meal_detail_video)
        val uri: Uri = Uri.parse(mealDetail.strYoutube)
        videoView.setVideoURI(uri)
        videoView.start()

        val mealImg: ImageView = findViewById(R.id.meal_detail_img)
        val url = mealDetail.mealImgUrl
        Picasso.get()
            .load(url)
            .into(mealImg)


        val ingredient1: TextView = findViewById(R.id.meal_detail_ingredient1)
        ingredient1.text =mealDetail.ingredient1
        val ingredient2: TextView = findViewById(R.id.meal_detail_ingredient2)
        ingredient2.text=mealDetail.ingredient2
        val ingredient3: TextView = findViewById(R.id.meal_detail_ingredient3)
        ingredient3.text=mealDetail.ingredient3
        val ingredient4: TextView = findViewById(R.id.meal_detail_ingredient4)
        ingredient4.text=mealDetail.ingredient4
        val ingredient5: TextView = findViewById(R.id.meal_detail_ingredient5)
        ingredient5.text=mealDetail.ingredient5
        val ingredient6: TextView = findViewById(R.id.meal_detail_ingredient6)
        ingredient6.text=mealDetail.ingredient6
        val ingredient7: TextView = findViewById(R.id.meal_detail_ingredient7)
        ingredient7.text=mealDetail.ingredient7
        val ingredient8: TextView = findViewById(R.id.meal_detail_ingredient8)
        ingredient8.text=mealDetail.ingredient8
        val ingredient9: TextView = findViewById(R.id.meal_detail_ingredient9)
        ingredient9.text=mealDetail.ingredient9
        val ingredient10: TextView = findViewById(R.id.meal_detail_ingredient10)
        ingredient10.text=mealDetail.ingredient10
        val ingredient11: TextView = findViewById(R.id.meal_detail_ingredient11)
        ingredient11.text=mealDetail.ingredient11
        val ingredient12: TextView = findViewById(R.id.meal_detail_ingredient12)
        ingredient12.text=mealDetail.ingredient12
        val ingredient13: TextView = findViewById(R.id.meal_detail_ingredient13)
        ingredient13.text=mealDetail.ingredient13
    }
}
