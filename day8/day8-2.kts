import java.io.File

fun Pair<Int, Int>.calculateTreeScenicScore(forest: List<List<Int>>): Int {
    println("$this left: ${this.getVisibleTreesCountOnTheLeft(forest)}, right: ${this.getVisibleTreesCountOnTheRight(forest)}, top: ${this.getVisibleTreesCountOnTheTop(forest)}, bottom: ${this.getVisibleTreesCountOnTheBottom(forest)}")
    return getVisibleTreesCountOnTheLeft(forest) *
            getVisibleTreesCountOnTheRight(forest) *
            getVisibleTreesCountOnTheTop(forest) *
            getVisibleTreesCountOnTheBottom(forest)
}

fun Pair<Int, Int>.getVisibleTreesCountOnTheLeft(forest: List<List<Int>>): Int {
    var visibleTreesCount = 0
    for (col in this.second - 1 downTo 0) {
        val treeOnTheLeftHeight = forest[this.first][col]
        val thisTreeHeight = forest[this.first][this.second]
        visibleTreesCount++
        if (treeOnTheLeftHeight >= thisTreeHeight) {
            break
        }
    }
    return visibleTreesCount
}

fun Pair<Int, Int>.getVisibleTreesCountOnTheRight(forest: List<List<Int>>): Int {
    var visibleTreesCount = 0
    for (col in this.second + 1 until forest[this.first].size) {
        val treeOnTheRightHeight = forest[this.first][col]
        val thisTreeHeight = forest[this.first][this.second]
        visibleTreesCount++
        if (treeOnTheRightHeight >= thisTreeHeight) {
            break
        }
    }
    return visibleTreesCount
}

fun Pair<Int, Int>.getVisibleTreesCountOnTheTop(forest: List<List<Int>>): Int {
    var visibleTreesCount = 0
    for (row in this.first - 1 downTo 0) {
        val treeOnTheTopHeight = forest[row][this.second]
        val thisTreeHeight = forest[this.first][this.second]
        visibleTreesCount++
        if (treeOnTheTopHeight >= thisTreeHeight) {
            break
        }
    }
    return visibleTreesCount
}

fun Pair<Int, Int>.getVisibleTreesCountOnTheBottom(forest: List<List<Int>>): Int {
    var visibleTreesCount = 0
    for (row in this.first + 1 until forest.size) {
        val treeOnTheBottomHeight = forest[row][this.second]
        val thisTreeHeight = forest[this.first][this.second]
        visibleTreesCount++
        if (treeOnTheBottomHeight >= thisTreeHeight) {
            break
        }
    }
    return visibleTreesCount
}

val forest = File("input").readLines().map { line ->
    line.map { char ->
        char.toString().toInt()
    }
}

var maxScenicScore = 0
forest.forEachIndexed { row, treeRow ->
    treeRow.forEachIndexed { col, treeHeight ->
        val tree = Pair(row, col)
        val scenicScore = tree.calculateTreeScenicScore(forest)
        if (scenicScore > maxScenicScore) {
            maxScenicScore = scenicScore
        }
    }
}
println("Max scenic score: $maxScenicScore")