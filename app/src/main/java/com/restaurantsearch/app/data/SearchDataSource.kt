package com.restaurantsearch.app.data

import android.util.Log
import com.restaurantsearch.data.model.response.menu.MenuList
import com.restaurantsearch.data.model.response.restaurant.RestaurantList
import com.restaurantsearch.domain.database.models.RestaurantDetails
import com.restaurantsearch.domain.database.repository.SearchService

class SearchDataSource : SearchService {

    private var isRestaurantFound: Boolean = false
    private var restaurantDetails = mutableListOf<RestaurantDetails>()

    override fun searchQueryFromDataModel(
        queryText: String,
        restaurantList: RestaurantList,
        menuList: MenuList
    ): MutableList<RestaurantDetails> {
        isRestaurantFound = false
        restaurantDetails.clear()
        menuList.menus.forEach { menus ->
            val resId = menus.restaurantId
            menus.categories.forEach { cate ->
                cate.menuitems.forEach { menuitems ->
                    if (menuitems.name.contains(queryText, true)) {
                        restaurantList.restaurants.forEach { res ->
                            if (resId == res.id) {
                                Log.i("SearchViewModel", "serach menu:" + res.name)
                                restaurantDetails.add(
                                    RestaurantDetails(
                                        restaurantName = res.name,
                                        restaurantAddress = res.address
                                    )
                                )
                                isRestaurantFound = true
                            }
                        }
                    }
                }
            }
        }

        if (!isRestaurantFound) {
            restaurantList.restaurants.forEach { restaurant ->
                if (restaurant.cuisine_type.contains(queryText, false) || restaurant.name.contains(
                        queryText,
                        false
                    )
                ) {
                    Log.i("SearchViewModel", "serach cuisine:" + restaurant.name)
                    restaurantDetails.add(
                        RestaurantDetails(
                            restaurantName = restaurant.name,
                            restaurantAddress = restaurant.address
                        )
                    )
                }
            }
        }
        return restaurantDetails
    }

    override fun searchQueryFromDatabase(queryText: String) {
        TODO("Not yet implemented")
    }
}