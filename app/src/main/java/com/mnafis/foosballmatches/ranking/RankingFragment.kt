package com.mnafis.foosballmatches.ranking

import android.app.AlertDialog
import android.content.Context
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
import javax.inject.Inject

class RankingFragment : Fragment() {

    @Inject
    lateinit var rankingViewModelFactory: RankingViewModelFactory

    @Inject
    lateinit var rankingRecyclerAdapter: RankingRecyclerAdapter

    @Inject
    lateinit var rankingSortTypeDialogRecyclerAdapter: RankingSortTypeDialogRecyclerAdapter

    private lateinit var viewModel: RankingViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as FoosballApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, rankingViewModelFactory)[RankingViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeDisposables()
    }

    override fun onResume() {
        super.onResume()

        setupToolbar()
        setupPlayerList()
        viewModel.fetchData()
    }

    private fun setupToolbar() {
        val activity = activity as MainActivity
        activity.setToolbarTitle(R.string.menu_title_ranking)
        activity.setToolbarTrailerIcon(ToolbarTrailerIcon.SORT) {
            val dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.recycler_text_dialog_layout, null)

            val dialog = AlertDialog.Builder(activity)
                .setView(dialogView)
                .create()

            val recyclerViewHeader = dialogView.findViewById<TextView>(R.id.recycler_dialog_title)
            recyclerViewHeader.text = activity.getString(R.string.ranking_sort_type_dialog_header)
            val recyclerView =
                dialogView.findViewById<RecyclerView>(R.id.recycler_dialog_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = rankingSortTypeDialogRecyclerAdapter
            rankingSortTypeDialogRecyclerAdapter.setOnClickListener {
                viewModel.setSortType(it)
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private fun setupPlayerList() {
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.fragment_ranking_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = rankingRecyclerAdapter
        viewModel.players.observe(viewLifecycleOwner) { players ->
            rankingRecyclerAdapter.setData(players)
        }
    }
}