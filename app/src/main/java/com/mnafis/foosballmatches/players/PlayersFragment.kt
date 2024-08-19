package com.mnafis.foosballmatches.players

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
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.players.details.PlayerDetailsActivity
import javax.inject.Inject

class PlayersFragment : Fragment() {

    @Inject
    lateinit var playersRepository: PlayersRepository

    @Inject
    lateinit var playersRecyclerAdapter: PlayersRecyclerAdapter

    private lateinit var viewModel: PlayersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as FoosballApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, ViewModelFactory {
            PlayersViewModel(playersRepository)
        })[PlayersViewModel::class]
    }

    override fun onResume() {
        super.onResume()

        setupToolbar()
        setupPlayersList()
        viewModel.fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeDisposables()
    }

    private fun setupPlayersList() {
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.fragment_players_recycler_view)
        val emptyListMessageTextView = activity?.findViewById<TextView>(R.id.fragment_players_list_empty_message)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = playersRecyclerAdapter

        viewModel.players.observe(viewLifecycleOwner) { players ->
            if (players.isEmpty()) {
                emptyListMessageTextView?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
            } else {
                emptyListMessageTextView?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
            }
            playersRecyclerAdapter.setData(players)
        }

        playersRecyclerAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(PlayerDetailsActivity.PLAYER_DETAILS_EDIT, it)
            }
            val intent = Intent(activity, PlayerDetailsActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }
    }

    private fun setupToolbar() {
        val activity = activity as MainActivity
        activity.setToolbarTitle(R.string.menu_title_players)
        activity.setToolbarTrailerIcon(ToolbarTrailerIcon.ADD) {
            val intent = Intent(activity, PlayerDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}