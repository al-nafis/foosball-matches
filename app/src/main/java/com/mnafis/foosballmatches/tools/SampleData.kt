package com.mnafis.foosballmatches.tools

import com.mnafis.foosballmatches.models.DateInfo
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player

val samplePlayers = listOf(
    Player(employeeId = 1, name = "Amos", totalMatchesPlayed = 12, wins = 6, losses = 6),
    Player(employeeId = 2, name = "Diego", totalMatchesPlayed = 8, wins = 5, losses = 3),
    Player(employeeId = 3, name = "Joel", totalMatchesPlayed = 3, wins = 1, losses = 2),
    Player(employeeId = 4, name = "Tim", totalMatchesPlayed = 5, wins = 2, losses = 3)
)

val sampleMatches = listOf(
    Match(
        id = 1,
        dateInfo = DateInfo(1,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 2,
        dateInfo = DateInfo(2,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 1,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 3,
        dateInfo = DateInfo(3,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 2,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 4,
        dateInfo = DateInfo(4,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 0,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 5,
        dateInfo = DateInfo(5,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 6,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 6,
        dateInfo = DateInfo(6,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 2,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 7,
        dateInfo = DateInfo(7,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 0,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 8,
        dateInfo = DateInfo(8,0, 2024),
        player1Id = samplePlayers[2].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 9,
        dateInfo = DateInfo(9,0, 2024),
        player1Id = samplePlayers[3].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[0].employeeId,
        player2Score = 5,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 10,
        dateInfo = DateInfo(10,0, 2024),
        player1Id = samplePlayers[3].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[0].employeeId,
        player2Score = 2,
        winner = samplePlayers[3].name
    ),
    Match(
        id = 11,
        dateInfo = DateInfo(11,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 3,
        player2Id = samplePlayers[3].employeeId,
        player2Score = 5,
        winner = samplePlayers[3].name
    ),
    Match(
        id = 12,
        dateInfo = DateInfo(12,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[3].employeeId,
        player2Score = 3,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 13,
        dateInfo = DateInfo(13,0, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[2].employeeId,
        player2Score = 4,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 14,
        dateInfo = DateInfo(14,0, 2024),
        player1Id = samplePlayers[2].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[3].employeeId,
        player2Score = 2,
        winner = samplePlayers[2].name
    )
)