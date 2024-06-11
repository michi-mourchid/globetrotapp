package com.android.flagcountryapp

import CountryAdapter
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.flagcountryapp.databinding.FragmentPaysBinding
import com.android.flagcountryapp.service.CountryApiService
import com.android.volley.RequestQueue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.android.flagcountryapp.models.Pays

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaysFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaysFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var requestQueue: RequestQueue
    private lateinit var view: View
    private lateinit var binding: FragmentPaysBinding
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaysBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.liste_items)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        Log.d("TAG", "ici")
        val paysService = RetrofitClient.instance.create(CountryApiService::class.java)
        if(PaysFragment.countries==null || PaysFragment.countries == ArrayList<Pays>()){
        paysService.getAllPays().enqueue(object : Callback<Map<String, Pays>> {
            override fun onResponse(call: Call<Map<String, Pays>>, response: Response<Map<String, Pays>>) {
                if (response.isSuccessful) {
                    val countriesMap = response.body() ?: emptyMap()
                    Log.d("TAG", "Countries JSON: ${response.body().toString()}")
                    val countries = countriesMap.values.toList()
                    PaysFragment.updateCountries(countries)
                    Log.d("TAG", countries.get(100).toString())
                    countryAdapter = CountryAdapter(activity!!, countries)
                    recyclerView.adapter =countryAdapter
                    Log.d("TAG", "Adapter attached with ${countries.size} items")
                } else {
                    Log.e("TAG", "Response was not successful")
                }
            }

            override fun onFailure(call: Call<Map<String, Pays>>, t: Throwable) {
                Log.e("TAG", "Failed to fetch countries", t)
            }
        })} else {
            countryAdapter = CountryAdapter(requireActivity(), PaysFragment.countries)
            recyclerView.adapter =countryAdapter
        }
        
        editText = view.findViewById(R.id.input_recherche)
        extracted()


    }

    private fun PaysFragment.extracted() {
        editText.doAfterTextChanged {
            var list_pays: ArrayList<Pays> = ArrayList()
            if (editText.text.toString().isNotEmpty()) {
                for (country in countries) {
                    if (country.name.lowercase().contains(editText.text.toString().lowercase()) ||
                        country.capital.lowercase().contains(editText.text.toString().lowercase())
                    ) {
                        list_pays.add(country)
                    }
                }
                countryAdapter.updateCountries(list_pays)
                recyclerView.adapter =countryAdapter
            } else {
                countryAdapter.updateCountries(PaysFragment.countries)
                recyclerView.adapter = countryAdapter
            }

        }
    }


    companion object {
        var countries : MutableList<Pays> = ArrayList()
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaysFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        fun retrieveCountries() : List<Pays>{
            return countries
        }
        fun updateCountries(countries : List<Pays>){
            this.countries= countries.toMutableList()
        }


    }
}