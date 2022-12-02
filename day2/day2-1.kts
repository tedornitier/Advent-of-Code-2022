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
    val (opponentMove, myMove) = line.split(" ")
    val draw = when (opponentMove) {
        "A" -> myMove == "X"
        "B" -> myMove == "Y"
        "C" -> myMove == "Z"
        else -> false
    }
    val lose = when (opponentMove) {
        "A" -> myMove == "Z" // opponent rock, I scissor
        "B" -> myMove == "X" // opponent paper, I rock
        "C" -> myMove == "Y" // opponent scissor, I paper
        else -> false
    }
    val win = !draw && !lose

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