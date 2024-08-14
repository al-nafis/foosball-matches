package com.mnafis.foosballmatches.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.database.players.PlayersRepository
import javax.inject.Inject


class RankingViewModelFactory @Inject constructor(
    private val playersRepository: PlayersRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RankingViewModel(playersRepository) as T
}