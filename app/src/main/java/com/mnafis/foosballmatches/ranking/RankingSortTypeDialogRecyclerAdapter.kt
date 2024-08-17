package com.mnafis.foosballmatches.ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mnafis.foosballmatches.R
import com.mnafis.foosballmatches.ranking.RankingViewModel.SortType
import javax.inject.Inject

class RankingSortTypeDialogRecyclerAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<RankingSortTypeDialogRecyclerAdapter.RankingSortViewHolder>() {

    private val sortTypes = SortType.entries.toList()
    private var onClickItem: (SortType) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingSortViewHolder =
        RankingSortViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_text_view, parent, false)
        )

    override fun getItemCount(): Int = sortTypes.size

    override fun onBindViewHolder(holder: RankingSortViewHolder, position: Int) {
        holder.text.text = context.getString(
            when (sortTypes[position]) {
                SortType.MOST_WINS -> R.string.ranking_sort_type_most_wins
                SortType.MOST_LOSSES -> R.string.ranking_sort_type_most_losses
                SortType.MOST_PLAYED -> R.string.ranking_sort_type_total_played
            }
        )
        holder.card.setOnClickListener { onClickItem(sortTypes[position]) }
    }

    fun setOnClickListener(onClick: (SortType) -> Unit) {
        onClickItem = onClick
    }

    class RankingSortViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.recycler_text_view_item)
        val card: CardView = itemView.findViewById(R.id.recycler_text_view_card)
    }
}