 package com.restaurantsearch.app.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.restaurantsearch.app.presentation.adapter.SearchAdapter
import com.restaurantsearch.app.presentation.viewmodel.SearchViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.restaurantsearch.R
import com.restaurantsearch.databinding.ActivitySearchBinding

 class SearchActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: ActivitySearchBinding
    lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bind RecyclerView
        val recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        adapter = SearchAdapter()
        adapter.setItemsList(searchViewModel.getRestaurantDetails())
        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
       performSearch()
        searchViewModel.restaurantDetailsEvent.observe(this,{
            adapter.apply {
                setItemsList(it)
                 notifyDataSetChanged()
            }
        })

    }

    private fun performSearch() {
        binding.searchView.queryHint = getString(R.string.search_hint)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("SearchViewModel", "search onQueryTextSubmit:$query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("SearchViewModel", "search onQueryTextChange:$newText")
                searchViewModel.searchQuery(newText)/* {
                    adapter.setItemsList(it)
                    adapter.notifyDataSetChanged()
                }*/
                return false
            }
        })
    }



}

