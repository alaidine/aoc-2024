import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var result: Int = 0;
        val firstList: MutableList<Int> = mutableListOf(0);
        val secondList: MutableList<Int> = mutableListOf(0);

        for (line in input) {
            val splitLine: List<String> = line.split("   ");

            val firstNumber = splitLine.first().toIntOrNull();
            val secondNumber = splitLine.last().toIntOrNull();

            if (firstNumber != null && secondNumber != null) {
                firstList.add(secondNumber);
                secondList.add(firstNumber);
            }
        }

        firstList.sort();
        secondList.sort();

        for (i in firstList.indices) {
            result += abs(firstList[i] - secondList[i]);
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result: Int = 0;
        val count: HashMap<Int, Int> = hashMapOf<Int, Int>();
        val list: MutableList<Int> = mutableListOf();

        for (line in input) {
            val splitLine: List<String> = line.split("   ");
            val firstNumber = splitLine.first().toIntOrNull();
            val secondNumber = splitLine.last().toIntOrNull();

            if (firstNumber != null && secondNumber != null) {
                if (!count.containsKey(secondNumber)) {
                    count[secondNumber] = 1;
                } else {
                    count[secondNumber] = count[secondNumber]!! + 1;
                }
                list.add(firstNumber);
            }
        }

        for (i in list.indices) {
            if (count.containsKey(list[i])) {
                result += list[i] * count[list[i]]!!;
            }
        }

        return result
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("3   4",
            "4   3",
            "2   5",
            "1   3",
            "3   9",
            "3   3")) == 11)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
