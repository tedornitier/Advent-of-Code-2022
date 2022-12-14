import java.io.File
import java.util.HashMap
import kotlin.math.roundToInt

data class Monkey(
    val items: MutableList<Int>,
    val operation: String,
    val divisibleBy: Int,
    val ifTrue: Int,
    val ifFalse: Int
)

val monkeys = HashMap<Int, Monkey>()
File("input").readLines().joinToString("\n").split("\n\n").forEach { section ->
    section.split("\n").let { line ->
        val monkey = line[0].substringAfter(" ").substringBefore(":").toInt()
        val startingItems = line[1].substringAfter(": ").split(", ").map { it.toInt() }
        val operation = line[2].substringAfter("= old ")
        val divisibleBy = line[3].substringAfter("by ").toInt()
        val ifTrue = line[4].substringAfter("monkey ").toInt()
        val ifFalse = line[5].substringAfter("monkey ").toInt()

        println("monkey = $monkey, startingItemsLine = $startingItems, operationLine = $operation, testLine = $divisibleBy, ifTrueLine = $ifTrue, ifFalseLine = $ifFalse")
        monkeys.put(
            monkey,
            Monkey(startingItems.toMutableList(), operation, divisibleBy, ifTrue, ifFalse)
        )
    }
}
println(monkeys)

val inspections = HashMap<Int, Int>()
repeat(20) {
    monkeys.forEach { (id, data) ->
        val itemsIterator = data.items.iterator()
        while (itemsIterator.hasNext()) {
            val item = itemsIterator.next()
            inspections[id] = inspections.getOrDefault(id, 0) + 1
            itemsIterator.remove()
            val n = data.operation.substringAfter(" ")
            val worry = when (data.operation.first()) {
                '+' -> item + n.toInt()
                '-' -> item - n.toInt()
                '*' -> n.let {
                    if (it.startsWith("o")) item * item // new = old * old
                    else item * n.toInt()
                }

                '/' -> item / n.toInt()
                else -> 0
            } / 3

            if (worry % data.divisibleBy == 0) {
                println("Monkey $id throws $worry to monkey ${data.ifTrue}")
                monkeys[data.ifTrue]!!.items.add(worry)
            } else {
                println("Monkey $id throws $worry to monkey ${data.ifFalse}")
                monkeys[data.ifFalse]!!.items.add(worry)
            }
        }
    }
}
println(inspections)
println(inspections.map { it.value }.sortedBy { -it }.take(2).let { it[0] * it[1] })