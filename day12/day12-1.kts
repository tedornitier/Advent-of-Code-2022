import java.io.File

var start = Pair(-1, -1)
var end = Pair(-1, -1)
val matrix = File("input").readLines().mapIndexed { i, line ->
    line.mapIndexed { j, c ->
        when (c) {
            'S' -> {
                start = Pair(i, j)
                -1
            }
            'E' -> {
                end = Pair(i, j)
                'z'.code - 'a'.code + 1
            }
            else -> c.code - 'a'.code
        }
    }.toIntArray()
}.toTypedArray()

val visited = Array(matrix.size) { BooleanArray(matrix[0].size) }
val queue = mutableListOf(start)
val previous = mutableMapOf(start to start)

var minimumStepsNeedeed = 0
loop@while (queue.isNotEmpty()) {
    val current = queue.removeAt(0)

    if (current == end) {
        var path = mutableListOf<Pair<Int, Int>>()
        var curr = end
        while (curr != start) {
            path.add(0, curr)
            curr = previous[curr]!!
        }
        path.add(0, start)
        minimumStepsNeedeed = path.size - 1
        break@loop
    }

    visited[current.first][current.second] = true
    val row = current.first
    val col = current.second

    // up
    if (col > 0 && matrix[row][col - 1] <= matrix[row][col] + 1 && !visited[row][col - 1]) {
        queue.add(Pair(row, col - 1))
        previous[Pair(row, col - 1)] = current
    }
    // down
    if (col < matrix[row].size - 1 && matrix[row][col + 1] <= matrix[row][col] + 1 && !visited[row][col + 1]) {
        queue.add(Pair(row, col + 1))
        previous[Pair(row, col + 1)] = current
    }
    // left
    if (row > 0 && matrix[row - 1][col] <= matrix[row][col] + 1 && !visited[row - 1][col]) {
        queue.add(Pair(row - 1, col))
        previous[Pair(row - 1, col)] = current
    }
    // right
    if (row < matrix.size - 1 && matrix[row + 1][col] <= matrix[row][col] + 1 && !visited[row + 1][col]) {
        queue.add(Pair(row + 1, col))
        previous[Pair(row + 1, col)] = current
    }
}

println("Minimum steps needed: $minimumStepsNeedeed")