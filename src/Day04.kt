fun main() {
    fun part1(input: List<String>): Int {
        return input.asSequence().map {
            it.split(",", "-")
        }
            .flatten().map {
                it.toInt()
            }.chunked(4).map { (a, b, c, d) ->
                if (a == c) 1
                else if (a < c) {
                    if (b >= d) 1 else 0
                } else {
                    if (b <= d) 1 else 0
                }
            }.sum()
    }

    fun part2(input: List<String>): Int {


    return input.asSequence().map {
        it.split(",", "-")
    }
        .flatten().map {
            it.toInt()
        }.chunked(4).map { (a, b, c, d) ->
            if ((b < c) ||
                (a > d)
            ) 0 else 1
        }.sum()
}




// test if implementation meets criteria from the description, like:
val testInput = readInput("Day04_test")
check(part1(testInput) == 2)
check(part2(testInput) == 4)

val input = readInput("Day04")
println(part1(input))
println(part2(input))
}

