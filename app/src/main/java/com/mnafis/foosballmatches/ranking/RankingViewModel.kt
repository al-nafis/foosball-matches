package com.mnafis.foosballmatches.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mnafis.foosballmatches.BaseViewModel
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.models.Player


class RankingViewModel(
    private val playersRepository: PlayersRepository
) : BaseViewModel() {

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players



    fun fetchData() {
        this addDisposable playersRepository.getAllPlayers()
            .subscribe(
                { list -> _players.postValue(list.sortedByDescending { it.wins }) },
                { error -> error.printStackTrace() }
            )
    }

    fun setSortType(type: SortType) {
        _players.postValue(_players.value!!.sortedByDescending {
            when (type) {
                SortType.MOST_WINS -> it.wins
                SortType.MOST_LOSSES -> it.losses
                SortType.MOST_PLAYED -> it.totalMatchesPlayed
            }
        })
    }

    enum class SortType {
        MOST_WINS, MOST_LOSSES, MOST_PLAYED
    }
}