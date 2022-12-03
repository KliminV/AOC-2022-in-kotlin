fun main() {

    fun part1(input: List<String>): Int {
        if (input.isEmpty()) return 0;
        var totalPoints = 0;
        for (line in input) {
            val split = line.split(" ");
            val playerOneChoice = decrypt(split.get(0)[0])
            val playerTwoChoice = decrypt(split.get(1)[0])
            totalPoints += playerTwoChoice.points + roundPoints(playerOneChoice, playerTwoChoice)
        }
        return totalPoints
    }


    fun part2(input: List<String>): Int {
        if (input.isEmpty()) return 0;
        var totalPoints = 0;
        for (line in input) {
            val split = line.split(" ");
            val playerOneChoice = decrypt(split.get(0)[0])
            val playerTwoGoal = gameResult(split.get(1)[0])
            totalPoints += playerTwoGoal + decryptReverse(playerTwoGoal, playerOneChoice)
        }
        return totalPoints
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class GameChoice(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3),
    UNKNOWN(0);


}

fun decrypt(input: Char): GameChoice {
    return when (input) {
        'A', 'X' -> GameChoice.ROCK
        'B', 'Y' -> GameChoice.PAPER
        'C', 'Z' -> GameChoice.SCISSORS
        else -> GameChoice.UNKNOWN
    }
}

fun decryptReverse(points: Int, opponentChoice: GameChoice): Int {
    if (points == 3) return opponentChoice.points
    return when (opponentChoice) {
        GameChoice.ROCK ->
            when (points) {
                0 -> GameChoice.SCISSORS.points
                6 -> GameChoice.PAPER.points
                else -> -1
            }
        GameChoice.PAPER ->
            when (points) {
                0 -> GameChoice.ROCK.points
                6 -> GameChoice.SCISSORS.points
                else -> -1
            }
        GameChoice.SCISSORS ->
            when (points) {
                0 -> GameChoice.PAPER.points
                6 -> GameChoice.ROCK.points
                else -> -1
            }
        else -> -1
    }
}

fun gameResult(input: Char): Int {
    return when (input) {
        'X' -> 0
        'Y' -> 3
        'Z' -> 6
        else -> -1
    }
}

fun roundPoints(gameChoice1: GameChoice, gameChoice2: GameChoice): Int {
    if (gameChoice1 == gameChoice2) return 3
    return when (gameChoice1) {
        GameChoice.ROCK -> if (gameChoice2 == GameChoice.PAPER) 6 else 0
        GameChoice.SCISSORS -> if (gameChoice2 == GameChoice.ROCK) 6 else 0
        GameChoice.PAPER -> if (gameChoice2 == GameChoice.SCISSORS) 6 else 0
        else -> 0
    }
}