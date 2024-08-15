package com.mnafis.foosballmatches.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mnafis.foosballmatches.BaseViewModel
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.models.Player


class PlayersViewModel(
    private val playersRepository: PlayersRepository
) : BaseViewModel() {

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players

    fun fetchData() {
        this addDisposable playersRepository.getAllPlayers()
            .subscribe(
                { list -> _players.postValue(list) },
                { error -> error.printStackTrace() }
            )
    }
}