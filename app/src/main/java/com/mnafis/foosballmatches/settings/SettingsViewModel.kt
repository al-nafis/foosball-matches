package com.mnafis.foosballmatches.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mnafis.foosballmatches.BaseViewModel
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.tools.sampleMatches
import com.mnafis.foosballmatches.tools.samplePlayers
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


class SettingsViewModel(
    private val matchesRepository: MatchesRepository,
    private val playersRepository: PlayersRepository
) : BaseViewModel() {

    private val _isDatabaseEmpty = MutableLiveData<Boolean>()
    val isDatabaseEmpty: LiveData<Boolean> = _isDatabaseEmpty

    fun checkIfDatabaseIsEmpty() {
        this addDisposable Single.zip(
            matchesRepository.getAllMatches(),
            playersRepository.getAllPlayers()
        ) { matches, players ->
            matches.isEmpty() && players.isEmpty()
        }.subscribe(
            { _isDatabaseEmpty.postValue(it) },
            { it.printStackTrace() }
        )
    }

    fun clearDatabase() {
        this addDisposable playersRepository.deleteAllPlayers()
            .andThen(matchesRepository.deleteAllMatches())
            .subscribe(
                { _isDatabaseEmpty.postValue(true) },
                { it.printStackTrace() }
            )
    }

    fun populateDatabase() {
        val disposable = CompositeDisposable()
        disposable.add(playersRepository.getAllPlayers()
            .flatMapCompletable { players ->
                if (players.isEmpty()) {
                    playersRepository.addNewPlayers(samplePlayers)
                } else {
                    Completable.error(Exception("Players exist already"))
                }
            }.andThen(
                matchesRepository.getAllMatches()
                    .flatMapCompletable { matches ->
                        if (matches.isEmpty()) {
                            matchesRepository.addNewMatches(sampleMatches)
                        } else {
                            Completable.error(Exception("Matches exist already"))
                        }
                    }
            ).subscribe(
                {
                    println("ABID: sample players added")
                    _isDatabaseEmpty.postValue(false)
                    disposable.dispose()
                },
                { println("ABID: sample players adding error: ${it.message}") }
            )
        )
    }
}