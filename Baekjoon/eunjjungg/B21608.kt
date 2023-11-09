package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st: StringTokenizer
    st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val size = n * n
    val data = Array<IntArray>(size) { IntArray(5) }
    val friends = Array<IntArray>(size + 1) { IntArray(4) }
    val hang = Array<Int>(size + 1){ 0 }
    val yul = Array<Int>(size + 1){ 0 }
    val school = Array<IntArray>(n + 1){ IntArray(n + 1){ 0 } }
    val MAX_EMPTY = 4

    repeat(size) {
        st = StringTokenizer(br.readLine())
        repeat(5) { idx ->
            data[it][idx] = st.nextToken().toInt()
        }
    }

    fun sitDown(std: Int, seat: Seat) {
        school[seat.h][seat.y] = std
        hang[std] = seat.h
        yul[std] = seat.y
    }

    fun settingFirst() {
        val std = data.first().first()
        sitDown(std, Seat(2, 2))
        friends[std][0] = data.first()[1]
        friends[std][1] = data.first()[2]
        friends[std][2] = data.first()[3]
        friends[std][3] = data.first()[4]
    }
    settingFirst()


    fun isFriendExist(arr: IntArray): MutableList<Int> {
        val lst = mutableListOf<Int>()
        (1..4).forEach {
            if(hang[arr[it]] != 0) { lst.add(arr[it]) }
        }
        return lst
    }

    fun findAdjacency(h: Int, y: Int): List<Int> {
        val adjacency = mutableListOf<Int>()
        if (y - 1 >= 1) { adjacency.add(school[h][y - 1]) } // left
        if (h - 1 >= 1) { adjacency.add(school[h - 1][y]) } // top
        if (y + 1 <= n) { adjacency.add(school[h][y + 1]) } // right
        if (h + 1 <= n) { adjacency.add(school[h + 1][y]) } // bottom
        return adjacency
    }

    fun findPositionIfNoFriendExist(): Seat {
        var finalH = 1
        var finalY = 1
        var max = -1

        (1..n).forEach { h ->
            (1..n).forEach { y ->
                // edit
                if (school[h][y] == 0) {
                    val emptySize = findAdjacency(h, y).filter { it == 0 }.size
                    if (emptySize == MAX_EMPTY) { return Seat(h, y) }
                    if (emptySize > max) {
                        max = emptySize
                        finalH = h
                        finalY = y
                    }
                }
            }
        }

        return Seat(finalH, finalY)
    }

    fun List<Seat>.getFinalPosition(): Seat {
        var seat = this.first().copy()
        var max = -1
        val tmp = this.toMutableList()
        val duplicated = mutableListOf<Seat>()

        if (tmp.size == tmp.toSet().size) {
            tmp.forEach {
                val size = findAdjacency(it.h, it.y).filter { it == 0 }.size
                if (max < size) { max = size; seat = it }
            }
            return seat
        }

        max = -1
        while (tmp.isNotEmpty()) {
            val comp = tmp.first()
            val size = tmp.filter { it.y == comp.y && it.h == comp.h }.size
            if (max < size) {
                seat = comp
                max = size
                duplicated.clear()
                duplicated.add(seat)
            } else if (max == size) { duplicated.add(comp) }
            tmp.remove(comp)
        }

        seat = duplicated.first()
        if (duplicated.size == 1) { return seat }

        max = -1
        // 인접하는 칸의 개수가 모두 같은 경우
        duplicated.forEach {
            val size = findAdjacency(it.h, it.y).filter { it == 0 }.size
            if (max < size) { max = size; seat = it }
        }
        return seat
    }

    fun getCandidateOfPosition(h: Int, y: Int): List<Seat> {
        val adjacency = mutableListOf<Seat>()
        if (y - 1 >= 1 && school[h][y - 1] == 0) { adjacency.add(Seat(h, y - 1)) } // left
        if (h - 1 >= 1 && school[h - 1][y] == 0) { adjacency.add(Seat(h - 1, y)) } // top
        if (y + 1 <= n && school[h][y + 1] == 0) { adjacency.add(Seat(h, y + 1)) } // right
        if (h + 1 <= n && school[h + 1][y] == 0) { adjacency.add(Seat(h + 1, y)) } // bottom
        return adjacency
    }

    (1 until data.size).forEach { i ->
        val arr = data[i]
        val std = arr[0]
        (0..3).forEach { friends[std][it] = arr[it + 1] }

        val friend = isFriendExist(arr)

        if (friend.isEmpty()) {
            val seat = findPositionIfNoFriendExist()
            sitDown(std, seat)
            return@forEach
        }

        val seatList = mutableListOf<Seat>()
        friend.forEach { idx -> seatList.addAll(getCandidateOfPosition(hang[idx], yul[idx])) }
        val tmp = seatList.sortedWith(compareBy<Seat> { it.h }.thenBy { it.y })
        if (tmp.isEmpty()) {
            val seat = findPositionIfNoFriendExist()
            sitDown(std, seat)
        } else {
            val seat = tmp.getFinalPosition()
            sitDown(std, seat)
        }
    }

    var answer = 0
    (1..size).forEach { idx ->
        val seat = Seat(hang[idx], yul[idx])
        val adjacency = findAdjacency(seat.h, seat.y).toMutableList()
        val memo = adjacency.size
        val friendList = friends[idx]

        adjacency.removeAll(friendList.toList())
        val happySize: Int = when (memo - adjacency.size) {
            0 -> HAPPY.H0.score
            1 -> HAPPY.H1.score
            2 -> HAPPY.H2.score
            3 -> HAPPY.H3.score
            else -> HAPPY.H4.score
        }
        answer += happySize
    }

    println(answer)
}

data class Seat(val h: Int, val y: Int)
sealed class HAPPY(val score: Int) {
    object H0 : HAPPY(score = 0)
    object H1 : HAPPY(1)
    object H2 : HAPPY(10)
    object H3 : HAPPY(100)
    object H4 : HAPPY(1000)
}