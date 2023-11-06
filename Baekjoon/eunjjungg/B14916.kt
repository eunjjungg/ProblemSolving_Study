package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = StringTokenizer(br.readLine()).nextToken().toInt()
    var memo = 0
    var ans = 0

    while ((n - memo) % 5 != 0) {
        ans++
        memo += 2
        if (memo > n) { println(-1); return }
    }

    ans += (n - memo) / 5

    // output
    println(ans)
}