package com.mnafis.foosballmatches.matches.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import javax.inject.Inject

class MatchDetailsViewModelFactory @Inject constructor(
    private val matchesRepository: MatchesRepository,
    private val playersRepository: PlayersRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MatchDetailsViewModel(matchesRepository, playersRepository) as T
}