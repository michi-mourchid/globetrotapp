package com.android.flagcountryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.flagcountryapp.Pays

class FavoriteCountryAdapter(private val context: Context, private val countries: List<Pays>) :
    RecyclerView.Adapter<FavoriteCountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pays, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //private val nameTextView: TextView = itemView.findViewById(R.id.country_name)
        //private val favoriteIcon: ImageView = itemView.findViewById(R.id.favorite_icon)

        fun bind(country: Pays) {
            //nameTextView.text = country.name
            //favoriteIcon.setImageResource(R.drawable.ic_favorite) // Display as favorite
        }
    }
}
