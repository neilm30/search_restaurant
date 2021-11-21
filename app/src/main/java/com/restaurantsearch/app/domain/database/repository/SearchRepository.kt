package com.restaurantsearch.domain.database.repository

import android.content.Context
import com.restaurantsearch.app.data.SearchDataSource
import com.restaurantsearch.app.domain.database.jsonmapper.convertToList
import com.restaurantsearch.data.model.response.menu.MenuList
import com.restaurantsearch.data.model.response.restaurant.RestaurantList
import com.restaurantsearch.data.parser.JsonFileParser
import com.restaurantsearch.domain.database.models.RestaurantDetails

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class SearchRepository(private val dataSource: SearchDataSource) {
    private lateinit var restaurantList: RestaurantList
    private lateinit var menuList: MenuList

    fun mapJsonToModel(context: Context) {
        restaurantList = JsonFileParser.getRestaurantList(context)
        menuList = JsonFileParser.getMenuItemsList(context)
    }

    fun searchQueryUsingModel(searchtext: String, success: (List<RestaurantDetails>) -> Unit) {
        if (searchtext.isNotEmpty() && searchtext.length > 1) {
            val restaurantDetails: List<RestaurantDetails> =
                dataSource.searchQueryFromDataModel(searchtext, restaurantList, menuList)
            success(restaurantDetails)
        } else {
            success(getRestaurantDetails())
        }
    }

    fun getRestaurantDetails() = restaurantList.restaurants.convertToList()
}
