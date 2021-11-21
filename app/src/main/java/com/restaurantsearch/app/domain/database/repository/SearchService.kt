package com.restaurantsearch.domain.database.repository

import com.restaurantsearch.data.model.response.menu.MenuList
import com.restaurantsearch.data.model.response.restaurant.RestaurantList
import com.restaurantsearch.domain.database.models.RestaurantDetails

interface SearchService {

    fun searchQueryFromDataModel(queryText: String, list1: RestaurantList, list: MenuList):MutableList<RestaurantDetails>

    fun searchQueryFromDatabase(queryText: String)

}