package com.mnafis.foosballmatches.players.details

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.BaseActivity
import com.mnafis.foosballmatches.FoosballApplication
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.ToolbarNavigationIcon
import com.mnafis.foosballmatches.ToolbarTrailerIcon
import com.mnafis.foosballmatches.ViewModelFactory
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.models.Player
import javax.inject.Inject

class PlayerDetailsActivity : BaseActivity() {

    @Inject
    lateinit var playersRepository: PlayersRepository

    lateinit var viewModel: PlayerDetailsViewModel

    private lateinit var employeeIdView: EditText
    private lateinit var nameView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as FoosballApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory {
                PlayerDetailsViewModel(playersRepository)
            }
        )[PlayerDetailsViewModel::class]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_player_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.player_details)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setToolbarNavigationIcon(ToolbarNavigationIcon.BACK) {
            finish()
        }
        setToolbarTitle(R.string.player_details)

        employeeIdView = findViewById(R.id.activity_player_details_edit_id)
        nameView = findViewById(R.id.activity_player_details_edit_name)

        val player = intent.getSerializableExtra(PLAYER_DETAILS_EDIT) as? Player
        player?.let {
            viewModel.isEdit = true
            viewModel.editablePlayer = it
            employeeIdView.isEnabled = false
            viewModel.setEmployeeId(it.employeeId)
            viewModel.setName(it.name)
            setToolbarTrailerIcon(ToolbarTrailerIcon.DELETE) {
                viewModel.deletePlayer()
            }
        }

        setupListeners()
        setupObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeDisposables()
    }

    private fun setupListeners() {
        val submitButton: Button = findViewById(R.id.player_details_button_submit)
        submitButton.setOnClickListener {
            viewModel.setEmployeeId(employeeIdView.text.toString().toIntOrNull())
            viewModel.setName(nameView.text.toString())
            viewModel.submit()
        }
    }

    private fun setupObservers() {
        val errorView: TextView = findViewById(R.id.player_details_error_text)
        viewModel.errorMessage.observe(this) { message ->
            val msg = when (message) {
                PlayerDetailsViewModel.ErrorType.ID -> R.string.activity_player_details_error_message_id
                PlayerDetailsViewModel.ErrorType.NAME -> R.string.activity_player_details_error_message_name
                PlayerDetailsViewModel.ErrorType.GENERIC -> R.string.error_message_generic
                PlayerDetailsViewModel.ErrorType.DUPLICATE_ID -> R.string.activity_player_details_error_message_duplicate_id
                PlayerDetailsViewModel.ErrorType.NONE -> null
            }
            msg?.let {
                errorView.text = getString(it)
                errorView.visibility = View.VISIBLE
            } ?: run {
                errorView.text = ""
                errorView.visibility = View.GONE
            }
        }

        viewModel.employeeId.observe(this) {
            employeeIdView.setText((it ?: "").toString())
        }

        viewModel.name.observe(this) {
            nameView.setText((it ?: "").toString())
        }

        viewModel.onSuccessSubmit.observe(this) {
            if (it) {
                finish()
            }
        }
    }

    companion object {
        const val PLAYER_DETAILS_EDIT = "player_details_edit"
    }
}