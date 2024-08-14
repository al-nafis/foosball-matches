package com.mnafis.foosballmatches.matches.details

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.BaseActivity
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.ToolbarNavigationIcon
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.tools.getFormattedDate
import java.util.Calendar
import javax.inject.Inject

class MatchDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: MatchDetailsViewModelFactory

    @Inject
    lateinit var playerItemAdapter: MatchesDetailsPlayersDialogRecyclerAdapter

    private lateinit var viewModel: MatchDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as FoosballApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MatchDetailsViewModel::class]
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
        setupListeners()

        val match = intent.getSerializableExtra(MATCH_DETAILS_EDIT) as? Match
        match?.let {
            viewModel.isEdit = true
            viewModel.editableMatch = it
        }

        viewModel.players.observe(this) {
            playerItemAdapter.setData(it)
        }
    }

    private fun setupListeners() {
        val dateInputView: TextView = findViewById(R.id.activity_match_details_edit_date)
        val player1Info: TextView = findViewById(R.id.activity_match_details_edit_player_1)
        val player1Score: EditText = findViewById(R.id.activity_match_details_edit_player_1_score)
        val player2Info: TextView = findViewById(R.id.activity_match_details_edit_player_2)
        val player2Score: EditText = findViewById(R.id.activity_match_details_edit_player_2_score)
        val submitButton: Button = findViewById(R.id.match_details_button_submit)

        dateInputView.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                dateInputView.text = getFormattedDate(year, month, day)
                viewModel.year = year
                viewModel.month = month
                viewModel.day = day
            }

            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
            ).apply {
                datePicker.maxDate = System.currentTimeMillis()
                show()
            }
        }

        player1Info.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.match_details_dialog_player_layout, null)

            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()

            val recyclerView = dialogView.findViewById<RecyclerView>(R.id.match_details_dialog_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = playerItemAdapter
            playerItemAdapter.setOnClickListener { selectedPlayer ->
                viewModel.player1Id = selectedPlayer.employeeId
                player1Info.text = getString(R.string.match_details_players_view_item_text, selectedPlayer.employeeId, selectedPlayer.name)
                dialog.dismiss()
            }

            dialog.show()
        }

        player2Info.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.match_details_dialog_player_layout, null)

            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()

            val recyclerView = dialogView.findViewById<RecyclerView>(R.id.match_details_dialog_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = playerItemAdapter
            playerItemAdapter.setOnClickListener { selectedPlayer ->
                viewModel.player2Id = selectedPlayer.employeeId
                player2Info.text = getString(R.string.match_details_players_view_item_text, selectedPlayer.employeeId, selectedPlayer.name)
                dialog.dismiss()
            }

            dialog.show()
        }

        submitButton.setOnClickListener {
            viewModel.player1Score = player1Score.text.toString().toIntOrNull()
            viewModel.player2Score = player2Score.text.toString().toIntOrNull()
            viewModel.submit()
        }

        viewModel.onSuccessSubmit.observe(this) {
            if (it) {
                finish()
            } else {
                //todo: Error
            }
        }
    }

    companion object {
        const val MATCH_DETAILS_EDIT = "match_details_edit"
    }
}