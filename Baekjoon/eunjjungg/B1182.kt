package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val s = st.nextToken().toInt()
    val list = List<Int>(n) { 0 }.toMutableList()
    st = StringTokenizer(br.readLine())

    repeat(n) { idx ->
        list[idx] = st.nextToken().toInt()
    }

    var ans = 0

    fun dfs(sum: Int, list: List<Int>) {
        if (sum == s) { ans++ }
        list.forEachIndexed { idx, i ->
            val mList = if (idx == list.size - 1) { listOf() } else {
                list.subList(idx + 1, list.size)
            }
            dfs(sum + i, mList)
        }
    }

    list.forEachIndexed { idx, i ->
        val mList = if (idx == list.size - 1) { listOf() } else {
            list.subList(idx + 1, list.size)
        }
        dfs(i, mList)
    }

    println(ans)
}