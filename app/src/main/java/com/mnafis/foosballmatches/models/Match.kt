package com.mnafis.foosballmatches.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Match(
    @PrimaryKey val id: Long,
    @Embedded val dateInfo: DateInfo,
    val player1Id: Int,
    val player1Score: Int,
    val player2Id: Int,
    val player2Score: Int,
    val winnerId: Int
) : Serializable

@Entity
data class DateInfo (
    val day: Int,
    val month: Int,
    val year: Int
) : Serializable