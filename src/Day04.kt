fun main() {
    fun outOfBounds(pos: MutableList<Int>, input: List<String>): Boolean {
        val rows: Int = input.size;
        val cols: Int = input[0].length;

        return pos[0] < 0 || pos[1] < 0 || pos[0] == rows || pos[1] == cols;
    }

    fun part1(input: List<String>): Int {
        var result: Int = 0;
        val curPos: MutableList<Int> = mutableListOf(0, 0);
        val directions: List<List<Int>> = listOf(
            listOf(0, 1),
            listOf(0, -1),
            listOf(1, 0),
            listOf(-1, 0),
            listOf(1, 1),
            listOf(1, -1),
            listOf(-1, 1),
            listOf(-1, -1),
        );

        for (line in input) {
            for (index in line.indices) {
                curPos[1] = index;
                val words: MutableList<String> = ArrayList();
                for (direction in directions) {
                    var word: String = line[index].toString();
                    for (i in 1..3) {
                        if (outOfBounds(mutableListOf(curPos[0] + (direction[0] * i), curPos[1] + (direction[1] * i)), input))
                            break;
                        word = word.plus(input[curPos[0] + (direction[0] * i)][curPos[1] + (direction[1] * i)]);
                    }
                    words.add(word);
                }
                for (word in words) {
                    if (word == "XMAS")
                        result++;
                }
            }
            curPos[0]++;
        }
        return result;
    }

    fun containsCrossMas(input: List<String>, y: Int, x: Int): Boolean {
        val firstDiagonal: String = input[y][x].toString().plus(input[y + 1][x + 1]).plus(input[y + 2][x + 2]);
        val secondDiagonal: String = input[y][x + 2].toString().plus(input[y + 1][x + 1]).plus(input[y + 2][x]);

        if (firstDiagonal == "MAS" || firstDiagonal == "SAM") {
            if (secondDiagonal == "SAM" || secondDiagonal == "MAS") {
                return true
            }
        }
        return false;
    }

    fun part2(input: List<String>): Int {
        var result: Int = 0;
        var i: Int = 0;
        var j: Int = 0;

        while (i < (input.size - 2)) {
            j = 0;
            while (j < (input[0].length - 2)) {
                if (containsCrossMas(input, i, j))
                    result++;
                j++;
            }
            i++;
        }
        return result;
    }

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
