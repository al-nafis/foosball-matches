package com.mnafis.foosballmatches.matches.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mnafis.foosballmatches.BaseActivity
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.ToolbarNavigationIcon
import com.mnafis.foosballmatches.models.Match

class MatchDetailsActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as FoosballApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_match_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.match_details)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setToolbarNavigationIcon(ToolbarNavigationIcon.BACK) {
            finish()
        }
        setToolbarTitle(R.string.match_details)

        val match = intent.getSerializableExtra(MATCH_DETAILS_EDIT) as? Match
        match?.let {
            println("ABID: Match Details: ${it.id} | ${it.winner}")
        }
    }

    companion object {
        const val MATCH_DETAILS_EDIT = "match_details_edit"
    }
}