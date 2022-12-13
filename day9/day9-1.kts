import java.io.File
import kotlin.math.max
import kotlin.math.min

var xRange = 0..0
var yRange = 0..0
var headX = 0
var headY = 0
var tailX = 0
var tailY = 0

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

val placesWhereTailHasBeenTo = mutableSetOf(Pair(0,0))

fun printMatrix(xRange: IntRange, yRange: IntRange, headX: Int, headY: Int, tailX: Int, tailY: Int) {
    for (y in yRange) {
        for (x in xRange) {
            if (x == headX && y == headY) {
                print("H")
            } else if (x == tailX && y == tailY) {
                print("T")
            } else {
                print(".")
            }
        }
        println()
    }
    println()
}

File("input").readLines().map { line ->
    val (direction, steps) = line.split(" ")
    println("=== $direction $steps ===")
    repeat(steps.toInt()) {
        when (direction) {
            "R" -> headX++
            "L" -> headX--
            "U" -> headY++
            "D" -> headY--
        }
        tailFollowsHead(headX, headY, tailX, tailY).let {
            tailX = it.first
            tailY = it.second
            placesWhereTailHasBeenTo.add(it)
        }
        // only needed for debugging
        // xRange = min(xRange.first, headX)..max(xRange.last, headX)
        // yRange = min(yRange.first, headY)..max(yRange.last, headY)
        // printMatrix(xRange, yRange, headX, headY, tailX, tailY)
    }
}

println(placesWhereTailHasBeenTo.size)