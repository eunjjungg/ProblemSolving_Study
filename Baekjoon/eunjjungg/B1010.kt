package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val cases = mutableListOf<Pair<Int, Int>>()

    repeat(size) {
        st = StringTokenizer(br.readLine())
        cases.add(Pair(st.nextToken().toInt(), st.nextToken().toInt()))
    }

    val memo = Array<ULong>(30){ 0uL }
    memo[0] = 1uL
    memo[1] = 1uL

    fun recursive(idx: Int) {
        if (memo[idx - 1] == 0uL) {
            recursive(idx - 1)
        }
        memo[idx] = memo[idx - 1] * idx.toULong()
    }

    cases.forEach { pair ->
        val n = pair.second
        val r = pair.first

        if (n == r) {
            bw.append("1\n")
            return@forEach
        }

        val find = if (n - r > r) {
            n - r + 1
        } else { r + 1 }
        val bottom = if (n - r > r) {
            r
        } else { n - r }

        recursive(bottom)

        // find..n까지 곱한 값을 구해야 됨.
        var sum: ULong = find.toULong()
        (find + 1 .. n).forEach {
            sum *= it.toULong()
        }
        sum /= memo[bottom]

        bw.append("$sum\n")
    }
    bw.flush()
    bw.close()

}
