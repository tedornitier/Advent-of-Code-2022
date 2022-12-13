import java.io.File

fun getVisibleTrees(forest: List<List<Int>>): List<Pair<Int, Int>> {
    return (getVisibleTreesFromLeft(forest) +
            getVisibleTreesFromRight(forest) +
            getVisibleTreesFromTop(forest) +
            getVisibleTreesFromBottom(forest)
            ).distinct()
}

fun getVisibleTreesFromLeft(forest: List<List<Int>>): List<Pair<Int, Int>> {
    var visibleTrees = mutableListOf<Pair<Int, Int>>()
    for (row in forest.indices) {
        var maxTreeHeightFromLeft = -1 // -1 so even 0 is counted
        for (col in forest[row].indices) {
            if (forest[row][col] > maxTreeHeightFromLeft) {
                println("Tree at ($row, $col) is visible from the left")
                maxTreeHeightFromLeft = forest[row][col]
                visibleTrees.add(Pair(row, col))
            }
        }
    }
    return visibleTrees
}

fun getVisibleTreesFromRight(forest: List<List<Int>>): List<Pair<Int, Int>> {
    var visibleTrees = mutableListOf<Pair<Int, Int>>()
    for (row in forest.indices) {
        var maxTreeHeightFromRight = -1 // -1 so even 0 is counted
        for (col in forest[row].indices.reversed()) {
            if (forest[row][col] > maxTreeHeightFromRight) {
                println("Tree at ($row, $col) is visible from the right")
                maxTreeHeightFromRight = forest[row][col]
                visibleTrees.add(Pair(row, col))
            }
        }
    }
    return visibleTrees
}

fun getVisibleTreesFromTop(forest: List<List<Int>>): List<Pair<Int, Int>> {
    var visibleTrees = mutableListOf<Pair<Int, Int>>()
    for (col in forest[0].indices) {
        var maxTreeHeightFromTop = -1 // -1 so even 0 is counted
        for (row in forest.indices) {
            if (forest[row][col] > maxTreeHeightFromTop) {
                println("Tree at ($row, $col) is visible from the top")
                maxTreeHeightFromTop = forest[row][col]
                visibleTrees.add(Pair(row, col))
            }
        }
    }
    return visibleTrees
}

fun getVisibleTreesFromBottom(forest: List<List<Int>>): List<Pair<Int, Int>> {
    var visibleTrees = mutableListOf<Pair<Int, Int>>()
    for (col in forest[0].indices) {
        var maxTreeHeightFromBottom = -1 // -1 so even 0 is counted
        for (row in forest.indices.reversed()) {
            if (forest[row][col] > maxTreeHeightFromBottom) {
                println("Tree at ($row, $col) is visible from the bottom")
                maxTreeHeightFromBottom = forest[row][col]
                visibleTrees.add(Pair(row, col))
            }
        }
    }
    return visibleTrees
}

val forest = File("input").readLines().map { line ->
    line.map { char ->
        char.toString().toInt()
    }
}
getVisibleTrees(forest).count().let { println(it) }