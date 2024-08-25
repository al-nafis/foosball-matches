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
    playersRepository: PlayersRepository
) : BaseViewModel() {
    private val _onSuccessSubmit = MutableLiveData<Boolean>()
    val onSuccessSubmit: LiveData<Boolean> = _onSuccessSubmit

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players

    private val _errorMessage = MutableLiveData(ErrorType.NONE)
    val errorMessage: LiveData<ErrorType> = _errorMessage

    private val _date = MutableLiveData<DateInfo>()
    val date: LiveData<DateInfo> = _date

    private val _player1 = MutableLiveData<Player>()
    val player1: LiveData<Player> = _player1

    private val _player2 = MutableLiveData<Player>()
    val player2: LiveData<Player> = _player2

    private val _player1Score = MutableLiveData<Int?>()
    val player1Score: LiveData<Int?> = _player1Score

    private val _player2Score = MutableLiveData<Int?>()
    val player2Score: LiveData<Int?> = _player2Score

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
            //Assuming in Foosball, there can not be a tie since the first to score a certain amount of goals wins
            val winnerId = if (player1Score.value!! > player2Score.value!!) {
                player1.value!!.employeeId
            } else {
                player2.value!!.employeeId
            }
            this addDisposable if (isEdit) {
                matchesRepository.updateMatch(
                    Match(
                        id = editableMatch!!.id,
                        dateInfo = _date.value!!,
                        player1Id = player1.value!!.employeeId,
                        player1Score = player1Score.value!!,
                        player2Id = player2.value!!.employeeId,
                        player2Score = player2Score.value!!,
                        winnerId = winnerId
                    )
                )
            } else {
                matchesRepository.addNewMatch(
                    Match(
                        id = System.currentTimeMillis(),
                        dateInfo = _date.value!!,
                        player1Id = player1.value!!.employeeId,
                        player1Score = player1Score.value!!,
                        player2Id = player2.value!!.employeeId,
                        player2Score = player2Score.value!!,
                        winnerId = winnerId
                    )
                )
            }.subscribe(
                { _onSuccessSubmit.postValue(true) },
                { _errorMessage.postValue(ErrorType.GENERIC) }
            )
        }
    }

    fun deleteMatch() {
        this addDisposable matchesRepository.deleteMatch(editableMatch!!)
            .subscribe(
                { _onSuccessSubmit.postValue(true) },
                { _errorMessage.postValue(ErrorType.GENERIC) }
            )
    }

    fun setDate(dateInfo: DateInfo) {
        _date.value = dateInfo
    }

    fun setPlayer1(player: Player) {
        _player1.value = player
    }

    fun setPlayer2(player: Player) {
        _player2.value = player
    }

    fun setPlayer1Score(score: Int?) {
        _player1Score.value = score
    }

    fun setPlayer2Score(score: Int?) {
        _player2Score.value = score
    }

    private fun validate(): Boolean {
        _errorMessage.postValue(ErrorType.NONE)
        if (_date.value == null) {
            _errorMessage.postValue(ErrorType.DATE)
            return false
        }
        if (player1.value == null || player2.value == null) {
            _errorMessage.postValue(ErrorType.PLAYER)
            return false
        }
        if (player1Score.value == null || player2Score.value == null) {
            _errorMessage.postValue(ErrorType.SCORE)
            return false
        }
        if (player1.value == player2.value) {
            _errorMessage.postValue(ErrorType.SAME_PLAYER)
            return false
        }
        if (player1Score.value == player2Score.value) {
            _errorMessage.postValue(ErrorType.SCORE_TIED)
            return false
        }
        return true
    }

    enum class ErrorType {
        NONE, DATE, PLAYER, SCORE, GENERIC, SCORE_TIED, SAME_PLAYER
    }
}