package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st: StringTokenizer
    st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val data = Array<Int>(size) { 0 }
    val sum = Array<Int>(size) { 0 }

    st = StringTokenizer(br.readLine())
    repeat(size) { idx ->
        data[size - idx - 1] = st.nextToken().toInt()
    }

    sum[0] = data[0]

    (1 until size).forEach { idx ->
        val tmp = if (sum[idx - 1] + data[idx] > data[idx]) {
            sum[idx - 1] + data[idx]
        } else { data[idx] }
        sum[idx] = tmp
    }

    // output
    println(sum.max())
}