package com.mnafis.foosballmatches

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IdRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mnafis.foosballmatches.matches.MatchesFragment
import com.mnafis.foosballmatches.players.PlayersFragment
import com.mnafis.foosballmatches.ranking.RankingFragment
import com.mnafis.foosballmatches.settings.SettingsFragment

class MainActivity : BaseActivity() {

    @IdRes
    private var currentTab: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as FoosballApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.activity_main_bottom_navigation_view)
        bottomNavigationView.itemActiveIndicatorColor = null

        bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedTab = when (item.itemId) {
                R.id.menu_item_matches -> MatchesFragment()
                R.id.menu_item_ranking -> RankingFragment()
                R.id.menu_item_players -> PlayersFragment()
                R.id.menu_item_settings -> SettingsFragment()
                else -> null
            }

            selectedTab?.let {
                if (item.itemId != currentTab) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment_container, it)
                        .commit()
                    currentTab = item.itemId
                }
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.menu_item_matches
    }
}