import java.io.File
import java.util.HashMap
import kotlin.math.roundToInt

data class Monkey(
    val items: MutableList<Long>,
    val operation: String,
    val divisibleBy: Long,
    val ifTrue: Int,
    val ifFalse: Int,
    var inspectionCount: Long = 0L
)

val monkeys = HashMap<Int, Monkey>()
File("input").readLines().joinToString("\n").split("\n\n").forEach { section ->
    section.split("\n").let { line ->
        val monkey = line[0].substringAfter(" ").substringBefore(":").toInt()
        val startingItems = line[1].substringAfter(": ").split(", ").map { it.toLong() }
        val operation = line[2].substringAfter("= old ")
        val divisibleBy = line[3].substringAfter("by ").toLong()
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
val upBound = monkeys.map { it.value.divisibleBy }.reduce(Long::times)
repeat(10000) {
    monkeys.forEach { (id, monkey) ->
        val itemsIterator = monkey.items.iterator()
        while (itemsIterator.hasNext()) {
            val item = itemsIterator.next()
            monkey.inspectionCount++
            itemsIterator.remove()
            val n = monkey.operation.substringAfter(" ")
            val worry = when (monkey.operation.first()) {
                '+' -> item + n.toInt()
                '-' -> item - n.toInt()
                '*' -> n.let {
                    if (it.startsWith("o")) item * item // new = old * old
                    else item * n.toInt()
                }

                '/' -> item / n.toInt()
                else -> 0
            }

            if (worry % monkey.divisibleBy == 0L) {
                // println("Monkey $id throws $worry to monkey ${data.ifTrue}")
                monkeys[monkey.ifTrue]!!.items.add(worry % upBound)
            } else {
                // println("Monkey $id throws $worry to monkey ${data.ifFalse}")
                monkeys[monkey.ifFalse]!!.items.add(worry % upBound)
            }
        }
    }
}
println(monkeys.map { it.value.inspectionCount })
println(monkeys.map { it.value.inspectionCount }.sortedBy { -it }.take(2).let { it[0] * it[1] })