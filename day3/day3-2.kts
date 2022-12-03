import java.io.File

fun findLetterInCommon(firstPart: String, secondPart: String, thirdPart: String): Char {
    firstPart.forEach { letter ->
        if (secondPart.contains(letter) && thirdPart.contains(letter)) {
            return letter
        }
    }
    return '_'
}

fun Char.getLetterValue(): Int = code - if (isUpperCase()) 38 else 96

val input = File("input").readLines().joinToString("\n")

var sum = 0
val lines = input.split("\n")
for (i in 0 until lines.size step 3) {
    val letterInCommon = findLetterInCommon(lines[i], lines[i + 1], lines[i + 2])
    val badgeValue = letterInCommon.getLetterValue()
    sum += badgeValue
}
println(sum)