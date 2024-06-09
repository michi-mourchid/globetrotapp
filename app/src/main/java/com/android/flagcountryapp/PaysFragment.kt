package com.android.flagcountryapp

import CountryAdapter
import android.os.Bundle
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
import retrofit2.create

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
    private lateinit var requestQueue: RequestQueue
    private lateinit var view: View
    private lateinit var binding: FragmentPaysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_pays, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paysService = RetrofitClient.instance.create(CountryApiService::class.java)

        recyclerView = view.findViewById(R.id.liste_items)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this.context))
        println("11111111111")

        paysService.getAllPays().enqueue(object: Callback<List<Pays>> {
            override fun onResponse(call: Call<List<Pays>>, response: Response<List<Pays>>) {
                if (response.isSuccessful) {
                    val countries = (response.body() ?: emptyList()) as List<Pays>
                    var adapter = CountryAdapter(countries)
                    recyclerView.adapter = adapter
                    print("censé marcher")
                } else {print("ça marche pas")}
            }

            override fun onFailure(call: Call<List<Pays>>, t: Throwable) {
                print("erreuuuuuur")
            }
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaysFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaysFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}