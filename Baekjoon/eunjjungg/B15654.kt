package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val list = List<Int>(n) { 0 }.toMutableList()
    st = StringTokenizer(br.readLine())

    repeat(n) { idx ->
        list[idx] = st.nextToken().toInt()
    }

    // output
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    list.sort()

    fun dfs(remaining: Int, list: List<Int>, str: String) {
        if (remaining == 0) { bw.append(str + "\n"); return }
        list.forEach {
            val mList = list.toMutableList()
            mList.remove(it)
            dfs(remaining - 1, mList, "$str $it")
        }
    }

    list.forEach {
        val mList = list.toMutableList()
        mList.remove(it)
        dfs(m - 1, mList, "$it")
    }

    bw.flush()
    bw.close()
}