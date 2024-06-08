import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.flagcountryapp.Pays
import com.android.flagcountryapp.databinding.FragmentPaysBinding

class CountryAdapter(private val countries: List<Pays>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    public  class PaysHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = FragmentPaysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size

    inner class CountryViewHolder(private val binding: FragmentPaysBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Pays) {
            //binding. = country.name
            //binding.countryCapital.text = country.capital
            // Assurez-vous d'utiliser une bibliothèque de chargement d'images pour charger les images de drapeaux
            // Par exemple, Glide
            // Glide.with(binding.root.context).load(country.flag.small).into(binding.countryFlag)
        }
    }
}
