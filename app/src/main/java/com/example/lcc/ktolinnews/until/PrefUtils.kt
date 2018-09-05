package com.example.lcc.ktolinnews.until

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils

object PrefUtils {
    fun  putString(ctx:Context,key:String,value:String) {
        val sp: SharedPreferences = ctx.getSharedPreferences("config",
        Context.MODE_PRIVATE)
        sp.edit().putString(key, value).commit();
    }

    fun getString( ctx:Context, key:String, defValue:String):String {
        val sp:SharedPreferences = ctx.getSharedPreferences("config",
        Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }
}