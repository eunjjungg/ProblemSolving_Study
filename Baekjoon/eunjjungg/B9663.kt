package baekjoon

import java.util.*
import kotlin.math.abs

private var sum = 0
private var n = 0

fun main() = with(Scanner(System.`in`)){
    n = nextInt()
    val list = MutableList<Int>(n){ _-> 0}
    dfs(0, list, n)
    println(sum)
}

fun isValid(index: Int, arr: List<Int>): Boolean {
    for (i in 0 until index) {
        if (arr[i] == arr[index] || (index - i == abs(arr[index] - arr[i]))) {
            return false
        }
    }
    return true
}

fun dfs(index: Int, arr: MutableList<Int>, n: Int) {
    if (index == n) {
        sum++
        return
    }

    for (i in 0 until n) {
        arr[index] = i
        if (isValid(index, arr)) {
            dfs(index + 1, arr, n)
        }
    }
}

// 시간초과 발생하는 풀이
private fun mainTimeLimit() = with(Scanner(System.`in`)){
    n = nextInt()

    // 좌표 만들기
    val list = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        for (j in 0 until n) {
            list.add(Pair(i, j))
        }
    }

    (0 until n * n).forEach { _ ->
        dfsTimeLimit(list, list[0], n)
        list.removeAt(0)
    }

    println(sum)
}

// 시간초과 발생하는 풀이
private fun dfsTimeLimit(
    list: List<Pair<Int, Int>>,
    position: Pair<Int, Int>,
    rem: Int,
) {
    val nRem = rem - 1

    if (nRem == 0) {
        sum++
        return
    }

    val nList = list.toMutableList()
    nList.removeIf {
        it.first == position.first ||
                it.second == position.second ||
                it.first - position.first == it.second - position.second ||
                it.first - position.first == - it.second + position.second ||
                (it.first <= position.first && it.second <= position.second)
    }

    nList.find { it.first == position.first + 1 } ?: return
    // 남은거로는 만들수 없다면
    if (nRem > nList.size) {
        return
    }

    nList.indices.forEach { _ ->
        dfsTimeLimit(nList, nList[0], nRem)
        nList.removeAt(0)
    }


}
