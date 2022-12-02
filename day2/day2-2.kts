import java.io.File

val input = File("input").readLines().joinToString("\n")

// A = rock
// B = paper
// C = scissors

// X = rock
// Y = paper
// Z = scissors

var score = 0
input.split("\n").forEach { line ->
    val (opponentMove, drawLoseOrWin) = line.split(" ")
    var lose = false
    var draw = false
    var win = false
    when (drawLoseOrWin) {
        "X" -> lose = true
        "Y" -> draw = true
        "Z" -> win = true
    }
    val myMove = when {
        lose && opponentMove == "A" -> "Z" // opponent played rock, I play scissors
        lose && opponentMove == "B" -> "X" // opponent played paper, I play rock
        lose && opponentMove == "C" -> "Y" // opponent played scissors, I play paper
        draw && opponentMove == "A" -> "X" // opponent played rock, I play rock
        draw && opponentMove == "B" -> "Y" // opponent played paper, I play paper
        draw && opponentMove == "C" -> "Z" // opponent played scissors, I play scissors
        win && opponentMove == "A" -> "Y" // opponent played rock, I play paper
        win && opponentMove == "B" -> "Z" // opponent played paper, I play scissors
        win && opponentMove == "C" -> "X" // opponent played scissors, I play rock
        else -> ""
    }

    score += when(myMove) {
        "X" -> 1
        "Y" -> 2
        "Z" -> 3
        else -> 0
    }
    score += when {
        draw -> 3
        win -> 6
        lose -> 0
        else -> 0
    }
}
println(score)