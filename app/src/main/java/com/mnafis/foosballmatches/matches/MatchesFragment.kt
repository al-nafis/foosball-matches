package com.mnafis.foosballmatches.matches

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.MainActivity
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.ToolbarTrailerIcon
import com.mnafis.foosballmatches.matches.details.MatchDetailsActivity
import javax.inject.Inject

class MatchesFragment : Fragment() {
    @Inject
    lateinit var matchesViewModelFactory: MatchesViewModelFactory

    @Inject
    lateinit var matchesRecyclerAdapter: MatchesRecyclerAdapter

    private lateinit var viewModel: MatchesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as FoosballApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, matchesViewModelFactory)[MatchesViewModel::class]
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

        val activity = activity as MainActivity
        activity.setToolbarTitle(R.string.menu_title_matches)
        activity.resetToolbarTrailerIcon()
        activity.setToolbarTrailerIcon(ToolbarTrailerIcon.ADD) {
            val intent = Intent(activity, MatchDetailsActivity::class.java)
            startActivity(intent)
        }

        setupMatchList()
        viewModel.fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeDisposables()
    }

    private fun setupMatchList() {
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.fragment_matches_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = matchesRecyclerAdapter
        viewModel.matchesAndPlayers.observe(viewLifecycleOwner) { pair ->
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