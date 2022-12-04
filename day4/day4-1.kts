import java.io.File

val input = File("input").readLines().joinToString("\n")

var sum = 0
input.split("\n").forEach { line ->
    val sectionRanges = line.split(",")
    val firstRange = sectionRanges[0].split("-")
    val secondRange = sectionRanges[1].split("-")
    val firstRangeStart = firstRange[0].toInt()
    val firstRangeEnd = firstRange[1].toInt()
    val secondRangeStart = secondRange[0].toInt()
    val secondRangeEnd = secondRange[1].toInt()

    val oneRangeFullyContainsTheOther =
        firstRangeStart <= secondRangeStart && firstRangeEnd >= secondRangeEnd ||
                secondRangeStart <= firstRangeStart && secondRangeEnd >= firstRangeEnd
    if (oneRangeFullyContainsTheOther) {
        sum++
    }
}
println(sum)