package com.mnafis.foosballmatches.players.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mnafis.foosballmatches.BaseViewModel
import com.mnafis.foosballmatches.database.players.PlayerIdExistsException
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.models.Player

class PlayerDetailsViewModel(
    private val playersRepository: PlayersRepository
) : BaseViewModel() {
    private val _onSuccessSubmit = MutableLiveData<Boolean>()
    val onSuccessSubmit: LiveData<Boolean> = _onSuccessSubmit

    private val _errorMessage = MutableLiveData(ErrorType.NONE)
    val errorMessage: LiveData<ErrorType> = _errorMessage

    private val _employeeId = MutableLiveData<Int?>()
    val employeeId: LiveData<Int?> = _employeeId

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name

    var isEdit = false
    var editablePlayer: Player? = null
    var players = emptyList<Player>()

    init {
        this addDisposable playersRepository.getAllPlayers()
            .subscribe(
                { players = it },
                { it.printStackTrace() }
            )
    }

    fun submit() {
        if (validate()) {
            this addDisposable if (isEdit) {
                playersRepository.updatePlayer(
                    editablePlayer!!.copy(name = _name.value!!.lowercase().replaceFirstChar { it.uppercase() })
                ).subscribe(
                    { _onSuccessSubmit.postValue(true) },
                    { _errorMessage.postValue(ErrorType.GENERIC) }
                )
            } else {
                playersRepository.addNewPlayer(
                    Player(
                        employeeId = _employeeId.value!!,
                        name = _name.value!!.lowercase().replaceFirstChar { it.uppercase() }
                    )
                ).subscribe(
                    { _onSuccessSubmit.postValue(true) },
                    {
                        if (it is PlayerIdExistsException) {
                            _errorMessage.postValue(ErrorType.DUPLICATE_ID)
                        } else {
                            _errorMessage.postValue(ErrorType.GENERIC)
                        }
                    }
                )
            }
        }
    }

    fun deletePlayer() {
        this addDisposable playersRepository.deletePlayer(editablePlayer!!)
            .subscribe(
                { _onSuccessSubmit.postValue(true) },
                { _errorMessage.postValue(ErrorType.GENERIC) }
            )
    }

    fun setEmployeeId(id: Int?) {
        _employeeId.value = id
    }

    fun setName(name: String?) {
        _name.value = name
    }

    private fun validate(): Boolean {
        _errorMessage.postValue(ErrorType.NONE)
        if (_employeeId.value == null) {
            _errorMessage.postValue(ErrorType.ID)
            return false
        }
        if (name.value.isNullOrEmpty()) {
            _errorMessage.postValue(ErrorType.NAME)
            return false
        }
        return true
    }

    enum class ErrorType {
        NONE, ID, NAME, GENERIC, DUPLICATE_ID
    }
}