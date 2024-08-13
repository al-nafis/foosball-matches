package com.mnafis.foosballmatches.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Player(
    @PrimaryKey val employeeId: Int,
    val name: String,
    val totalMatchesPlayed: Int = 0,
    val wins: Int = 0,
    val loses: Int = 0
) : Serializable