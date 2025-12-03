class Day03: IDay {
    private val lines: List<String> = FileUtils.readLines("Day03.txt")

    private val batteryBanks = getBatteryBanks()

    override fun partOne() {
        var sum: Long = 0

        batteryBanks.forEach {
            val result = getBiggestBatteriesSum(it, 2)
            sum += result
        }

        println("   Result: $sum")
    }

    override fun partTwo() {
        var sum: Long = 0

        batteryBanks.forEach {
            val result = getBiggestBatteriesSum(it, 12)
            sum += result
        }

        println("   Result: $sum")
    }

    private fun getBatteryBanks(): List<List<Int>> {
        return lines.map {
            it.split("")
                .filter { value -> value.isNotEmpty() }
                .map { value -> value.toInt() }
        }
    }

    // Simple implementation for part 1
    private fun getBiggestBatteriesSumPart1(banks: List<Int>): Int {
        var biggestFirst = 0
        var biggestSecond = 0

        banks.forEachIndexed { index, value ->
            if(value > biggestFirst && index != banks.count() - 1) {
                biggestFirst = value
                biggestSecond = 0
                return@forEachIndexed
            }

            if(value > biggestSecond) {
                biggestSecond = value
            }
        }

        return "$biggestFirst$biggestSecond".toInt()
    }

    private fun getBiggestBatteriesSum(banks: List<Int>, size: Int): Long {
        val numbers = Array(size) { 0 }

        banks.forEachIndexed { index, value ->
            var reset = false
            numbers.forEachIndexed inner@{ i, number ->
                if(reset) {
                    numbers[i] = 0
                    return@inner
                }

                if(value > number && index <= (banks.size - (size - i))) {
                    numbers[i] = value
                    reset = true
                }
            }
        }

        val result = StringBuilder()
        numbers.forEach { result.append(it) }

        return result.toString().toLong()
    }
}