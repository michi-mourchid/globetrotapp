package com.android.flagcountryapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.flagcountryapp.models.Pays
import com.android.flagcountryapp.service.CountryApiService
import com.android.volley.Response
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response as RetrofitResponse

class QuizFragment : Fragment() {

    private lateinit var flagImageView: ImageView
    private lateinit var countryNameEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var retourButton: ImageView

    private var countries: List<Pays> = listOf()
    private var currentCountry: Pays? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        flagImageView = view.findViewById(R.id.flagImageView)
        countryNameEditText = view.findViewById(R.id.countryNameEditText)
        submitButton = view.findViewById(R.id.submitButton)
        resultTextView = view.findViewById(R.id.resultTextView)
        retourButton = view.findViewById(R.id.image_retour)

        retourButton.setOnClickListener {
            this.activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, PaysFragment())?.commit()
        }

        submitButton.setOnClickListener {
            checkAnswer()
        }

        fetchCountriesFromApi()

        return view
    }

    private fun fetchCountriesFromApi() {
        val paysService = RetrofitClient.instance.create(CountryApiService::class.java)
        paysService.getAllPays().enqueue(object : Callback<Map<String,Pays>> {
            override fun onResponse(call: Call<Map<String, Pays>>, response: RetrofitResponse<Map<String,Pays>>) {
                if (response.isSuccessful) {
                    val countriesMap = response.body() ?: emptyMap()
                    countries = countriesMap.values.toList()
                    loadNewCountry()
                } else {
                    Log.e("QuizFragment", "Failed")
                }
            }

            override fun onFailure(call: Call<Map<String,Pays>>, t: Throwable) {
                Log.e("QuizFragment", "Failed ", t)
            }
        })
    }

    private fun loadNewCountry() {
        currentCountry = countries.random()
        currentCountry?.let {
            Glide.with(this).load(it.flag.medium).into(flagImageView)
            countryNameEditText.text.clear()
            resultTextView.text = ""
        }
    }

    private fun checkAnswer() {
        val userAnswer = countryNameEditText.text.toString().trim()
        if (userAnswer.equals(currentCountry?.name, ignoreCase = true)) {
            resultTextView.text = "Validé"
        } else {
            resultTextView.text = "Incorrectte, essaie encore!"
        }
    }
}