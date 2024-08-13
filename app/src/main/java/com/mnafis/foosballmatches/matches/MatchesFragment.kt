package com.mnafis.foosballmatches.matches

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.MainActivity
import com.mnafis.foosballmatches.R
import javax.inject.Inject

class MatchesFragment : Fragment() {
    @Inject
    lateinit var matchesViewModelFactory: MatchesViewModelFactory

    private lateinit var viewModel: MatchesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as FoosballApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, matchesViewModelFactory)[MatchesViewModel::class]

        viewModel.doThis()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(R.string.menu_title_matches)
    }
}