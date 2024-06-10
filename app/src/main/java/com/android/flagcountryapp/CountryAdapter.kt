import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.android.flagcountryapp.DetailActivity
import com.android.flagcountryapp.Pays
import com.android.flagcountryapp.R
import com.android.flagcountryapp.databinding.FragmentPaysBinding

class CountryAdapter(
    private val context: Context,
    private val countries: List<Pays>
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_pays, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val pays : Pays = countries[position]
        holder.nom_pays.setText(pays.name)
        holder.iso2.setText(pays.alpha2Code)

        holder.bouton_afficher_pays.setOnClickListener {
            var intent: Intent = Intent(context, DetailActivity::class.java)
            var bundle : Bundle = Bundle()
            bundle.putString("detail_drapeau",pays.flag.medium)
            bundle.putString("nom_pays",pays.name)
            bundle.putString("nom_officiel",pays.officialName)
            bundle.putString("iso2",pays.alpha2Code)
            bundle.putString("iso3",pays.alpha3Code)
            bundle.putString("numericode",pays.numericCode)
            bundle.putString("codeappel",pays.callingCode)
            bundle.putString("region",pays.region)
            bundle.putString("capital",pays.capital)
            bundle.putString("horaire",pays.timezones[0])
            bundle.putInt("population",pays.population)

            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = countries.size

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iso2 : TextView = itemView.findViewById(R.id.tvCountryIso)
        var nom_pays : TextView = itemView.findViewById(R.id.tvCountryName)

        var bouton_afficher_pays : Button = itemView.findViewById(R.id.button_afficher_drapeau)
    }
}
