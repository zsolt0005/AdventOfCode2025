import kotlin.math.abs

class Day01: IDay {
    private val lines: List<String> = FileUtils.readLines("Day01.txt")

    private val minPosition = 0
    private val maxPosition = 99

    override fun partOne()
    {
        var password = 0

        var currentPosition = 50
        lines.forEach {
            val isRight = it.startsWith('R')
            val amount = it.substring(1).toInt()

            currentPosition = rotatePart1(currentPosition, amount, isRight)
            if(currentPosition == 0) password++
        }

        println("   Password: $password")
    }

    override fun partTwo()
    {
        var result: Pair<Int, Int> = Pair(50, 0)

        lines.forEach {
            val isRight = it.startsWith('R')
            val amount = it.substring(1).toInt()

            result = rotatePart2(result.first, result.second, amount, isRight)
        }

        println("   Password: ${result.second}")
    }

    private fun rotatePart1(current: Int, amount: Int, isRight: Boolean): Int {
        var realAmount = if(amount >= (maxPosition + 1)) amount % (maxPosition + 1) else amount
        realAmount = if (isRight) realAmount else -realAmount

        if (current + realAmount > maxPosition) return realAmount - (maxPosition - current + 1)
        if (current + realAmount < minPosition) return maxPosition - (abs(realAmount) - current - 1)

        return current + realAmount
    }

    private fun rotatePart2(current: Int, password: Int, amount: Int, isRight: Boolean): Pair<Int, Int> {
        var (realAmount, turns) =
            if(amount >= (maxPosition + 1)) Pair(amount % (maxPosition + 1), amount / (maxPosition + 1))
            else Pair(amount, 0)

        realAmount = if (isRight) realAmount else -realAmount

        if (current + realAmount > maxPosition) {
            realAmount -= (maxPosition - current + 1)
            turns = if (realAmount == 0 || current == 0) turns else turns + 1
        }

        else if (current + realAmount < minPosition) {
            realAmount = maxPosition - (abs(realAmount) - current - 1)
            turns = if (realAmount == 0 || current == 0) turns else turns + 1
        }

        else realAmount += current

        val newPassword = password + turns + if (realAmount == 0) 1 else 0

        return Pair(realAmount, newPassword)
    }
}