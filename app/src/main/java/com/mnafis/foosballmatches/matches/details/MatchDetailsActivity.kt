package com.mnafis.foosballmatches.matches.details

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
import com.mnafis.foosballmatches.ToolbarTrailerIcon
import com.mnafis.foosballmatches.matches.details.MatchDetailsViewModel.ErrorType
import com.mnafis.foosballmatches.models.DateInfo
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

    private lateinit var dateInputView: TextView
    private lateinit var player1Info: TextView
    private lateinit var player1Score: EditText
    private lateinit var player2Info: TextView
    private lateinit var player2Score: EditText

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

        dateInputView = findViewById(R.id.activity_match_details_edit_date)
        player1Info = findViewById(R.id.activity_match_details_edit_player_1)
        player1Score = findViewById(R.id.activity_match_details_edit_player_1_score)
        player2Info = findViewById(R.id.activity_match_details_edit_player_2)
        player2Score = findViewById(R.id.activity_match_details_edit_player_2_score)

        val match = intent.getSerializableExtra(MATCH_DETAILS_EDIT) as? Match
        match?.let {
            viewModel.isEdit = true
            viewModel.editableMatch = it
            player1Info.isEnabled = false
            player2Info.isEnabled = false
            viewModel.setDate(DateInfo(it.dateInfo.day, it.dateInfo.month, it.dateInfo.year))
            viewModel.setPlayer1Score(it.player1Score)
            viewModel.setPlayer2Score(it.player2Score)
            setToolbarTrailerIcon(ToolbarTrailerIcon.DELETE) {
                viewModel.deleteMatch()
            }
        }

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        val submitButton: Button = findViewById(R.id.match_details_button_submit)

        dateInputView.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                viewModel.setDate(DateInfo(day, month, year))
            }

            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                listener,
                viewModel.date.value?.year ?: calendar.get(Calendar.YEAR),
                viewModel.date.value?.month ?: calendar.get(Calendar.MONTH),
                viewModel.date.value?.day ?: calendar.get(Calendar.DAY_OF_MONTH),
            ).apply {
                datePicker.maxDate = System.currentTimeMillis()
                show()
            }
        }

        player1Info.setOnClickListener {
            if (!viewModel.isEdit) {
                val dialogView = LayoutInflater.from(this)
                    .inflate(R.layout.recycler_text_dialog_layout, null)

                val dialog = AlertDialog.Builder(this)
                    .setView(dialogView)
                    .create()

                val recyclerViewHeader = dialogView.findViewById<TextView>(R.id.recycler_dialog_title)
                recyclerViewHeader.text = getString(R.string.activity_match_details_edit_player_hint)
                val recyclerView =
                    dialogView.findViewById<RecyclerView>(R.id.recycler_dialog_recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = playerItemAdapter
                playerItemAdapter.setOnClickListener { selectedPlayer ->
                    viewModel.setPlayer1(selectedPlayer)
                    dialog.dismiss()
                }

                dialog.show()
            }
        }

        player2Info.setOnClickListener {
            if (!viewModel.isEdit) {
                val dialogView = LayoutInflater.from(this)
                    .inflate(R.layout.recycler_text_dialog_layout, null)

                val dialog = AlertDialog.Builder(this)
                    .setView(dialogView)
                    .create()

                val recyclerViewHeader = dialogView.findViewById<TextView>(R.id.recycler_dialog_title)
                recyclerViewHeader.text = getString(R.string.activity_match_details_edit_player_hint)
                val recyclerView =
                    dialogView.findViewById<RecyclerView>(R.id.recycler_dialog_recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = playerItemAdapter
                playerItemAdapter.setOnClickListener { selectedPlayer ->
                    viewModel.setPlayer2(selectedPlayer)
                    dialog.dismiss()
                }

                dialog.show()
            }
        }

        submitButton.setOnClickListener {
            viewModel.setPlayer1Score(player1Score.text.toString().toIntOrNull())
            viewModel.setPlayer2Score(player2Score.text.toString().toIntOrNull())
            viewModel.submit()
        }
    }

    private fun setupObservers() {
        viewModel.players.observe(this) { players ->
            playerItemAdapter.setData(players)
            if (viewModel.isEdit) {
                val p1 = players.find { viewModel.editableMatch?.player1Id == it.employeeId }!!
                val p2 = players.find { viewModel.editableMatch?.player2Id == it.employeeId }!!
                viewModel.setPlayer1(p1)
                viewModel.setPlayer2(p2)
                player1Info.text = getString(
                    R.string.match_details_players_view_item_text,
                    p1.employeeId,
                    p1.name
                )
                player2Info.text = getString(
                    R.string.match_details_players_view_item_text,
                    p2.employeeId,
                    p2.name
                )
            }
        }

        val errorView: TextView = findViewById(R.id.match_details_error_text)
        viewModel.errorMessage.observe(this) { message ->
            val msg = when (message) {
                ErrorType.DATE -> R.string.activity_match_details_error_message_date
                ErrorType.PLAYER -> R.string.activity_match_details_error_message_player
                ErrorType.SCORE -> R.string.activity_match_details_error_message_score
                ErrorType.GENERIC -> R.string.error_message_generic
                ErrorType.SCORE_TIED -> R.string.activity_match_details_error_message_score_tied
                ErrorType.SAME_PLAYER -> R.string.activity_match_details_error_message_same_player
                ErrorType.NONE -> null
            }
            msg?.let {
                errorView.text = getString(it)
                errorView.visibility = View.VISIBLE
            } ?: run {
                errorView.text = ""
                errorView.visibility = View.GONE
            }
        }

        viewModel.date.observe(this) {
            dateInputView.text = getFormattedDate(it)
        }

        viewModel.player1.observe(this) {
            player1Info.text = getString(
                R.string.match_details_players_view_item_text,
                it.employeeId,
                it.name
            )
        }

        viewModel.player1Score.observe(this) {
            player1Score.setText((it ?: "").toString())
        }

        viewModel.player2.observe(this) {
            player2Info.text = getString(
                R.string.match_details_players_view_item_text,
                it.employeeId,
                it.name
            )
        }

        viewModel.player2Score.observe(this) {
            player2Score.setText((it ?: "").toString())
        }

        viewModel.onSuccessSubmit.observe(this) {
            if (it) {
                finish()
            }
        }
    }

    companion object {
        const val MATCH_DETAILS_EDIT = "match_details_edit"
    }
}