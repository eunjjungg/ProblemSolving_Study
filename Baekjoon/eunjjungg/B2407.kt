package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    var r = st.nextToken().toInt()
    if (n - r < r) { r = n - r }
    val arr = Array<BigInteger>(r + 1){ BigInteger.ONE };
    arr[1] = n.toBigInteger()

    (2..r).forEach {
        arr[it] = arr[it - 1] * (n - it + 1).toBigInteger() / it.toBigInteger()
    }

    // output
    println(arr[r])
}
