package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st: StringTokenizer = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val data = MutableList<Int>(size) { 0 }

    st = StringTokenizer(br.readLine())
    repeat(size) { idx ->
        data[idx] = st.nextToken().toInt()
    }

    data.sortDescending()
    var answer: Double = data.removeFirst().toDouble()

    while (data.isNotEmpty()) {
        answer += data.removeFirst().toDouble() / 2
    }

    // output
    if (answer > answer.toInt()) {
        println(answer)
    } else { println(answer.toInt()) }

}