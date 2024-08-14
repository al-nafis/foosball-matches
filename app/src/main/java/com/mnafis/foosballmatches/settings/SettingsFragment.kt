package com.mnafis.foosballmatches.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.MainActivity
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import javax.inject.Inject

class SettingsFragment : Fragment() {
    @Inject
    lateinit var playersRepository: PlayersRepository

    @Inject
    lateinit var matchesRepository: MatchesRepository

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as FoosballApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, settingsViewModelFactory)[SettingsViewModel::class]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).resetToolbarTrailerIcon()
        (activity as MainActivity).setToolbarTitle(R.string.menu_title_settings)

        setupUi()
    }

    private fun setupUi() {
        val clearButton = activity?.findViewById<Button>(R.id.fragment_settings_clear_data_button)
        val populateButton = activity?.findViewById<Button>(R.id.fragment_settings_populate_data_button)

        clearButton?.setOnClickListener {
            viewModel.clearDatabase()
        }

        populateButton?.setOnClickListener {
            viewModel.populateDatabase()
        }

        viewModel.isDatabaseEmpty.observe(viewLifecycleOwner) {
            clearButton?.isEnabled = !it
            populateButton?.isEnabled = it
        }
        viewModel.checkIfDatabaseIsEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeDisposables()
    }
}