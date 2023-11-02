package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val nodes = HashMap<String, Pair<String, String>>()

    repeat(size) {
        st = StringTokenizer(br.readLine())
        nodes[st.nextToken()] = Pair(st.nextToken(), st.nextToken())
    }

    // root -> left -> right
    fun first(root: String) {
        bw.append(root)
        val left = nodes[root]!!.first
        val right = nodes[root]!!.second

        if (left != ".") { first(left) }
        if (right != ".") { first(right) }
    }

    first("A")

    // left -> root -> right
    fun mid(root: String) {
        val left = nodes[root]!!.first
        val right = nodes[root]!!.second

        if (left != ".") { mid(left) }
        bw.append(root)
        if (right != ".") { mid(right) }
    }

    bw.append('\n')
    mid("A")

    fun last(root: String) {
        val left = nodes[root]!!.first
        val right = nodes[root]!!.second

        if (left != ".") { last(left) }
        if (right != ".") { last(right) }
        bw.append(root)
    }

    bw.append('\n')
    last("A")

    bw.flush()
    bw.close()
}
