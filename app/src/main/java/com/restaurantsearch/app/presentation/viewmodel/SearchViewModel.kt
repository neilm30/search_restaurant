package com.restaurantsearch.app.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.restaurantsearch.R
import com.restaurantsearch.app.data.SearchDataSource
import com.restaurantsearch.data.model.response.restaurant.RestaurantList
import com.restaurantsearch.data.extensions.fromjson
import com.restaurantsearch.data.model.response.menu.MenuList
import com.restaurantsearch.domain.database.models.RestaurantDetails
import com.restaurantsearch.domain.database.repository.SearchRepository


class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: SearchRepository = SearchRepository(SearchDataSource())
    private var isRestaurantFound: Boolean = false
   // private var restaurantList : RestaurantList
   // private var menuList : MenuList
     var restaurantDetailsEvent = MutableLiveData<List<RestaurantDetails>>()


    init {
       // restaurantList = application.resources?.openRawResource(R.raw.restaurants).fromjson()
      //  menuList =  application.resources?.openRawResource(R.raw.menu).fromjson()

       // val wordsDao = RestaurantDatabase.getDatabase(application, viewModelScope).searchDao()
        repository.mapJsonToModel(application)
       // restaurantList = repository.allDevelopers
    }

    fun getRestaurantDetails() =  repository.getRestaurantDetails()

    fun searchQuery(text: String?){
        text?.let {
            repository.searchQueryUsingModel(it) {
                restaurantDetailsEvent.value = it.distinct().toList()
            }
        }
       }
    }

   /*  fun search(text: String?,success: (List<RestaurantDetails>) ->Unit) {
         isRestaurantFound = false
         restaurantDetails.clear()
        text?.let {
            if(it.length>=2){

                menuList.menus.forEach { menus ->
                    val resId = menus.restaurantId
                    menus.categories.forEach { cate->
                        cate.menuitems.forEach { menuitems ->
                            if(menuitems.name.contains(text,true)){
                                restaurantList.restaurants.forEach { res->
                                    if(resId == res.id){
                                        Log.i("SearchViewModel","serach menu:"+res.name)
                                        restaurantDetails.add(RestaurantDetails(restaurantName = res.name, restaurantAddress = res.address))
                                        success(restaurantDetails.distinct().toList())
                                        isRestaurantFound = true
                                    }
                                }
                            }
                        }
                    }
                }

                if(!isRestaurantFound){
                    restaurantList.restaurants.forEach { restaurant->
                        if(restaurant.cuisine_type.contains(text,false) || restaurant.name.contains(text,false)){
                            Log.i("SearchViewModel","serach cuisine:"+restaurant.name)
                            restaurantDetails.add(RestaurantDetails(restaurantName = restaurant.name, restaurantAddress = restaurant.address))
                    }
                        success(restaurantDetails)

                    }
                }

                    *//*menus.categories.forEach { cate->
                        cate.menuitems.forEach { menuitems ->
                            if(menuitems.name.contains(text,false)){
                                restaurantList.restaurants.forEach { res->
                                    if(resId == res.id){
                                        Log.i("SearchViewModel","serach menu:"+res.name)
                                        success(RestaurantDetails(restaurantName = res.name, restaurantAddress = res.address))
                                    }
                                }
                            }else{
                                restaurantList.restaurants.forEach { ccuisineType->
                                    if(ccuisineType.cuisine_type.contains(text,false)){
                                        Log.i("SearchViewModel","serach cuisine:"+ccuisineType.name)
                                        success(RestaurantDetails(restaurantName = ccuisineType.name, restaurantAddress = ccuisineType.address))

                                    }else if(ccuisineType.name.contains(text,false)){
                                        Log.i("SearchViewModel","serach res:"+ccuisineType.name)
                                        success(RestaurantDetails(restaurantName = ccuisineType.name, restaurantAddress = ccuisineType.address))
                                    }
                                }
                            }
                        }

                    }*//*

            } else {
                success(getRestaurantDetails())
            }

        }
           *//* people.forEach { person ->
                if (person.name.contains(text, true) ||
                    person.age.toString().contains(text, true)
                ) {
                    matchedPeople.add(person)
                    updateRecyclerView()
                }
            }
            if (matchedPeople.isEmpty()) {
                Toast.makeText(this, "No match found!", Toast.LENGTH_SHORT).show()
            }
            updateRecyclerView()
        }*//*
    }
}*/