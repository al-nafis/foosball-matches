package com.mnafis.foosballmatches.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.database.players.PlayersRepository
import javax.inject.Inject


class PlayersViewModelFactory @Inject constructor(
    private val playersRepository: PlayersRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        PlayersViewModel(playersRepository) as T
}