package com.restaurantsearch.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.restaurantsearch.databinding.ItemRowBinding
import com.restaurantsearch.domain.database.models.RestaurantDetails

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

     private var restaurantDetailsList: List<RestaurantDetails> = emptyList()
    //class SearchViewHolder(var viewBinding: ItemRowBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(restaurantDetailsList[position])
    }

    override fun getItemCount(): Int {
       return restaurantDetailsList.size
    }

    inner class ViewHolder(private var item: ItemRowBinding) : RecyclerView.ViewHolder(item.root) {
       fun bind(details: RestaurantDetails) {
            item.restaurantName.text = details.restaurantName
           item.restaurantAddress.text = details.restaurantAddress

       }
    }

    fun setItemsList(restaurantDetailsList: List<RestaurantDetails>){
          this.restaurantDetailsList = restaurantDetailsList
    }
}