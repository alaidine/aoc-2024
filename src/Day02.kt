import kotlin.math.abs

fun main() {
    fun isPairSafe(a: Int, b: Int, decreasing: Boolean): Boolean {
        if (decreasing) {
            if (a < b) return false;
        }
        if (!decreasing) {
            if (a > b) return false;
        }
        if (abs(a - b) < 1 || abs(a - b) > 3) return false;
        return true;
    }

    fun isLevelValid(levels: List<Int>): Boolean {
        var i = 0;
        var j = 1;
        var decreasing = false;

        if (levels.size < 2) {
            return true;
        }

        if (levels[j] < levels[i]) {
            decreasing = true;
        }

        while (j < levels.size) {
            if (!isPairSafe(levels[i], levels[j], decreasing)) {
                return false;
            }
            i += 1;
            j += 1;
        }
        return true;
    }

    fun part1(input: List<String>): Int {
        var result: Int = 0;

        for (line in input) {
            val levels = line.split(" ").map { it.toInt() };
            if (isLevelValid(levels)) {
                result += 1;
            }
        }
        return result;
    }

    fun part2(input: List<String>): Int {
        var result: Int = 0;

        for (line in input) {
            val levels = line.split(" ").map { it.toInt() };

            if (isLevelValid(levels)) {
                result += 1;
            }

            if (!isLevelValid(levels)) {
                for (i in levels.indices) {
                    if (isLevelValid(levels.filterIndexed({index, _ -> index != i}))) {
                        result += 1;
                        break;
                    }
                }
            }
        }
        return result;
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
