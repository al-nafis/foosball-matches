package com.mnafis.foosballmatches.di

import com.mnafis.foosballmatches.MainActivity
import com.mnafis.foosballmatches.matches.MatchesFragment
import com.mnafis.foosballmatches.matches.details.MatchDetailsActivity
import com.mnafis.foosballmatches.players.PlayersFragment
import com.mnafis.foosballmatches.ranking.RankingFragment
import com.mnafis.foosballmatches.settings.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    SharedModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: MatchDetailsActivity)
    fun inject(fragment: MatchesFragment)
    fun inject(fragment: RankingFragment)
    fun inject(fragment: PlayersFragment)
    fun inject(fragment: SettingsFragment)
}