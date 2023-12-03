import kotlin.contracts.contract

data class Game(
    val id: Int,
    val gameSets: List<GameSets>
) {
    companion object {
        fun parse(raw: String): Game {
            val data = raw.split(": ")
            return Game(
                id = data[0].split(" ")[1].toInt(),
                gameSets = GameSets.parse(data[1])
            )
        }
    }
}

data class GameSets(
    val setNumber: Int = 0,
    val red: Int = 0,
    val green: Int = 0,
    val blue: Int = 0
) {
    companion object {
        fun parse(raw: String): List<GameSets> {
            val data = raw.replace(" ", "").split(";")
            return data.indices.map { i ->
                val colorList = listOf("red", "green", "blue").map { color ->
                    var colorValue = 0
                    data[i].split(",").forEach {
                        if (it.replace("\\d".toRegex(), "") == color) {
                            colorValue = it.replace("\\D".toRegex(), "").toInt()
                        }
                    }
                    colorValue
                }
                GameSets(
                    setNumber = i,
                    red = colorList[0],
                    green = colorList[1],
                    blue = colorList[2]
                )
            }
        }
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        val maxRed = 12
        val maxGreen = 13
        val maxBlue = 14

        val result = input.map {
            Game.parse(it).let { game ->
                game.gameSets.forEach { set ->
                    if (set.red > maxRed) return@map 0
                    if (set.green > maxGreen) return@map 0
                    if (set.blue > maxBlue) return@map 0
                }

                return@map game.id
            }
        }
        result.println()
        return result.sum()
    }

    fun part2(input: List<String>): Int {
        val result = input.map {
            Game.parse(it).let { game ->
                return@map game.gameSets.maxOf { it.red } *
                        game.gameSets.maxOf { it.blue } *
                        game.gameSets.maxOf { it.green }
            }
        }
        return result.sum()
    }

    println("Test Input:")
    val testInput = readInput("Day02_test")
    println(part1(testInput))

    val testInput2 = readInput("Day02_test2")
    println(part2(testInput2))

    println("\nInput:")
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

}
