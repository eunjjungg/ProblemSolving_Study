[완료](https://dongdong216.tistory.com/31)

``` kotlin
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class L3sum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val map = mutableMapOf<Int, Pair<Int, Int>>()
        nums.map {
            val absNum = abs(it)
            val (positiveCount, negativeCount) = map.getOrDefault(absNum, Pair(0, 0))

            if (it > 0) {
                map[absNum] = Pair(positiveCount + 1, negativeCount)
            } else if (it < 0) {
                map[absNum] = Pair(positiveCount, negativeCount + 1)
            } else {
                map[absNum] = Pair(positiveCount + 1, negativeCount)
            }
        }

        val answer = mutableSetOf<List<Int>>()
        map.toSortedMap()

        if ((map[0]?.first ?: 0) > 0) {
            val tmp = map.filter {
                it.value.first > 0 && it.value.second > 0
            }
            tmp.map {
                answer.add(listOf(it.key * -1, 0, it.key))
            }

            if ((map[0]?.first ?: 0) >= 3) {
                answer.add(listOf(0, 0, 0))
            }
            map.remove(0)
        }

        val minus = map.filter { it.value.second > 0 }
        val plus = map.filter { it.value.first > 0 }

        minus.forEach { (key, _) ->
            if (key % 2 == 0 && (map[key / 2]?.first ?: 0) >= 2) {
                answer.add(listOf(key * -1, key / 2, key / 2))
            }

            plus.map { (plusKey, _) ->
                if (plusKey >= key) {
                    return@map
                }

                if (plusKey * 2 != key && (map[key - plusKey]?.first ?: 0) > 0) {
                    answer.add(listOf(key * -1, min(plusKey, key - plusKey), max(plusKey, key - plusKey)))
                }
            }
        }

        plus.forEach { (key, _) ->
            if (key % 2 == 0 && (map[key / 2]?.second ?: 0) >= 2) {
                answer.add(listOf(key, -key / 2, -key / 2))
            }



            minus.map { (minusKey, _) ->
                if (minusKey >= key) {
                    return@map
                }

                if (minusKey * 2 != key && (map[key - minusKey]?.second ?: 0) > 0) {
                    answer.add(listOf(key, min(-minusKey, -key + minusKey), max(-minusKey, -key + minusKey)))
                }
            }
        }

        return answer.toList()
    }
}


```
