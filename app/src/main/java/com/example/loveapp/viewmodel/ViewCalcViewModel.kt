package com.example.loveapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loveapp.model.LoveResult
import com.example.loveapp.model.LoveApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalcViewModel(private val apiService: LoveApiService) : ViewModel() {

    private val _loveResult = MutableLiveData<LoveResult>()
    val loveResult: LiveData<LoveResult> get() = _loveResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun calculateLove(firstName: String, secondName: String) {
        viewModelScope.launch {
            apiService.getPercentage(
                key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
                host = "love-calculator.p.rapidapi.com",
                firstName = firstName,
                secondName = secondName
            ).enqueue(object : Callback<LoveResult> {
                override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                    if (response.isSuccessful && response.body() != null) {
                        _loveResult.postValue(response.body())
                    } else {
                        _errorMessage.postValue("Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LoveResult>, t: Throwable) {
                    _errorMessage.postValue("Error: ${t.message}")
                }
            })
        }
    }
}
