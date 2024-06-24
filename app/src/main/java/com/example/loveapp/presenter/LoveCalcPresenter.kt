package com.example.loveapp.presenter

import com.example.loveapp.LoveApiService
import com.example.loveapp.model.LoveResult
import com.example.loveapp.view.LoveCalcView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalcPresenter(private val view: LoveCalcView, private val apiService: LoveApiService) {

    fun calculateLove(firstName: String, secondName: String) {
        apiService.getPercentage(
            key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
            host = "love-calculator.p.rapidapi.com",
            firstName = firstName,
            secondName = secondName
        ).enqueue(object : Callback<LoveResult> {
            override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                if (response.isSuccessful && response.body() != null) {
                    val loveResult = response.body()!!
                    view.showResult(
                        firstName = loveResult.firstName,
                        secondName = loveResult.secondName,
                        percentage = loveResult.percentage,
                        result = loveResult.result
                    )
                } else {
                    view.showError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoveResult>, t: Throwable) {
                view.showError("Error: ${t.message}")
            }
        })
    }
}
