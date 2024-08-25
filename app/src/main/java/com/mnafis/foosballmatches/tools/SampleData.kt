package com.mnafis.foosballmatches.tools

import com.mnafis.foosballmatches.models.DateInfo
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player

val samplePlayers = listOf(
    Player(employeeId = 981664, name = "Joel", totalMatchesPlayed = 3, wins = 1, losses = 2),
    Player(employeeId = 141411, name = "Diego", totalMatchesPlayed = 8, wins = 5, losses = 3),
    Player(employeeId = 629782, name = "Tim", totalMatchesPlayed = 5, wins = 2, losses = 3),
    Player(employeeId = 458925, name = "Amos", totalMatchesPlayed = 12, wins = 6, losses = 6)
)

val sampleMatches = listOf(
    Match(
        id = 6682,
        dateInfo = DateInfo(1,2, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[1].employeeId
    ),
    Match(
        id = 12648,
        dateInfo = DateInfo(2,7, 2023),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 1,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[1].employeeId
    ),
    Match(
        id = 5795,
        dateInfo = DateInfo(4,3, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 0,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[1].employeeId
    ),
    Match(
        id = 10236,
        dateInfo = DateInfo(6,5,2023),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 2,
        winnerId = samplePlayers[0].employeeId
    ),
    Match(
        id = 4321,
        dateInfo = DateInfo(5,2, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 6,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[0].employeeId
    ),
    Match(
        id = 9521,
        dateInfo = DateInfo(3,2, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 2,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[1].employeeId
    ),
    Match(
        id = 14390,
        dateInfo = DateInfo(7,4, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 0,
        winnerId = samplePlayers[0].employeeId
    ),
    Match(
        id = 11670,
        dateInfo = DateInfo(8,1, 2024),
        player1Id = samplePlayers[2].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[1].employeeId
    ),
    Match(
        id = 3542,
        dateInfo = DateInfo(9,6, 2023),
        player1Id = samplePlayers[3].employeeId,
        player1Score = 4,
        player2Id = samplePlayers[0].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[0].employeeId
    ),
    Match(
        id = 13215,
        dateInfo = DateInfo(10,7, 2024),
        player1Id = samplePlayers[3].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[0].employeeId,
        player2Score = 2,
        winnerId = samplePlayers[3].employeeId
    ),
    Match(
        id = 2645,
        dateInfo = DateInfo(11,7, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 3,
        player2Id = samplePlayers[3].employeeId,
        player2Score = 5,
        winnerId = samplePlayers[3].employeeId
    ),
    Match(
        id = 8009,
        dateInfo = DateInfo(12,7, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[3].employeeId,
        player2Score = 3,
        winnerId = samplePlayers[0].employeeId
    ),
    Match(
        id = 1435,
        dateInfo = DateInfo(14,7, 2024),
        player1Id = samplePlayers[0].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[2].employeeId,
        player2Score = 4,
        winnerId = samplePlayers[0].employeeId
    ),
    Match(
        id = 7456,
        dateInfo = DateInfo(14,7, 2024),
        player1Id = samplePlayers[2].employeeId,
        player1Score = 5,
        player2Id = samplePlayers[3].employeeId,
        player2Score = 2,
        winnerId = samplePlayers[2].employeeId
    )
)