import java.io.File

val input = File("input").readLines().joinToString("\n")

var sum = 0
input.split("\n").forEach { line ->
    val sectionRanges = line.split(",")
    val firstRange = sectionRanges[0].split("-")
    val secondRange = sectionRanges[1].split("-")
    val firstRangeStart = firstRange[0].toInt()
    val secondRangeStart = secondRange[0].toInt()

    val smallerRange = if (firstRangeStart < secondRangeStart) firstRange else secondRange
    val largerRange = if (firstRangeStart < secondRangeStart) secondRange else firstRange
    val smallerRangeEnd = smallerRange[1].toInt()
    val largerRangeStart = largerRange[0].toInt()

    val rangesOverlap = smallerRangeEnd >= largerRangeStart
    if (rangesOverlap) {
        sum++
    }
}
println(sum)