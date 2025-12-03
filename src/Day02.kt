class Day02: IDay {
    private val input: String = FileUtils.readText("Day02.txt")

    override fun partOne() {
        val ranges = getRanges()

        var invalidIdsCount: Long = 0
        ranges.forEach { (min, max) ->
            for (i in min.toLong()..max.toLong()) {
                if (!isIdValidPart1(i.toString())) {
                    invalidIdsCount += i
                }
            }
        }

        println("   Invalid IDs count: $invalidIdsCount")
    }

    override fun partTwo() {
        val ranges = getRanges()

        var invalidIdsCount: Long = 0
        ranges.forEach { (min, max) ->
            for (i in min.toLong()..max.toLong()) {
                if (!isIdValidPart2(i.toString())) {
                    invalidIdsCount += i
                }
            }
        }

        println("   Invalid IDs count: $invalidIdsCount")
    }

    private fun getRanges(): List<Pair<String, String>> {
        val pairs = mutableListOf<Pair<String, String>>()

        input.split(",")
            .forEach { range ->
                val (min, max) = range.split("-")
                pairs.add(Pair(min, max))
            }

        return pairs
    }

    private fun isIdValidPart1(id: String): Boolean {
        if (id.length % 2 != 0) {
            return true
        }

        val firstHalf = id.take(id.length / 2)
        val secondHalf = id.substring(id.length / 2)
        return firstHalf != secondHalf
    }

    private fun isIdValidPart2(id: String): Boolean {
        if(id.length == 2) {
            return id[0] != id[1]
        }

        val half = id.length / 2
        val start = if (id.length % 2 == 0) 2 else 1

        for (i in start..half step 1) {
            val section = id.take(i)
            val repeats = (id.length / i)

            if(section.repeat(repeats) == id) return false
        }

        return true
    }
}