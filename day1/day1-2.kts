import java.io.File

val input = File("input").readLines().joinToString("\n")

val sums = mutableListOf<Int>()
input.split("\n\n").forEach {
    val sum = it.trim()
        .split("\n")
        .map { it.toInt() }
        .sum()
    sums.add(sum)
}
println(sums.sortedDescending().take(3).sum())