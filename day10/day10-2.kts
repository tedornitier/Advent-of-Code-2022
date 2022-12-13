import java.io.File

val imageWidth = 40
val imageHeight = 6

var x = mutableListOf(1)

fun drawPixel(image: Array<Char>, cycle: Int, x: Int) {
    if (cycle % imageWidth in x - 1..x + 1) {
        image[cycle] = '\u2588' // █, but technically it would be #
    }
}

val cycle: Int get() = x.size - 1
val image = Array(imageWidth * imageHeight) { '.' }
File("input").readLines().forEach { line ->
    line.split(" ").let {
        when (it[0]) {
            "noop" -> {
                drawPixel(image, cycle, x.last())
                x.add(x.last())
            }

            "addx" -> {
                drawPixel(image, cycle, x.last())
                x.add(x.last())
                drawPixel(image, cycle, x.last())
                x.add(x.last() + it[1].toInt())
            }
        }
    }
}

image.forEachIndexed { index, pixel ->
    print("$pixel$pixel$pixel")
    if (index % imageWidth == imageWidth - 1) {
        println()
    }
}