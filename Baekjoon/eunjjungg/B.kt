package baekjoon

import java.util.*

private var sum = 0
private var n = 0

fun main() = with(Scanner(System.`in`)){
    n = nextInt()

    (0 until n * n).forEach {
        val list = (it + 1 until n * n)
        dfs(
            list.toList(),
            it,
            rem = n - 1
        )
    }

    println(sum)
}

private fun dfs(
    list: List<Int>,
    x: Int,
    rem: Int,
) {
    if (rem == 0) {
        sum++
        return
    }

    val idx = x % n + 1
    val widthStart = x - idx + 1
    val widthEnd = x + n - idx
    val newL = list.toMutableList()

    newL.removeAll(widthStart..widthEnd) // 가로 제거

    (1..n).forEach { // 대각선 제거
        if (it > idx) {
            newL.remove(x + (it - idx) * (n + 1)) // 우하단 제거
        } else if (it < idx) {
            newL.remove(x + it * (n - 1)) // 좌하단 제거
        }
    }

    newL.removeIf { // 세로 제거
        (it - x) % n == 0
    }

    if (newL.isEmpty()) {
        return
    }

    println("$x : $newL, rem: $rem")

    newL.forEach {
        dfs(newL, it, rem - 1)
    }
}
