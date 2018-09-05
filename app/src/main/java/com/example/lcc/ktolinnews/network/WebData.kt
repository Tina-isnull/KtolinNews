package com.example.lcc.ktolinnews.network

interface WebData<T>{
    fun getData(url:String):ArrayList<T>
}