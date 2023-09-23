package baekjoon

import java.util.*

private lateinit var list: MutableList<Int>
// jin sim ;; 어이없네
// stringBuilder ;;;
private val sb = StringBuilder()

fun main() = with(Scanner(System.`in`)){
    val n = nextInt()
    val m = nextInt()

    list = (1..n).toMutableList()

    if (m == 1) {
        list.forEach { println(it) }
        return@with
    }

    list.forEach {
        dfs(
            cur = it,
            tmp = "",
            rem = m
        )
    }

    println(sb)
}

private fun dfs(
    cur: Int,
    tmp: String,
    rem: Int,
) {
    val t = if (tmp == "") {
        "$cur"
    } else {
        "$tmp $cur"
    }

    if (rem - 1 == 1) {
        list.forEach {
            sb.append("$t $it\n")
        }
        return
    }

    list.forEach { index ->
        dfs(index, t, rem - 1)
    }
}
