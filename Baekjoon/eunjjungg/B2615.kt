import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// 5돌이면서 6돌이라면 이긴거라니..
// 그런 말은 없었잖아요ㅠㅠ
fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st: StringTokenizer
    val board = Array<IntArray>(20) { IntArray(20) }
    val win = 1
    val lose = 0
    val six = 100
    val empty = 0

    repeat(19) { row ->
        st = StringTokenizer(br.readLine())
        repeat(19) { board[row + 1][it + 1] = st.nextToken().toInt() }
    }

    // -
    fun checkRow(row: Int, idx: Int): Int {
        if (idx <= 15) {
            val tmp = board[row][idx]
            repeat(4) { if (tmp != board[row][idx + it + 1]) { return lose } }
            if (idx > 1 && tmp == board[row][idx - 1]) { return six }
            if (idx <= 14 && tmp == board[row][idx + 5]) { return six }
            return win
        }
        return lose
    }

    // |
    fun checkIdx(row: Int, idx: Int): Int {
        if (row <= 15) {
            val tmp = board[row][idx]
            repeat(4) { if (tmp != board[row + it + 1][idx]) { return lose } }
            if (row > 1 && tmp == board[row - 1][idx]) { return six }
            if (row <= 14 && tmp == board[row + 5][idx]) { return six }
            return win
        }
        return lose
    }

    // \
    fun checkRightBottom(row: Int, idx: Int): Int {
        if (row <= 15 && idx <= 15) {
            val tmp = board[row][idx]
            repeat(4) { if (tmp != board[row + it + 1][idx + it + 1]) { return lose } }
            if (row > 1 && idx > 1 && tmp == board[row - 1][idx - 1]) { return six }
            if (row <= 14 && idx <= 14 && tmp == board[row + 5][idx + 5]) { return six }
            return win
        }
        return lose
    }

    // /
    fun checkRightTop(row: Int, idx: Int): Int {
        if (idx <= 15 && row >= 5) {
            val tmp = board[row][idx]
            repeat(4) { if (tmp != board[row - it - 1][idx + it + 1]) { return lose } }
            if (idx >= 2 && row <= 18 && tmp == board[row + 1][idx - 1]) { return six }
            if (idx <= 14 && row >= 6 && tmp == board[row - 5][idx + 5]) { return six }
            return win
        }
        return lose
    }

    fun checkAllDirection(row: Int, idx: Int): Pair<Int, Int>? {
        val sum = checkRow(row, idx) + checkIdx(row, idx) +
                checkRightBottom(row, idx) + checkRightTop(row, idx)

        return if (sum % 100 == 0) { null }
        else { Pair(row, idx) }
    }

    (1..19).forEach { idx ->
        (1..19).forEach { row ->
            if (board[row][idx] != empty && checkAllDirection(row, idx) != null) {
                val pair = checkAllDirection(row, idx)!!
                println(board[pair.first][pair.second])
                println("${pair.first} ${pair.second}")
                return
            }
        }
    }

    println(0)
}