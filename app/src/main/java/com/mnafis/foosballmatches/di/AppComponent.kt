package com.mnafis.foosballmatches.di

import com.mnafis.foosballmatches.MainActivity
import com.mnafis.foosballmatches.matches.MatchesFragment
import com.mnafis.foosballmatches.matches.details.MatchDetailsActivity
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
}