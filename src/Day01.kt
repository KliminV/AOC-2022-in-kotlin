fun main() {
    fun part1(input: List<String>): Int {
        if (input.isEmpty()) return 0;
        var curIdx: Int = 1;
        var maxIdx: Int = 0;
        var curWeight: Int = 0
        var maxTotalCalories: Int = 0;
        for (line in input) {
            when (line) {
                "" -> {
                    maxTotalCalories = if (maxTotalCalories > curWeight) maxTotalCalories else curWeight
                    maxIdx = if (maxTotalCalories > curWeight) maxIdx else curIdx
                    curIdx++
                    curWeight = 0
                }
                else -> {
                    curWeight += line.toInt()
                }
            }
        }
        if (curWeight!=0) {
            maxTotalCalories = if (maxTotalCalories > curWeight) maxTotalCalories else curWeight
        }
        return maxTotalCalories
    }

    fun part2(input: List<String>): Int {
        if (input.isEmpty()) return 0;
        var curWeight: Int = 0

        var maxTotalCalories: Int = 0;
        var secondTotalCalories: Int = 0
        var thirdTotalCalories: Int = 0

        for (line in input) {
            when (line) {
                "" -> {
                    if (curWeight > thirdTotalCalories) {
                        if (curWeight > secondTotalCalories) {
                            if (curWeight > maxTotalCalories) {
                                val storage = curWeight
                                curWeight = maxTotalCalories
                                maxTotalCalories = storage
                            }
                            val storage = curWeight
                            curWeight = secondTotalCalories
                            secondTotalCalories = storage
                        }
                        thirdTotalCalories = curWeight
                    }
                    curWeight = 0
                }
                else -> {
                    curWeight += line.toInt()
                }
            }
        }
        if (curWeight!=0) {
            if (curWeight > thirdTotalCalories) {
                if (curWeight > secondTotalCalories) {
                    if (curWeight > maxTotalCalories) {
                        val storage = curWeight
                        curWeight = maxTotalCalories
                        maxTotalCalories = storage
                    }
                    val storage = curWeight
                    curWeight = secondTotalCalories
                    secondTotalCalories = storage
                }
                thirdTotalCalories = curWeight
            }
        }
        return maxTotalCalories + secondTotalCalories + thirdTotalCalories
    }

    fun List<List<Int>>.topNElfes(n: Int): Int {
        return map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
