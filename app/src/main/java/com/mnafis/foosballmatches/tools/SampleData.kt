package com.mnafis.foosballmatches.tools

import com.mnafis.foosballmatches.models.DateInfo
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player

val samplePlayers = listOf(
    Player(employeeId = 1, name = "Amos"),
    Player(employeeId = 2, name = "Diego"),
    Player(employeeId = 3, name = "Joel"),
    Player(employeeId = 4, name = "Tim")
)

val sampleMatches = listOf(
    Match(
        id = 1,
        dateInfo = DateInfo(1, 1, 2024, 13, 15),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 2,
        dateInfo = DateInfo(2, 1, 2024, 10, 10),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 1,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 3,
        dateInfo = DateInfo(3, 1, 2024, 10, 0),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 2,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 4,
        dateInfo = DateInfo(4, 1, 2024, 12, 15),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 0,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 5,
        dateInfo = DateInfo(5, 1, 2024, 9, 15),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 6,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 5,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 6,
        dateInfo = DateInfo(6, 1, 2024, 15, 45),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 5,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 2,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 7,
        dateInfo = DateInfo(7, 1, 2024, 13, 15),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 0,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 8,
        dateInfo = DateInfo(8, 1, 2024, 11, 15),
        player1Id = samplePlayers[2].employeeId,
        player1Name = samplePlayers[2].name,
        player1Score = 4,
        player2Id = samplePlayers[1].employeeId,
        player2Name = samplePlayers[1].name,
        player2Score = 5,
        winner = samplePlayers[1].name
    ),
    Match(
        id = 9,
        dateInfo = DateInfo(9, 1, 2024, 9, 15),
        player1Id = samplePlayers[3].employeeId,
        player1Name = samplePlayers[3].name,
        player1Score = 4,
        player2Id = samplePlayers[0].employeeId,
        player2Name = samplePlayers[0].name,
        player2Score = 5,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 10,
        dateInfo = DateInfo(10, 1, 2024, 9, 15),
        player1Id = samplePlayers[3].employeeId,
        player1Name = samplePlayers[3].name,
        player1Score = 5,
        player2Id = samplePlayers[0].employeeId,
        player2Name = samplePlayers[0].name,
        player2Score = 2,
        winner = samplePlayers[3].name
    ),
    Match(
        id = 11,
        dateInfo = DateInfo(11, 1, 2024, 9, 15),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 3,
        player2Id = samplePlayers[3].employeeId,
        player2Name = samplePlayers[3].name,
        player2Score = 5,
        winner = samplePlayers[3].name
    ),
    Match(
        id = 12,
        dateInfo = DateInfo(12, 1, 2024, 9, 15),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 5,
        player2Id = samplePlayers[3].employeeId,
        player2Name = samplePlayers[3].name,
        player2Score = 3,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 13,
        dateInfo = DateInfo(13, 1, 2024, 9, 15),
        player1Id = samplePlayers[0].employeeId,
        player1Name = samplePlayers[0].name,
        player1Score = 5,
        player2Id = samplePlayers[2].employeeId,
        player2Name = samplePlayers[2].name,
        player2Score = 4,
        winner = samplePlayers[0].name
    ),
    Match(
        id = 14,
        dateInfo = DateInfo(14, 1, 2024, 9, 15),
        player1Id = samplePlayers[2].employeeId,
        player1Name = samplePlayers[2].name,
        player1Score = 5,
        player2Id = samplePlayers[3].employeeId,
        player2Name = samplePlayers[3].name,
        player2Score = 2,
        winner = samplePlayers[2].name
    )
)