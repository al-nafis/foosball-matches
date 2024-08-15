package com.mnafis.foosballmatches.ranking

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.models.Player
import javax.inject.Inject

class RankingRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<RankingRecyclerAdapter.RankingItemViewHolder>() {

    private var players = listOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingItemViewHolder =
        RankingItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_ranking_recycler_item_view, parent, false)
        )

    override fun getItemCount(): Int = players.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RankingItemViewHolder, position: Int) {
        holder.rank.text = (position + 1).toString()
        holder.name.text = players[position].name
        holder.totalPlayed.text = players[position].totalMatchesPlayed.toString()
        holder.wins.text = players[position].wins.toString()
        holder.losses.text = players[position].losses.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }

    class RankingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rank: TextView = itemView.findViewById(R.id.ranking_recycler_item_ranking)
        val name: TextView = itemView.findViewById(R.id.ranking_recycler_item_name)
        val totalPlayed: TextView = itemView.findViewById(R.id.ranking_recycler_item_total_played)
        val wins: TextView = itemView.findViewById(R.id.ranking_recycler_item_wins)
        val losses: TextView = itemView.findViewById(R.id.ranking_recycler_item_losses)
    }
}