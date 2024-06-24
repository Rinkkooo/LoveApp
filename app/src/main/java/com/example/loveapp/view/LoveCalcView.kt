package com.example.loveapp.view

interface LoveCalcView {
    fun showResult(firstName: String, secondName: String, percentage: String, result: String)
    fun showError(message: String)
}
