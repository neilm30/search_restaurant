package com.restaurantsearch.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants_table")
data class RestaurantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")  var id: Int,
    @ColumnInfo(name = "cusinetype") var cusinetype: String,
    @ColumnInfo(name = "restaurantname") var restaurantname: String
)
