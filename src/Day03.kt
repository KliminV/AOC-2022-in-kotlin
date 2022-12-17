fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            it.chunkAtIndex(it.length / 2)
        }
            .map { strings ->
                val things = strings.get(0).toCharArray().toSet()
                val wrongThing = strings.get(1).filter { things.contains(it) }
                    .first()
                wrongThing.getPriority()
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        val badge = input.chunked(3).map {
            it.get(0)
                .toCharArray()
                .filter { element -> it.get(1).contains(element) }
                .filter { element -> it.get(2).contains(element) }
                .first()
        }
        return badge.map { it.getPriority() }.sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun Char.getPriority(): Int {
    return if (this.isLowerCase()) {
        Character.getNumericValue(this).minus(9)
    } else {
        Character.getNumericValue(this).plus(17)
    }
}
