package baekjoon

import java.util.*

fun main() = with(Scanner(System.`in`)){
    val n = nextInt()
    val m = nextInt()
    val rem: Int = m

    (1..n).forEach {
        val visited = MutableList<Boolean>(n + 1){ false }
        visited[0] = true

        dfs(
            visited = visited,
            cur = it,
            tmp = "",
            rem = rem
        )
    }
}

fun dfs(
    visited: MutableList<Boolean>,
    cur: Int,
    tmp: String,
    rem: Int
) {
    val v = mutableListOf<Boolean>()
    v.addAll(visited)

    v[cur] = true
    val t = if (tmp == "") {
        "$cur"
    } else {
        "$tmp $cur"
    }

    if (rem - 1 == 0) {
        println(t)
        return
    }

    v.forEachIndexed { index, b ->
        if (!b) {
            dfs(v, index, t, rem - 1)
        }
    }
}
