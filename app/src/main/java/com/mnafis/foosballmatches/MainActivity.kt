package com.mnafis.foosballmatches

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mnafis.foosballmatches.matches.MatchesFragment
import com.mnafis.foosballmatches.players.PlayersFragment
import com.mnafis.foosballmatches.ranking.RankingFragment
import com.mnafis.foosballmatches.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.activity_main_bottom_navigation_view)
        bottomNavigationView.itemActiveIndicatorColor = null

        bottomNavigationView.setOnItemSelectedListener { item ->
            val selected = when (item.itemId) {
                R.id.menu_item_matches -> MatchesFragment()
                R.id.menu_item_ranking -> RankingFragment()
                R.id.menu_item_players -> PlayersFragment()
                R.id.menu_item_settings -> SettingsFragment()
                else -> null
            }

            selected?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.menu_item_matches
    }
}