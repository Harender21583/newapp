package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Article
import com.example.myapplication.network.RetroInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainScreenViewModel:ViewModel() {
    private val _insertUsersDataStatus = MutableLiveData<List<Article>>()

    val insertUsersDataStatus: LiveData<List<Article>> get() = _insertUsersDataStatus

    var q="india"


    init {
        viewModelScope.launch {
            val response=try {
                RetroInstance.getNews.getNews(q)
            }catch (e:HttpException){
                Log.d("Http","no internet")
                return@launch
            }catch (e:IOException){
                Log.d("IO","SomethingWent Wrong")
                return@launch
            }
            if (response.isSuccessful){
                Log.d("respo",response.body().toString())
                _insertUsersDataStatus.postValue(response.body()!!.articles)
            }
        }
    }

    fun searchNews(s:String){
        viewModelScope.launch {
            val response=try {
                RetroInstance.getNews.getNews(s)
            }catch (e:HttpException){
                Log.d("Http","no internet")
                return@launch
            }catch (e:IOException){
                Log.d("IO","SomethingWent Wrong")
                return@launch
            }
            if (response.isSuccessful){
                Log.d("respo",response.body().toString())
                _insertUsersDataStatus.postValue(response.body()!!.articles)

            }
        }
    }
}