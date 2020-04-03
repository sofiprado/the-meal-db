package com.sofiaprado.themeal.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.repository.MealRepository
import domain.entities.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MealViewModel : ViewModel() {
    private var disposable: Disposable? = null
    val mealLiveData = MutableLiveData<MealRequestResponse>()
    val randomMealLiveData = MutableLiveData<RandomMealRequestResponse>()

    fun getMeals(query: String) {
        disposable = MealRepository
            .getMeals(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( { success -> mealLiveData.value = MealRequestResponse.Success(success) }, { error ->  MealRequestResponse.Error(error)} )
    //mealLiveData.value asigna un valor una vez que el request dio success

    }

    fun getRandomMeal(){
        disposable = MealRepository
            .getRandomMeal()
            .observeOn(AndroidSchedulers.mainThread())
            .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS) }
            .subscribeOn(Schedulers.io())
            .subscribe( { success -> randomMealLiveData.value = RandomMealRequestResponse.Success(success) }, { error ->  RandomMealRequestResponse.Error(error)} )
    }

        //reutilizar método para todos los request
    sealed class MealRequestResponse {
        class Success(val data: List<Meal>): MealRequestResponse()
        class Error(val error: Throwable): MealRequestResponse()
    }

    sealed class RandomMealRequestResponse{
        class Success(val data: List<Meal>): RandomMealRequestResponse()
        class Error(val error: Throwable): RandomMealRequestResponse()

    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}