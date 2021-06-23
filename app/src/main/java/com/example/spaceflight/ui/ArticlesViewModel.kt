package com.example.spaceflight.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflight.models.ArticleItem
import com.example.spaceflight.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesViewModel : ViewModel() {
    private val _newsLiveData = MutableLiveData<MutableList<ArticleItem>>().apply {
        mutableListOf<ArticleItem>()
    }
    val newsLiveData: MutableLiveData<MutableList<ArticleItem>> = _newsLiveData

    fun init() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getNews()
            }
        }
    }

    private suspend fun getNews() {
        val response = RetrofitService.retrofitService.getAllArticles()
        if (response.isSuccessful) {
//            val news = response.body()
            _newsLiveData.postValue(response.body())
            Log.d("Response", response.body().toString())
        } else {
            response.code()
            Log.d("Response", response.toString())
        }
    }
}