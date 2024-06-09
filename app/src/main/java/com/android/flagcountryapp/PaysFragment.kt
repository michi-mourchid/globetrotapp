package com.android.flagcountryapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.android.flagcountryapp.service.CountryApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaysFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val countriesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pays, container, false)
        listView = view.findViewById(R.id.liste_items)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, countriesList)
        listView.adapter = adapter
        fetchCountries()
        return view
    }

    private fun fetchCountries() {
        val api = RetrofitClient.instance.create(CountryApiService::class.java)
        api.getAllPays().enqueue(object : Callback<List<Pays>> {
            override fun onResponse(call: Call<List<Pays>>, response: Response<List<Pays>>) {
                if (response.isSuccessful) {
                    response.body()?.let { countries ->
                        countries.forEach {
                            countriesList.add(it.name)
                        }
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("PaysFragment", "Response not successful: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Pays>>, t: Throwable) {
                Log.e("PaysFragment", "Erreur : ${t.message}")
            }
        })
    }
}
