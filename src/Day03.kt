fun main() {
    fun getInstruction(s: String, startingIndex: Int): Int {
        var index: Int = startingIndex;

        if (!s.substring(startingIndex, s.length).startsWith("mul"))
            return -1;
        index += 3;
        if (index > s.length - 1 || s[index] != '(')
            return -1;
        index += 1;
        if (!s[index].isDigit())
            return -1;

        var firstDigit: Int = 0;
        var secondDigit: Int = 0;

        while (s[index].isDigit()) {
            firstDigit *= 10;
            firstDigit += s[index] - '0';
            index++;
        }

        if (s[index] != ',')
            return -1;

        index++;

        if (!s[index].isDigit())
            return -1;

        while (s[index].isDigit()) {
            secondDigit *= 10;
            secondDigit += s[index] - '0';
            index++;
        }

        if (s[index] != ')')
            return -1;

        return firstDigit * secondDigit;
    }

    fun part1(input: List<String>): Int {
        var result: Int = 0;
        var enabled: Boolean = true;

        for (line in input) {
            for (i in line.indices) {
                val n = getInstruction(line, i);
                if (n != -1 && enabled)
                    result += n;
            }
        }
        return result;
    }

    fun part2(input: List<String>): Int {
        var result: Int = 0;
        var enabled: Boolean = true;

        for (line in input) {
            for (i in line.indices) {
                if (line.substring(i, line.length).startsWith("don't()") && enabled)
                    enabled = false;
                if (line.substring(i, line.length).startsWith("do()") && !enabled)
                    enabled = true;
                val n = getInstruction(line, i);
                if (n != -1 && enabled)
                    result += n;
            }
        }
        return result;
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
