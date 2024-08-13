package com.mnafis.foosballmatches

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.matches.MatchesFragment
import com.mnafis.foosballmatches.players.PlayersFragment
import com.mnafis.foosballmatches.ranking.RankingFragment
import com.mnafis.foosballmatches.settings.SettingsFragment
import com.mnafis.foosballmatches.tools.sampleMatches
import com.mnafis.foosballmatches.tools.samplePlayers
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var playersRepository: PlayersRepository

    @Inject
    lateinit var matchesRepository: MatchesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as FoosballApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupBottomNavigationView()
        populateSampleDataIfThereIsNone()
    }

    fun setToolbarTitle(@StringRes title: Int) {
        val toolBarTitle: TextView? = findViewById(R.id.toolbar_title)
        toolBarTitle?.text = getString(title)
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
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_main_fragment_container, it)
                    .commit()
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.menu_item_matches
    }

    //sample data entry
    private val disposable = CompositeDisposable()
    private fun populateSampleDataIfThereIsNone() {
        disposable.addAll(
            playersRepository.getAllPlayers()
                .flatMapCompletable { players ->
                    if (players.isEmpty()) {
                        playersRepository.addNewPlayers(samplePlayers)
                    } else {
                        Completable.error(Exception("Players exist already"))
                    }
                }.subscribe(
                    { println("ABID: sample players added") },
                    { println("ABID: sample players adding error: ${it.message}") }
                ),
            matchesRepository.getAllMatches().flatMapCompletable { matches ->
                if (matches.isEmpty()) {
                    matchesRepository.addNewMatches(sampleMatches)
                } else {
                    Completable.error(Exception("Matches exist already"))
                }
            }.subscribe(
                {
                    println("ABID: sample matches added")
                    disposable.dispose()
                },
                { println("ABID: sample matches adding error: ${it.message}") }
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}