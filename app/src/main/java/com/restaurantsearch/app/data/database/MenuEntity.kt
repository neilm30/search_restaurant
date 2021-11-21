package com.restaurantsearch.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_table")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "restaurantId")  var id: Int,
    @ColumnInfo(name = "menuitem") var menuitem: String
)
