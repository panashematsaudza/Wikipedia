package com.panashematsaudza.wikipedia.activities


import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity

import com.panashematsaudza.wikipedia.R
import com.panashematsaudza.wikipedia.fragments.ExploreFragment
import com.panashematsaudza.wikipedia.fragments.FavouritesFragment
import com.panashematsaudza.wikipedia.fragments.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment = ExploreFragment()
    private val favouritesFragment: FavouritesFragment = FavouritesFragment()
    private val historyFragment: HistoryFragment = HistoryFragment()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

            when (item.itemId) {
                R.id.navigation_explore -> transaction.replace(
                    R.id.fragment_container,
                    exploreFragment
                )
                R.id.navigation_favourites -> transaction.replace(
                    R.id.fragment_container,
                    favouritesFragment
                )
                R.id.navigation_history -> transaction.replace(
                    R.id.fragment_container,
                    historyFragment
                )

            }

            transaction.commit()
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragment_container, exploreFragment)
        transaction.commit()
    }
}
