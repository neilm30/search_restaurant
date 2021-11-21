package com.restaurantsearch.data.extensions

import android.content.Context
import com.google.gson.Gson
import com.restaurantsearch.R
import com.restaurantsearch.data.model.response.restaurant.RestaurantList
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

inline fun<reified T> InputStream?.fromjson():T{
    try {
        val br = BufferedReader(InputStreamReader(this))
        return Gson().fromJson<T>(br, T::class.java)
    }catch (e: Exception){
        throw Throwable()
    }

}