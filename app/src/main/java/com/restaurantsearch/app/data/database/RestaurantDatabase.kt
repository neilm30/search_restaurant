package com.restaurantsearch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.restaurantsearch.R
import com.restaurantsearch.data.extensions.fromjson
import com.restaurantsearch.data.model.response.menu.MenuList
import com.restaurantsearch.data.model.response.restaurant.RestaurantList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

@Database(
    entities = [RestaurantEntity::class, MenuEntity::class],
    version = 1
)

abstract class RestaurantDatabase : RoomDatabase(){
    abstract fun searchDao(): SearchDao

    companion object {
        @Volatile private var instance: RestaurantDatabase? = null
        private val LOCK = Any()


        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RestaurantDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return instance
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RestaurantDatabase::class.java,
                        "search_database"
                    ).fallbackToDestructiveMigration()
                        .addCallback(
                            DeveloperDatabaseCallback(
                                context,
                                scope
                            )
                        )
                        .build()
                    instance
                }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            RestaurantDatabase::class.java, "restaurant-list.db")
            .build()
    }

    private class DeveloperDatabaseCallback(
        private val context: Context,
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            instance?.let { database ->
                scope.launch(Dispatchers.IO) {
                    val restaurantList: RestaurantList = context.resources?.openRawResource(R.raw.restaurants).fromjson()
                    val menuList: MenuList = context.resources?.openRawResource(R.raw.menu).fromjson()

                    populateDatabase(
                        database,
                        restaurantList,
                        menuList
                    )
                }
            }
        }

        private fun populateDatabase(database: RestaurantDatabase, restaurantList: RestaurantList, menuList: MenuList) {
            val searchDao = database.searchDao()

            // Empty database on first load
            searchDao.delete()

            restaurantList.restaurants.forEach {
                searchDao.insertAllRestuarant(RestaurantEntity(it.id,it.cuisine_type,it.name))
            }

            menuList.menus.forEach { res ->
                res.categories.forEach { cat ->
                    cat.menuitems.forEach {
                        searchDao.insertAllMenuItem(MenuEntity(res.restaurantId,it.name))
                    }

                }
            }
        }
    }

}
