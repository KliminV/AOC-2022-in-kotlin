fun main() {
    fun part1(input: Array<Array<Int>>): Int {
        val topView = Array(input.size) { Array(input[0].size) { 0 } }
        val leftView = Array(input.size) { Array(input[0].size) { 0 } }
        val bottomView = Array(input.size) { Array(input[0].size) { 0 } }
        val rightView = Array(input.size) { Array(input[0].size) { 0 } }
        for (i in input.indices) {
            for (j in input[0].indices) {
                leftView[i][j] = if (j == 0) input[i][j] else leftView[i][j - 1].coerceAtLeast(input[i][j])
                topView[i][j] = if (i == 0) input[i][j] else topView[i - 1][j].coerceAtLeast(input[i][j])
            }
        }
        for (i in input.size - 1 downTo 0) {
            for (j in input[0].size - 1 downTo 0) {
                rightView[i][j] =
                    if (j == input[0].size - 1) input[i][j] else rightView[i][j + 1].coerceAtLeast(input[i][j])
                bottomView[i][j] = if (i == input.size - 1) input[i][j] else bottomView[i + 1][j].coerceAtLeast(input[i][j])
            }
        }
        var result = (input.size ) * 2 + (input[0].size ) * 2 - 4
        for (i in input.indices) {
            for (j in input[0].indices) {
                if (i == 0 || j == 0 || i == input.size - 1 || j == input[0].size - 1) {
                    continue
                }
                if (input[i][j] > leftView[i][j - 1] ||
                    input[i][j] > rightView[i][j + 1] ||
                    input[i][j] > topView[i - 1][j] ||
                    input[i][j] > bottomView[i + 1][j]
                ) {
                    result += 1
                }
            }
        }
        return result
    }

    fun part2(input: Array<Array<Int>>): Int {
        var top = 0
        for (i in input.indices) {
            for (j in input[0].indices) {
                if (i == 0 || j == 0 || i == input.size - 1 || j == input[0].size - 1) {
                    continue
                }
                val current = input[i][j]
                var leftIterator = i-1
                var rightIterator = i+1
                while (leftIterator > 0) {
                    if (input[leftIterator][j] < current) leftIterator-- else break
                }
                while (rightIterator < input.size-1) {
                    if (input[rightIterator][j] < current) rightIterator++ else break
                }

                var topIterator = j-1
                var bottomIterator = j+1
                while (topIterator > 0) {
                    if (input[i][topIterator] < current) topIterator-- else break
                }
                while (bottomIterator < input[0].size-1) {
                    if (input[i][bottomIterator] < current) bottomIterator++ else break
                }
                val score = (i - leftIterator) * (rightIterator - i) * (j - topIterator) * (bottomIterator - j)
                top =
                    top.coerceAtLeast(score)
            }
        }
        return top
    }

    fun build2DArray(input: List<String>): Array<Array<Int>> {
        val result = Array(input.size) { Array(input.first().length) { 0 } }
        for (i in input.indices) {
            input[i].toCharArray()
                .mapIndexed { j, it ->
                    result[i][j] = it.digitToInt()
                }
        }
        return result
    }

// test if implementation meets criteria from the description, like:
    val testInput = build2DArray(readInput("Day08_test"))

    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = build2DArray(readInput("Day08"))
    println(part1(input))
    println(part2(input))
}



