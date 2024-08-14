package com.mnafis.foosballmatches.database.matches

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
import com.mnafis.foosballmatches.tools.getDate
import com.mnafis.foosballmatches.tools.getTime
import javax.inject.Inject

class MatchesRecyclerAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<MatchesRecyclerAdapter.MatchViewHolder>() {

    private var matches = listOf<Match>()
    private var onClickItem: (Match) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.match_card_view, parent, false)
        )

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.date.text = match.dateInfo.getDate()
        holder.time.text = match.dateInfo.getTime()
        holder.player1Name.text = match.player1Name
        holder.score.text = context.getString(R.string.match_card_view_score, match.player1Score, match.player2Score)
        holder.player2Name.text = match.player2Name
        holder.editButton.setOnClickListener { onClickItem(match) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Match>) {
        matches = data
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (Match) -> Unit) {
        onClickItem = onClick
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.match_card_view_date)
        val time: TextView = itemView.findViewById(R.id.match_card_view_time)
        val player1Name: TextView = itemView.findViewById(R.id.match_card_view_player1_name)
        val score: TextView = itemView.findViewById(R.id.match_card_view_score)
        val player2Name: TextView = itemView.findViewById(R.id.match_card_view_player2_name)
        val editButton: ImageView = itemView.findViewById(R.id.match_card_view_edit_button)
    }
}