package com.mnafis.foosballmatches.matches

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player
import com.mnafis.foosballmatches.tools.getFormattedDate
import javax.inject.Inject

class MatchesRecyclerAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<MatchesRecyclerAdapter.MatchViewHolder>() {

    private var matches = listOf<Match>()
    private var players = listOf<Player>()
    private var onClickItem: (Match) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_matches_card_view, parent, false)
        )

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.date.text = getFormattedDate(match.dateInfo)
        holder.player1Name.text = players.find { it.employeeId == matches[position].player1Id }?.name
        holder.player1Id.text = players.find { it.employeeId == matches[position].player1Id }?.employeeId.toString()
        holder.score.text = context.getString(R.string.match_card_view_score, match.player1Score, match.player2Score)
        holder.player2Name.text = players.find { it.employeeId == matches[position].player2Id }?.name
        holder.player2Id.text = players.find { it.employeeId == matches[position].player2Id }?.employeeId.toString()
        holder.editButton.setOnClickListener { onClickItem(match) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(matches: List<Match>, players: List<Player>) {
        this.matches = matches
        this.players = players
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (Match) -> Unit) {
        onClickItem = onClick
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.match_card_view_date)
        val player1Name: TextView = itemView.findViewById(R.id.match_card_view_player1_name)
        val player1Id: TextView = itemView.findViewById(R.id.match_card_view_player1_id)
        val score: TextView = itemView.findViewById(R.id.match_card_view_score)
        val player2Name: TextView = itemView.findViewById(R.id.match_card_view_player2_name)
        val player2Id: TextView = itemView.findViewById(R.id.match_card_view_player2_id)
        val editButton: ImageView = itemView.findViewById(R.id.match_card_view_edit_button)
    }
}