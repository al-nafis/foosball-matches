package com.mnafis.foosballmatches.matches

import androidx.lifecycle.ViewModel
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.models.DateInfo
import com.mnafis.foosballmatches.models.Match
import io.reactivex.disposables.CompositeDisposable

class MatchesViewModel(
    private val matchesRepository: MatchesRepository
) : ViewModel() {
    fun doThis() {
        val disposable = CompositeDisposable()
        disposable.add(
            matchesRepository.addNewMatch(
                Match(
                    id = 312803721837,
                    dateInfo = DateInfo(1, 1, 1, 1, 1),
                    player1Id = 21212,
                    player1Score = 2,
                    player2Id = 2121212,
                    player2Score = 5,
                    winnerId = 2121212
                )
            ).subscribe(
                {
                    println("ABID: job well done")
                },
                { error ->
                    println("ABID: ${error.message}")
                }
            )
        )

        matchesRepository.getAllMatches().subscribe(
            { list ->
                println("ABID: ${list.first().id}")
            },
            { error ->
                println("ABID: ${error.message}")
            }
        )
    }
}