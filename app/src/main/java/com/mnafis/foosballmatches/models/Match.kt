package com.mnafis.foosballmatches.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Match(
    @PrimaryKey val timestamp: Long,
    val player1Id: Int,
    val player1Score: Int,
    val player2Id: Int,
    val player2Score: Int,
    val winnerId: Int
)