package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// 스타박스 8시까지 줄세우고
// 8시에 커피하나씩 주고 자리 받으러 감
// 입실 시 팁 줌. 팁 = 원래 주려고 한 돈 - (입실 순서 - 1)
//               = original - 입실 순서 + 1
// 팁은 음수라면 0원 줌.
// original 배열 주어질 떄 팁의 최댓값 구하기
fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st: StringTokenizer
    st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val data = MutableList<Int>(size) { 0 }
    var ans: Long = 0

    repeat(size) { idx ->
        st = StringTokenizer(br.readLine())
        data[idx] = st.nextToken().toInt()
    }

    data.sortDescending()
    data.forEachIndexed { idx, original ->
        val tip = original - idx
        if (tip <= 0) { println(ans); return }
        ans += tip
    }

    // output
    println(ans)
}