package com.mnafis.foosballmatches.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.MainActivity
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.tools.sampleMatches
import com.mnafis.foosballmatches.tools.samplePlayers
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SettingsFragment : Fragment() {
    @Inject
    lateinit var playersRepository: PlayersRepository

    @Inject
    lateinit var matchesRepository: MatchesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onAttach(context: Context) {
        (activity?.application as FoosballApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(R.string.menu_title_settings)
        populateSampleDataIfThereIsNone()
    }

    //sample data entry
    private val disposable = CompositeDisposable()
    private fun populateSampleDataIfThereIsNone() {
        disposable.addAll(
            playersRepository.getAllPlayers()
                .flatMapCompletable { players ->
                    if (players.isEmpty()) {
                        playersRepository.addNewPlayers(samplePlayers)
                    } else {
                        Completable.error(Exception("Players exist already"))
                    }
                }.subscribe(
                    { println("ABID: sample players added") },
                    { println("ABID: sample players adding error: ${it.message}") }
                ),
            matchesRepository.getAllMatches().flatMapCompletable { matches ->
                if (matches.isEmpty()) {
                    matchesRepository.addNewMatches(sampleMatches)
                } else {
                    Completable.error(Exception("Matches exist already"))
                }
            }.subscribe(
                {
                    println("ABID: sample matches added")
                    disposable.dispose()
                },
                { println("ABID: sample matches adding error: ${it.message}") }
            )
        )
    }
}