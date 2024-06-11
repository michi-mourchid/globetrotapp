import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.android.flagcountryapp.DetailActivity
import com.android.flagcountryapp.PaysFragment
import com.android.flagcountryapp.models.Pays
import com.android.flagcountryapp.R
//import com.android.flagcountryapp.database.AppDatabase
import com.android.flagcountryapp.models.Favoris

class CountryAdapter(
    private val context: Context,
    private var countries: List<Pays>
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    //private var countryDao = AppDatabase.getDatabase(context).countryDao()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_pays, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val pays : Pays = countries[position]
        holder.nom_pays.setText(pays.name)
        holder.iso2.setText(pays.alpha2Code)

        /*var pays_retourne = countryDao.getCountryByName(pays.name)
        var est_favoris : Boolean = false
        if (pays_retourne!=null){
            est_favoris = true
            holder.ajouter_favoris.setImageResource(R.drawable.baseline_favorite_24)
            holder.ajouter_favoris.setTag(R.drawable.baseline_favorite_24)
        }*/

        var pays_retourne = Favoris.find(pays.name)
        var est_favoris : Boolean = false
        if (pays_retourne!=null){
            est_favoris = true
            holder.ajouter_favoris.setImageResource(R.drawable.baseline_favorite_24)
            holder.ajouter_favoris.setTag(R.drawable.baseline_favorite_24)
        }


        holder.bouton_afficher_pays.setOnClickListener {
            var intent: Intent = Intent(context, DetailActivity::class.java)
            var bundle: Bundle = Bundle()
            bundle.putString("detail_drapeau", pays.flag.medium)
            bundle.putString("nom_pays", pays.name)
            bundle.putString("nom_officiel", pays.officialName)
            bundle.putString("iso2", pays.alpha2Code)
            bundle.putString("iso3", pays.alpha3Code)
            bundle.putString("numericode", pays.numericCode)
            bundle.putString("codeappel", pays.callingCode)
            bundle.putString("region", pays.region)
            bundle.putString("capital", pays.capital)
            bundle.putString("horaire", pays.timezones[0])
            bundle.putInt("population", pays.population)

            intent.putExtras(bundle)
            context.startActivity(intent)
        }
        holder.ajouter_favoris.setOnClickListener{
            if (holder.ajouter_favoris.getTag()==R.drawable.baseline_favorite_24 || est_favoris){
                holder.ajouter_favoris.setImageResource(R.drawable.baseline_favorite_border_24)
                holder.ajouter_favoris.setTag(R.drawable.baseline_favorite_border_24)
                //countryDao.deleteCountry(pays.name)
                Favoris.remove(pays)
            } else if(holder.ajouter_favoris.getTag()==R.drawable.baseline_favorite_border_24 || !est_favoris){
                holder.ajouter_favoris.setImageResource(R.drawable.baseline_favorite_24)
                holder.ajouter_favoris.setTag(R.drawable.baseline_favorite_24)
                //countryDao.insert(pays)
                Favoris.add(pays)
            } else {
                holder.ajouter_favoris.setImageResource(R.drawable.baseline_favorite_24)
                holder.ajouter_favoris.setTag(R.drawable.baseline_favorite_24)
                //countryDao.insert(pays)
                Favoris.add(pays)
            }

        }



    }

    override fun getItemCount(): Int = countries.size

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iso2 : TextView = itemView.findViewById(R.id.tvCountryIso)
        var nom_pays : TextView = itemView.findViewById(R.id.tvCountryName)
        var bouton_afficher_pays : Button = itemView.findViewById(R.id.button_afficher_drapeau)
        var ajouter_favoris : ImageView = itemView.findViewById(R.id.image_ajout_favori)
    }

    fun updateCountries(newCountries: List<Pays>) {
        countries = newCountries
    }
}
