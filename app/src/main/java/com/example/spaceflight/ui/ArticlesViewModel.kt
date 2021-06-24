package com.example.spaceflight.ui

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflight.models.ArticleItem
import com.example.spaceflight.network.RetrofitService
import kotlinx.coroutines.*

class ArticlesViewModel : ViewModel() {
    private val _newsLiveData = MutableLiveData<MutableList<ArticleItem>>().apply {
        mutableListOf<ArticleItem>()
    }
    val newsLiveData: MutableLiveData<MutableList<ArticleItem>> = _newsLiveData

    var job: Job? = null

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
            d("Response", response.body().toString())
        } else {
            response.code()
            d("Response", response.toString())
        }
    }

    fun intervalCall(ms: Long) {
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                while (isActive) {
                    d("call: ", "CALL")
                    getNews()
                    delay(ms)
                }
            }
        }
    }
}