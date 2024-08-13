//package com.mnafis.foosballmatches.di
//
//import androidx.fragment.app.Fragment
//import com.mnafis.foosballmatches.matches.MatchesFragment
//import com.mnafis.foosballmatches.players.PlayersFragment
//import com.mnafis.foosballmatches.ranking.RankingFragment
//import com.mnafis.foosballmatches.settings.SettingsFragment
//import dagger.Binds
//import dagger.Module
//import dagger.multibindings.ClassKey
//import dagger.multibindings.IntoMap
//
//@Module
//abstract class FragmentModule {
//    @Binds
//    @IntoMap
//    @ClassKey(MatchesFragment::class)
//    abstract fun bindMatchesFragment(fragment: MatchesFragment): Fragment
//
//    @Binds
//    @IntoMap
//    @ClassKey(RankingFragment::class)
//    abstract fun bindRankingFragment(fragment: RankingFragment): Fragment
//
//    @Binds
//    @IntoMap
//    @ClassKey(PlayersFragment::class)
//    abstract fun bindPlayersFragment(fragment: PlayersFragment): Fragment
//
//    @Binds
//    @IntoMap
//    @ClassKey(SettingsFragment::class)
//    abstract fun bindSettingsFragment(fragment: SettingsFragment): Fragment
//}