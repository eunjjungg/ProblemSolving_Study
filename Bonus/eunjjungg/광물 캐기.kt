package com.ej.problemsolvingstudy.Bonus

import kotlin.math.ceil
import kotlin.math.min

class P172927 {
    fun solution(picks: IntArray, minerals: Array<String>): Int {
        var answer: Int = 0

        val diaPick: Pick = Pick.Diamond.apply { remaining = picks[0] }
        val ironPick = Pick.Iron.apply { remaining = picks[1] }
        val stonePick = Pick.Stone.apply { remaining = picks[2] }
        val allPick = diaPick.remaining + ironPick.remaining + stonePick.remaining

        // 내림차순으로 정렬한 피로도 계수
        val tiredness: List<Pair<Int, List<String>>> = minerals
            .toList()
            .chunked(5)
            .subList(0, min(allPick, ceil(minerals.size / 5.0).toInt()))
            .map {
                var sum = 0
                it.map {
                    sum += when (it) {
                        "diamond" -> 25
                        "iron" -> 5
                        else -> 1
                    }
                }
                Pair(sum, it)
            }.run {
                sortedByDescending { it.first }
            }

        tiredness.map {
            val pick: Pick = if (diaPick.remaining > 0) {
                diaPick
            } else if (ironPick.remaining > 0) {
                ironPick
            } else if (stonePick.remaining > 0) {
                stonePick
            } else {
                return answer
            }

            pick.remaining--

            it.second.forEach {
                answer += when (it) {
                    "diamond" -> pick.dia
                    "iron" -> pick.iron
                    else -> pick.stone
                }
            }
        }

        return answer
    }

    sealed class Pick(var remaining: Int, val dia: Int, val iron: Int, val stone: Int) {
        object Diamond : Pick(0, 1, 1, 1)
        object Iron : Pick(0, 5, 1, 1)
        object Stone : Pick(0, 25, 5, 1)
    }

}

// 광물: 다이어 25, 철 5, 돌 1
// 곡괭이: 다25, 철5, 돌1
// 광물 / 다곡 => 다이아 1, 철 1/5, 돌 1/25 => 피로도 1, 1, 1
// 광물 / 철곡 => 다이아 5, 철 1, 돌 1/5 => 피로도 5, 1, 1
// 광물 / 돌곡 => 다이아 25, 철 5, 돌 1 => 피로도 25, 5, 1
// 광물 5개씩 줄세우자. -> [a, b, c, d, ...]

fun main() {
    val t = (1..13).chunked(5).subList(0, 2).map {
        var sum = 0
        it.forEach { item ->
            sum += item
        }
        Pair(sum, it)
    }.run { sortedByDescending { it.first } }
    println(t.toString())
}
