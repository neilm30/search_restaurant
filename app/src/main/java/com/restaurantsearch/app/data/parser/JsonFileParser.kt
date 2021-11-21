package com.restaurantsearch.data.parser

import android.content.Context
import com.google.gson.Gson
import com.restaurantsearch.R
import com.restaurantsearch.data.extensions.fromjson
import com.restaurantsearch.data.model.response.menu.MenuList
import com.restaurantsearch.data.model.response.restaurant.RestaurantList

object JsonFileParser{

    fun getRestaurantList(context:Context) = context.resources?.openRawResource(R.raw.restaurants).fromjson<RestaurantList>()

    fun getMenuItemsList(context:Context) = context.resources?.openRawResource(R.raw.menu).fromjson<MenuList>()

}