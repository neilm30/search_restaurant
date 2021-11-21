package com.restaurantsearch.data.model.response.menu

data class Menuitems (
	val id : Int,
	val name : String,
	val description : String,
	val price : Double,
	val images : List<String>
)