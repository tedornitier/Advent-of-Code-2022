import java.io.File

var chain = MutableList<Pair<Int, Int>>(10) { Pair(0, 0) }
val placesWhereTailHasBeenTo = mutableSetOf(Pair(0,0))
var xRange = 0..0
var yRange = 0..0

fun tailFollowsHead(headX: Int, headY: Int, tailX: Int, tailY: Int): Pair<Int, Int> {
    if (Math.abs(headX - tailX) <= 1 && Math.abs(headY - tailY) <= 1) {
        return Pair(tailX, tailY)
    }
    var newTailX = tailX
    var newTailY = tailY
    if (headX > tailX) {
        newTailX++
    } else if (headX < tailX) {
        newTailX--
    }
    if (headY > tailY) {
        newTailY++
    } else if (headY < tailY) {
        newTailY--
    }
    return Pair(newTailX, newTailY)
}

fun printMatrix(xRange: IntRange, yRange: IntRange, chain: List<Pair<Int, Int>>) {
    val headX = chain[0].first
    val headY = chain[0].second
    val tailX = chain[chain.size - 1].first
    val tailY = chain[chain.size - 1].second
    for (y in yRange) {
        for (x in xRange) {
            if (x == headX && y == headY) {
                print('☺')
            } else if (x == tailX && y == tailY) {
                print("🔚")
            } else if (chain.contains(Pair(x, y))) {
                print("🪩")
            } else {
                print("  ")
            }
        }
        println()
    }
    println()
}

File("input").readLines().map { line ->
    val (direction, steps) = line.split(" ")
    //println("=== $direction $steps ===")
    repeat(steps.toInt()) {
        var x = 0
        var y = 0
        when (direction) {
            "R" -> x++
            "L" -> x--
            "U" -> y++
            "D" -> y--
        }
        // move head
        chain[0] = Pair(chain[0].first + x, chain[0].second + y)
        // move chain
        for (i in 1 until chain.size) {
            val (leadingX, leadingY) = chain[i - 1]
            val (followingX, followingY) = chain[i]
            tailFollowsHead(leadingX, leadingY, followingX, followingY).let {
                chain[i] = it
                if (i == chain.size - 1) {
                    // save only the actual tail movements
                    placesWhereTailHasBeenTo.add(it)
                }
            }
        }
        // only needed for debugging
        // xRange = minOf(xRange.first, chain[0].first)..maxOf(xRange.last, chain[0].first)
        // yRange = minOf(yRange.first, chain[0].second)..maxOf(yRange.last, chain[0].second)
        // printMatrix(xRange, yRange, chain)
    }
}

println(placesWhereTailHasBeenTo.size)