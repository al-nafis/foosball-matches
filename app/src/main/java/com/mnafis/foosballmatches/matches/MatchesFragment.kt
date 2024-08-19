package com.mnafis.foosballmatches.matches

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.MainActivity
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.ToolbarTrailerIcon
import com.mnafis.foosballmatches.ViewModelFactory
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.matches.details.MatchDetailsActivity
import javax.inject.Inject

class MatchesFragment : Fragment() {
    @Inject
    lateinit var playersRepository: PlayersRepository

    @Inject
    lateinit var matchesRepository: MatchesRepository

    @Inject
    lateinit var matchesRecyclerAdapter: MatchesRecyclerAdapter

    private lateinit var viewModel: MatchesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as FoosballApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, ViewModelFactory {
            MatchesViewModel(
                matchesRepository,
                playersRepository
            )
        })[MatchesViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onResume() {
        super.onResume()

        setupToolbar()
        setupMatchList()
        viewModel.fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeDisposables()
    }

    private fun setupToolbar() {
        val activity = activity as MainActivity
        activity.setToolbarTitle(R.string.menu_title_matches)
        activity.setToolbarTrailerIcon(ToolbarTrailerIcon.ADD) {
            val players = viewModel.matchesAndPlayers.value?.second
            if (players == null || players.size < 2) {
                AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.players_list_empty_message_title))
                    .setMessage(getString(R.string.players_list_not_enough_players_message))
                    .setPositiveButton(getString(R.string.players_list_dialog_dismiss_button)) { dialog, _ ->
                        dialog.dismiss()
                    }.create()
                    .show()
            } else {
                val intent = Intent(activity, MatchDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupMatchList() {
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.fragment_matches_recycler_view)
        val emptyListMessageTextView = activity?.findViewById<TextView>(R.id.fragment_matches_list_empty_message)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = matchesRecyclerAdapter

        viewModel.matchesAndPlayers.observe(viewLifecycleOwner) { pair ->
            if (pair.first.isEmpty()) {
                emptyListMessageTextView?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
            } else {
                emptyListMessageTextView?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
            }
            matchesRecyclerAdapter.setData(pair.first, pair.second)
        }

        matchesRecyclerAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(MatchDetailsActivity.MATCH_DETAILS_EDIT, it)
            }
            val intent = Intent(activity, MatchDetailsActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }
    }
}