import java.io.File

fun findLetterInCommon(firstPart: String, secondPart: String): Char {
    firstPart.forEach { letter ->
        if (secondPart.contains(letter)) {
            return letter
        }
    }
    return '_'
}

fun Char.getLetterValue(): Int = code - if (isUpperCase()) 38 else 96

val input = File("input").readLines().joinToString("\n")

var sum = 0
input.split("\n").forEach { line ->
    val firstPart = line.take(line.length / 2)
    val secondPart = line.drop(line.length / 2)
    val letterInCommon = findLetterInCommon(firstPart, secondPart)
    val letterValue = letterInCommon.getLetterValue()
    sum += letterValue
}
println(sum)