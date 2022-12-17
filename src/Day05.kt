import kotlin.collections.ArrayList

fun main() {
    fun part1(input: String): String {
        val parsedSplit = input.split("\n")
        val splitIndex = parsedSplit.indexOfFirst(String::isBlank)
        val map = parsedSplit.filterIndexed { index, _ -> index < splitIndex }.toList()
        val steps = parsedSplit.filterIndexed { index, _ -> index > splitIndex }.toList()
        val inputCols = arrayListOf<ArrayList<Char>>()
        val literalFinder = "\\[.]\\s?".toRegex()
        val numberFinder = "^move (\\d+) from (\\d+) to (\\d+)\\r?$".toRegex()
        map
            .reversed()
            .map { row ->
                row
                    .windowed(4, 4, true)
                    .mapIndexed { i, maybeLetter ->
                        if (i >= inputCols.size) inputCols.add(ArrayList())
                        if (maybeLetter.matches(literalFinder)) {
                            inputCols[i].add(maybeLetter[1])
                        }
                    }
            }

        steps
            .map { command ->
                val numbers = mutableListOf<Int>()

                if (numberFinder.matches(command)) {
                    val matchResult = numberFinder.find(command)
                    matchResult!!.groupValues.mapIndexed { i, group ->
                        if (i != 0) numbers.add(group.toInt())
                    }
                }

                if (numbers.size > 0) {
                    val numOfElementsToMove = numbers[0] - 1
                    val wasCol = numbers[1] - 1
                    val nowCol = numbers[2] - 1
                    for (i in inputCols[wasCol].size - 1 downTo inputCols[wasCol].size - 1 - numOfElementsToMove) {
                        inputCols[nowCol].add(inputCols[wasCol][i])
                        inputCols[wasCol].removeAt(i)

                    }
                }
            }
        return inputCols.map { if (it.size > 0) it[it.size - 1] else "" }.joinToString("", "", "")
    }

    fun part2(input: String): String {
        val parsedSplit = input.split("\n")
        val splitIndex = parsedSplit.indexOfFirst(String::isBlank)
        val map = parsedSplit.filterIndexed { index, _ -> index < splitIndex }.toList()
        val steps = parsedSplit.filterIndexed { index, _ -> index > splitIndex }.toList()
        val inputCols = arrayListOf<ArrayList<Char>>()
        val literalFinder = "\\[.]\\s?".toRegex()
        val numberFinder = "^move (\\d+) from (\\d+) to (\\d+)\\r?$".toRegex()
        map
            .reversed()
            .map { row ->
                row
                    .windowed(4, 4, true)
                    .mapIndexed { i, maybeLetter ->
                        if (i >= inputCols.size) inputCols.add(ArrayList())
                        if (maybeLetter.matches(literalFinder)) {
                            inputCols[i].add(maybeLetter[1])
                        }
                    }
            }

        steps
            .map { command ->
                val numbers = mutableListOf<Int>()

                if (numberFinder.matches(command)) {
                    val matchResult = numberFinder.find(command)
                    matchResult!!.groupValues.mapIndexed { i, group ->
                        if (i != 0) numbers.add(group.toInt())
                    }
                }

                if (numbers.size > 0) {
                    val numOfElementsToMove = numbers[0] - 1
                    val wasCol = numbers[1] - 1
                    val nowCol = numbers[2] - 1
                    for (i in inputCols[wasCol].size - 1 - numOfElementsToMove until inputCols[wasCol].size) {
                        inputCols[nowCol].add(inputCols[wasCol][i])
                    }
                    for (i in inputCols[wasCol].size - 1 downTo inputCols[wasCol].size - 1 - numOfElementsToMove) {
                        inputCols[wasCol].removeAt(i)
                    }
                }
            }
        return inputCols.map { if (it.size > 0) it[it.size - 1] else "" }.joinToString("", "", "")
    }


// test if implementation meets criteria from the description, like:
    val testInput = read("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = read("Day05")
    println(part1(input))
    println(part2(input))
}

