package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger
import java.util.*

// 공부한 것: 이항 계수...
// https://blog.naver.com/vollollov/220947452823
// https://blog.naver.com/yh6613/221167678486
fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = StringTokenizer(br.readLine()).nextToken().toInt()
    val afterProcess = 10_007.toBigInteger()
    fun BigInteger.makeAnswer(): Long = (this % afterProcess).toLong()
    val list = mutableListOf<BigInteger>()
    var answer = BigInteger.ONE
    list.add(1.toBigInteger())
    list.add((N - 1).toBigInteger())
    answer += list[1]

    var i = 2

    while (i <= N / 2) {
        var tmp = list[i - 1]
        tmp = getNMinusRMinus(N - i + 1, i - 1, tmp)
        tmp = getRPlus(N - i, i - 2, tmp)
        tmp = getRPlus(N - i, i - 1, tmp)
        list.add(tmp)
        answer += list[i]
        i++
    }

    // output
    println(answer.makeAnswer())
}

private fun getRPlus(n: Int, r: Int, value: BigInteger) = value * (n - r).toBigInteger() / (r + 1).toBigInteger()

private fun getNMinusRMinus(n: Int, r: Int, value: BigInteger) = value * r.toBigInteger() / n.toBigInteger()
