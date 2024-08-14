package com.mnafis.foosballmatches.matches.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mnafis.foosballmatches.BaseViewModel
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.models.DateInfo
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player

class MatchDetailsViewModel(
    private val matchesRepository: MatchesRepository,
    private val playersRepository: PlayersRepository
) : BaseViewModel() {
    private val _onSuccessSubmit = MutableLiveData<Boolean>()
    val onSuccessSubmit: LiveData<Boolean> = _onSuccessSubmit

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players

    var month = 0
    var day = 0
    var year = 0

    var player1Id: Int? = null
    var player2Id: Int? = null
    var player1Score: Int? = null
    var player2Score: Int? = null

    var isEdit = false
    var editableMatch: Match? = null

    init {
        this addDisposable playersRepository.getAllPlayers()
            .subscribe(
                { _players.postValue(it) },
                { it.printStackTrace() }
            )
    }

    fun submit() {
        if (validate()) {
            val winnerName: String
            val winner: Player
            val loser: Player

            if (player1Score!! > player2Score!!) {
                winnerName = players.value?.find { it.employeeId == player1Id }!!.name
                winner = players.value?.find { it.employeeId == player1Id }!!
                loser = players.value?.find { it.employeeId == player2Id }!!
            } else {
                winnerName = players.value?.find { it.employeeId == player2Id }!!.name
                winner = players.value?.find { it.employeeId == player2Id }!!
                loser = players.value?.find { it.employeeId == player1Id }!!
            }



            this addDisposable
//                    if (isEdit) {
//                        matchesRepository.updateMatch()
//                    } else {
                        matchesRepository.addNewMatch(
                            Match(
                                id = System.currentTimeMillis(),
                                dateInfo = DateInfo(day, month, year),
                                player1Id = player1Id!!,
                                player1Score = player1Score!!,
                                player2Id = player2Id!!,
                                player2Score = player2Score!!,
                                winner = winnerName
                            )
                        ).andThen(
                            playersRepository.updatePlayer(winner.copy(
                                totalMatchesPlayed = winner.totalMatchesPlayed + 1,
                                wins = winner.wins + 1
                            ))
                        ).andThen(
                            playersRepository.updatePlayer(loser.copy(
                                totalMatchesPlayed = loser.totalMatchesPlayed + 1,
                                losses = loser.losses + 1
                            ))
                        ).subscribe(
                            { _onSuccessSubmit.postValue(true) },
                            { _onSuccessSubmit.postValue(false) }
                        )
//                    }
        }
    }

    private fun validate(): Boolean {
        return true
    }
}