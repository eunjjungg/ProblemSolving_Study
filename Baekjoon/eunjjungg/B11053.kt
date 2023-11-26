package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val data = Array<Int>(size) { 0 }
    var answer = Int.MIN_VALUE
    val itself = 1

    st = StringTokenizer(br.readLine())
    repeat(size) { idx ->
        data[size - 1 - idx] = st.nextToken().toInt()
    }

    val indexList = data.toList().distinct().sorted()

    val map = HashMap<Int, List<Int>>()

    fun findAndUpdateList(num: Int, lst: MutableList<Int>): MutableList<Int> {
        var result = lst
        val startSearchIdx = indexList.indexOf(num) + 1
        (startSearchIdx until indexList.size).forEach {
            if (map.containsKey(indexList[it])) {
                if (result.size < map[indexList[it]]!!.size + itself) {
                    result = map[indexList[it]]!!.toMutableList()
                    result.add(num)
                }
            }
        }
        return result
    }

    data.forEach {
        var lst = map[it]?.toMutableList() ?: mutableListOf(it)
        lst = findAndUpdateList(it, lst)
        if (lst.size > answer) { answer = lst.size; }
        map[it] = lst
    }

    // output
    println(answer)
}

