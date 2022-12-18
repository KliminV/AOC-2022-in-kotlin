fun main() {
    fun part1(input: String): Int {
        return firstOccurrenceNDistinctChars(input, 4)
    }

    fun part2(input: String): Int {
        return firstOccurrenceNDistinctChars(input, 14)
    }

// test if implementation meets criteria from the description, like:
    val testInput = read("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = read("Day06")
    println(part1(input))
    println(part2(input))
}

fun firstOccurrenceNDistinctChars(input: String, numOfElements: Int): Int {
    val result = input.windowed(numOfElements, 1, false)
        .indexOfFirst { sequence ->
            val set = mutableSetOf<Char>()
            for (i in 0 until numOfElements)
                set.add(sequence[i])
            set.size == numOfElements
        }
    return if (result == -1) result else result + numOfElements
}

