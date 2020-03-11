package com.sofiaprado.themeal.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.repository.MealRepository
import domain.entities.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MealViewModel : ViewModel() {
    private var disposable: Disposable? = null
    val mealLiveData = MutableLiveData<MealRequestResponse>()

    fun getMealByQuery(query: String) {
        disposable = MealRepository
            .getMeals(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( { success -> mealLiveData.value = MealRequestResponse.Success(success) }, { error ->  MealRequestResponse.Error(error)} )
    }

    sealed class MealRequestResponse {
        class Success(val data: List<Meal>): MealRequestResponse()
        class Error(val error: Throwable): MealRequestResponse()
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}