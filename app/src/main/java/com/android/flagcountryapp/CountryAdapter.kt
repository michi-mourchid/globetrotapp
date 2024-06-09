import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.android.flagcountryapp.Pays
import com.android.flagcountryapp.R
import com.android.flagcountryapp.databinding.FragmentPaysBinding

class CountryAdapter(
    private val countries: List<Pays>
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_pays, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val pays : Pays = countries[position]
        holder.bind(pays)
    }

    override fun getItemCount(): Int = countries.size

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pays: Pays) {
            itemView.findViewById<TextView>(R.id.tvCountryIso).text = pays.alpha2Code
            itemView.findViewById<TextView>(R.id.tvCountryName).text = pays.name
        }
    }
}
