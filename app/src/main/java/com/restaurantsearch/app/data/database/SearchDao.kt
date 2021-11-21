package com.restaurantsearch.data.database

import androidx.room.*

@Dao
interface SearchDao {
    @Query("SELECT * FROM restaurants_table")
    fun getAllRestaurants(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurants_table")
    fun getSingleRestaurants(): List<RestaurantEntity>

    @Query("SELECT restaurantname FROM restaurants_table WHERE menuitem LIKE :searchStr")
    fun findBySearchQuery(searchStr: String): RestaurantEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllRestuarant(vararg todo: RestaurantEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMenuItem(vararg todo: MenuEntity)

    @Query("DELETE FROM restaurants_table")
    fun delete()
}