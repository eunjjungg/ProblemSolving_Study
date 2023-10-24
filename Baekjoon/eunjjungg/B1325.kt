package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val nodes = Array(size+1){ArrayList<Int>()}
    repeat(m) {
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        nodes[y].add(x)
    }

    fun dfs(
        parent: Int,
        visited: IntArray,
    ) {
        visited[parent] = 1
        for (i in 0 until nodes[parent].size) {
            if (visited[nodes[parent][i]] == 0) {
                dfs(nodes[parent][i], visited)
            }
        }
    }

    var max = 0
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var visited: IntArray

    (1..size).forEach {
        visited = IntArray(size + 1){ _ -> 0 }
        dfs(it, visited)

        val sum = visited.sum()
        if (sum > max) {
            max = sum
            bw = BufferedWriter(OutputStreamWriter(System.out))
            bw.append("$it ")
        } else if (sum == max) { bw.append("$it ") }
    }

    bw.flush()
    bw.close()
}
