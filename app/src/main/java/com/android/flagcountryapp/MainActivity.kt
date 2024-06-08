package com.android.flagcountryapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.ListFragment
import com.android.flagcountryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//AjUxVCug9IgZZglt0tGgOx2BdA7Nx8BHjDZKkx3O
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(AccueilFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.accueil -> {
                    replaceFragment(AccueilFragment())
                    true
                }
                R.id.ajout -> {
                    replaceFragment(AjoutFragment())
                    true
                }
                R.id.liste -> {
                    replaceFragment(PaysFragment())
                    true
                }
                R.id.favoris -> {
                    replaceFragment(FavorisFragment())
                    true
                }

                else -> {
                    true
                }
            }


        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }
}