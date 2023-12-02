fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            val digitOnly = it.replace("\\D".toRegex(), "")
            val fd = digitOnly[0].toString()
            val ld = digitOnly[digitOnly.length - 1].toString()
            return@map (fd + ld).toInt()
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val digitDict = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        val result = input.map { line ->
            var result = line
            var i = 0;
//            println(line)
            while (i <= result.length) {
                for ((letter, number) in digitDict) {
                    val endIndex = (i + letter.length).coerceAtMost(result.length)
                    val substr = result.substring(i, endIndex)
//                    println("$substr == $letter")
                    if (substr == letter) {
                        result = result.replace(substr, number.toString() + substr.last())
//                        println("true $result")
                        break
                    }
                }
                i++
            }
            val digitOnly = result.replace("\\D".toRegex(), "")
            val fd = digitOnly[0].toString()
            val ld = digitOnly[digitOnly.length - 1].toString()
//            println((fd + ld).toInt())
            return@map (fd + ld).toInt()
        }
        return result.sum()
    }

    println("Test Input:")
    val testInput = readInput("Day01_test")
    println(part1(testInput))

    val testInputPart2 = readInput("Day01_test2")
    println(part2(testInputPart2))

    println("\nInput:")
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
