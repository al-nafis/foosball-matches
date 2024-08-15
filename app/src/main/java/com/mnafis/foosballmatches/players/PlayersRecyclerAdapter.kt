package com.mnafis.foosballmatches.players

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.models.Player
import javax.inject.Inject

class PlayersRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<PlayersRecyclerAdapter.PlayersViewHolder>() {

    private var players = listOf<Player>()
    private var onClickItem: (Player) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder =
        PlayersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_players_card_view, parent, false)
        )

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = players[position]
        holder.employeeId.text = player.employeeId.toString()
        holder.name.text = player.name
        holder.editButton.setOnClickListener { onClickItem(player) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (Player) -> Unit) {
        onClickItem = onClick
    }

    class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeId: TextView = itemView.findViewById(R.id.fragment_players_card_view_card_view_employee_id)
        val name: TextView = itemView.findViewById(R.id.fragment_players_card_view_card_view_player1_name)
        val editButton: ImageView = itemView.findViewById(R.id.fragment_players_card_view_card_view_edit_button)
    }
}