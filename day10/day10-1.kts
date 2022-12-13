import java.io.File

var x = mutableListOf(1)

File("input").readLines().forEach { line ->
    line.split(" ").let {
        when (it[0]) {
            "noop" -> x.add(x.last())
            "addx" -> {
                x.add(x.last())
                x.add(x.last() + it[1].toInt())
            }
        }
    }
}

var sum = 0
for (i in 20 until x.size step 40) {
    sum += i * x[i - 1]
}
println(sum)