import java.io.File

var path = "/"
var sumMap = mutableMapOf<String, Int>()
File("input").readLines().forEach {
    if (it.first() == '$') {
        // command. example: $ cd mydir
        val splitCommand = it.split(" ")
        if (splitCommand[1] == "cd") {
            val dir = splitCommand[2]
            when (dir) {
                ".." -> path = path.substringBeforeLast("/")
                "/" -> path = "/"
                else -> path += "$dir/"
            }
        }
    } else {
        // file or directory
        if (it.first() != 'd') {
            // file size and name. example: 123 myfile
            val filesize = it.split(" ")[0].toInt()
            var tmpPath = "/"
            if (path != "/") {
                path.drop(1).dropLast(1).split("/").forEach { dir ->
                    sumMap[tmpPath] = (sumMap[tmpPath] ?: 0) + filesize
                    tmpPath += "$dir/"
                }
            }
            sumMap[path] = (sumMap[path] ?: 0) + filesize
        }
    }
}
val filteredDirectories = sumMap.filter { it.value < 100000 }.toSortedMap()
filteredDirectories.map { it.value }.sum().let { println(it) }