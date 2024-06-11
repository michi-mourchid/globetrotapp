package com.android.flagcountryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.flagcountryapp.models.Favoris
import com.android.flagcountryapp.models.Pays
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    lateinit var retour : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)


        var bundle : Bundle = intent.extras!!
        var drapeauView: ImageView = findViewById(R.id.detail_drapeau)
        var detail_drapeau = bundle.getString("detail_drapeau")
        Glide.with(this).load(detail_drapeau).into(drapeauView)

        var nomPaysView: TextView = findViewById(R.id.nom_pays)
        var nom_pays = bundle.getString("nom_pays")
        nomPaysView.text = nom_pays

        var nomOfficielView: TextView = findViewById(R.id.nom_officiel)
        var nom_officiel = bundle.getString("nom_officiel")
        nomOfficielView.text = nom_officiel

        var iso2View: TextView = findViewById(R.id.iso2)
        var iso2 = bundle.getString("iso2")
        iso2View.text = iso2

        var iso3View: TextView = findViewById(R.id.iso3)
        var iso3 = bundle.getString("iso3")
        iso3View.text = iso3

        var numericodeView: TextView = findViewById(R.id.numericode)
        var numericode = bundle.getString("numericode")
        numericodeView.text = numericode

        var codeappelView: TextView = findViewById(R.id.codeappel)
        var codeappel = bundle.getString("codeappel")
        codeappelView.text = codeappel

        var regionView: TextView = findViewById(R.id.region)
        var region = bundle.getString("region")
        regionView.text = region

        var capitalView: TextView = findViewById(R.id.capitale)
        var capital = bundle.getString("capital")
        capitalView.text = capital

        var horaireView: TextView = findViewById(R.id.horaire)
        var horaire = bundle.getString("horaire")
        horaireView.text = horaire

        var populationView: TextView = findViewById(R.id.population)
        var population: Int = bundle.getInt("population")
        populationView.text = population.toString()

        var ajouterFavoris : Button = findViewById(R.id.button_ajout_favoris)
        var pays_retourne : Pays? = Favoris.find(nom_pays.toString())
        var est_favoris : Boolean = false
        if (pays_retourne!=null){
            ajouterFavoris.setText("Enlever des favoris")
            est_favoris = true
        }
        ajouterFavoris.setOnClickListener {
            if (est_favoris){
                Favoris.remove(pays_retourne!!)
                ajouterFavoris.setText("Ajouter aux favoris")
            } else {
                var pays :Pays = PaysFragment.find(nom_pays.toString())!!
                Favoris.add(pays)
                ajouterFavoris.setText("Enlever des favoris")
            }
        }

        retour = findViewById(R.id.image_retour)
        retour.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }


    }
}