fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            val digitOnly = it.replace("\\D".toRegex(),"")
            val fd = digitOnly[0].toString()
            val ld = digitOnly[digitOnly.length-1].toString()
            return@map (fd+ld).toInt()
        }.sum()
    }

    val testInput = readInput("Day01_test")
    println(part1(testInput))

    val input = readInput("Day01")
    part1(input).println()
}
