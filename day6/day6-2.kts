import java.io.File

fun isMarker(buffer: CharArray): Boolean = buffer.distinct().size == buffer.size

val input = File("input").readLines()[0]
val messageSize = 14
var buffer = CharArray(messageSize) { ' ' }
run loop@{
    input.forEachIndexed { i, char ->
        for (j in 0 until messageSize - 1) {
            buffer[j] = buffer[j + 1]
        }
        buffer[messageSize - 1] = char
        if (isMarker(buffer) && i > messageSize - 1) {
            println(i + 1)
            return@loop
        }
    }
}