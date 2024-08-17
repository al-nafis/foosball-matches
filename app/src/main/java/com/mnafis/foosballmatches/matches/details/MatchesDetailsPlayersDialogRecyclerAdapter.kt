package com.mnafis.foosballmatches.matches.details

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.models.Player
import javax.inject.Inject

class MatchesDetailsPlayersDialogRecyclerAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<MatchesDetailsPlayersDialogRecyclerAdapter.PlayerViewHolder>() {

    private var players = listOf<Player>()
    private var onClickItem: (Player) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder =
        PlayerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_text_view, parent, false)
        )

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.text.text = context.getString(R.string.match_details_players_view_item_text, players[position].employeeId, players[position].name)
        holder.card.setOnClickListener { onClickItem(players[position]) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (Player) -> Unit) {
        onClickItem = onClick
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.recycler_text_view_item)
        val card: CardView = itemView.findViewById(R.id.recycler_text_view_card)
    }
}