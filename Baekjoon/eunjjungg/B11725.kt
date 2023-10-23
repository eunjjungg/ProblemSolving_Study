package baekjoon

// 1 - 6, 4
// 2 - 4
// 3 - 5, 6
// 4 - 1, 2, 7
// 5 - 3
// 6 - 1, 3
// 7 - 4

// with dfs
// 4 6 1 3 1 4
// 1 - 6 4 -> 6, 4 -> 1
// 6 - 1, 3 -> 1 no 3 -> 6
// 3 - 5, 6 -> 6 no 3 -> 5
// 5 - 3 -> end
// 4 - 1,2,7 -> 1 no 2 -> 4 / 7 -> 4

fun main(args: Array<String>) {
    // input
    val n = readln().toInt()
    val nodes = ArrayList<Nodes>()
    val undefined = -1
    repeat(n - 1) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        nodes.add(Nodes(x, y))
    }

    // data
    val answer = Array<Int>(n + 1){ _ -> undefined }
    val hash = HashMap<Int, MutableSet<Int>>()

    // data setting
    nodes.forEach { node ->
        val setX = hash[node.x] ?: mutableSetOf<Int>()
        setX.add(node.y)
        hash[node.x] = setX

        val setY = hash[node.y] ?: mutableSetOf<Int>()
        setY.add(node.x)
        hash[node.y] = setY
    }

    fun dfs(i: Int) {
        val relates = hash[i]!!
        relates.forEach {
            if (answer[it] == undefined) {
                answer[it] = i
                dfs(it)
            }
        }
    }

    dfs(1)

    for (i in 2..n) {
        println(answer[i])
    }
}

private data class Nodes(val x: Int, val y: Int)