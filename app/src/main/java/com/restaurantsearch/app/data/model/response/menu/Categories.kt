package com.restaurantsearch.data.model.response.menu


data class Categories (
	val id : Int,
	val name : String,
	val menuitems : List<Menuitems>
)