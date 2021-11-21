package com.restaurantsearch.app.domain.database.jsonmapper

import com.restaurantsearch.data.model.response.restaurant.Restaurants
import com.restaurantsearch.domain.database.models.RestaurantDetails

fun List<Restaurants>.convertToList(): List<RestaurantDetails> = this.map {
           RestaurantDetails(it.name, it.address)
}
