package com.sofiaprado.themeal.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sofiaprado.themeal.R
import domain.entities.Meal
import android.view.LayoutInflater
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider

class MealAdapter(val meals: List<Meal>):
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    interface MealListener{
        fun onItemClick(meal: Meal)
    }

    var listener: MealListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.meal_adapter,parent,false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        val url = meal.mealImgUrl
        holder.title.text = meal.title
        holder.category.text= meal.category

        Picasso.get()
            .load(url)
            .into(holder.mealImgUrl)

        holder.itemView.setOnClickListener{listener?.onItemClick(meal)}
    }


    override fun getItemCount(): Int = meals.size

    class MealViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView= view.findViewById(R.id.meal_adapter_title)
        val category: TextView= view.findViewById(R.id.meal_adapter_category)
        val mealImgUrl: ImageView= view.findViewById(R.id.meal_adapter_img)
    }
}



