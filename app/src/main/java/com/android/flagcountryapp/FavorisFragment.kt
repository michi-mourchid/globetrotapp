package com.android.flagcountryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavorisFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val countriesList = mutableListOf<String>()
    private lateinit var database: CountryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = CountryDatabase.getDatabase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favoris, container, false)
        listView = view.findViewById(R.id.liste_items)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, countriesList)
        listView.adapter = adapter
        loadFavorites()
        return view
    }

    private fun loadFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = database.countryDao().getAllCountries()
            withContext(Dispatchers.Main) {
                countriesList.clear()
                countriesList.addAll(favorites.map { it.name })
                adapter.notifyDataSetChanged()
            }
        }
    }
}
