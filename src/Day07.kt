fun main() {
    fun part1(input:Map<String, Int>): Int {
        return input.values
            .filter { it < 100_000 }
            .sum()
    }

    fun part2(input: Map<String, Int>): Int {
        val occupied = input[""]

        val toFree = occupied!! - 40_000_000

        val result = input.values
            .filter {
            it > toFree
        }.min()
        return result
    }

    fun buildFoldersMap(input : List<String>): Map<String, Int> {
        val folders = HashMap<String, Int>()
        var cwd = ""
        for (line in input) {
            val matchResult = PATTERN.matchEntire(line) ?: continue
            matchResult.groups[1]?.value?.let { dir ->
                cwd = when (dir) {
                    "/" -> ""
                    ".." -> cwd.substringBeforeLast('/', "")
                    else -> if (cwd.isEmpty()) dir else "$cwd/$dir"
                }
            } ?: matchResult.groups[2]?.value?.toIntOrNull()?.let {
                var dir = cwd
                while (true) {
                    folders[dir] = folders.getOrElse(dir) { 0 } + it
                    if (dir.isEmpty()) break
                    dir = dir.substringBeforeLast('/', "")
                }
            }
        }
        return folders
    }

// test if implementation meets criteria from the description, like:
    val testInput = buildFoldersMap(readInput("Day07_test"))

    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = buildFoldersMap(readInput("Day07"))
    println(part1(input))
    println(part2(input))
}

val PATTERN = """[$] cd (.*)|(\d+).*""".toRegex()


