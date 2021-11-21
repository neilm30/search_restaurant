package com.restaurantsearch.app.presentation.viewmodel


sealed class Result<out T:Any>

class Success<out T:Any>(val str:String): Result<T>()

class Failure<out T:Any>(val error:Throwable): Result<T>()