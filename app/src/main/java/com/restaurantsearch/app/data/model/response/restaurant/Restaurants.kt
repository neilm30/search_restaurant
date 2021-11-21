package com.restaurantsearch.data.model.response.restaurant

data class Restaurants (

    val id : Int,
    val name : String,
    val neighborhood : String?,
    val photograph : String?,
    val address : String,
    val latlng : Latlng?,
    val cuisine_type : String,
    val reviews : List<Reviews>
)