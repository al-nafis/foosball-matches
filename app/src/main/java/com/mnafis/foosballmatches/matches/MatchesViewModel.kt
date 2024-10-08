package com.mnafis.foosballmatches.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mnafis.foosballmatches.BaseViewModel
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player
import io.reactivex.Single
import java.time.LocalDate

class MatchesViewModel(
    private val matchesRepository: MatchesRepository,
    private val playersRepository: PlayersRepository
) : BaseViewModel() {

    private val _matchesAndPlayers = MutableLiveData<Pair<List<Match>, List<Player>>>()
    val matchesAndPlayers: LiveData<Pair<List<Match>, List<Player>>> = _matchesAndPlayers

    fun fetchData() {
        this addDisposable Single.zip(
            matchesRepository.getAllMatches(),
            playersRepository.getAllPlayers()
        ) { matches, players ->
            val sortedMatches = matches
                .sortedWith(
                    compareByDescending<Match> {
                        LocalDate.of(it.dateInfo.year, it.dateInfo.month + 1, it.dateInfo.day)
                    }
                        .thenByDescending { it.id }
                )
            Pair(sortedMatches, players)
        }.subscribe(
                { pair -> _matchesAndPlayers.postValue(pair) },
                { error -> error.printStackTrace() }
            )
    }
}