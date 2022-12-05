import java.io.File

//                    [B]     [L]     [S]
//            [Q] [J] [C]     [W]     [F]
//        [F] [T] [B] [D]     [P]     [P]
//        [S] [J] [Z] [T]     [B] [C] [H]
//        [L] [H] [H] [Z] [G] [Z] [G] [R]
//    [R] [H] [D] [R] [F] [C] [V] [Q] [T]
//    [C] [J] [M] [G] [P] [H] [N] [J] [D]
//    [H] [B] [R] [S] [R] [T] [S] [R] [L]
//     1   2   3   4   5   6   7   8   9
val crates = listOf(
    ArrayDeque(listOf('H', 'C', 'R')),
    ArrayDeque(listOf('B', 'J', 'H', 'L', 'S', 'F')),
    ArrayDeque(listOf('R', 'M', 'D', 'H', 'J', 'T', 'Q')),
    ArrayDeque(listOf('S', 'G', 'R', 'H', 'Z', 'B', 'J')),
    ArrayDeque(listOf('R', 'P', 'F', 'Z', 'T', 'D', 'C', 'B')),
    ArrayDeque(listOf('T', 'H', 'C', 'G')),
    ArrayDeque(listOf('S', 'N', 'V', 'Z', 'B', 'P', 'W', 'L')),
    ArrayDeque(listOf('R', 'J', 'Q', 'G', 'C')),
    ArrayDeque(listOf('L', 'D', 'T', 'R', 'H', 'P', 'F', 'S'))
)

File("input").readLines().forEach { line ->
    val words = line.split(" ")
    val (move, from, to) = listOf(words[1], words[3], words[5]).map { it.toInt() }
    repeat(move) {
        crates[to - 1].addLast(crates[from - 1].removeLast())
    }
}
crates.map { it.last() }.joinToString("").let { println(it) }