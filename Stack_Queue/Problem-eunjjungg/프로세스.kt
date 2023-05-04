import java.util.LinkedList
import java.util.Queue

// Strategy
// 우선순위 기준으로 정렬
// 우선순위가 4, 3, 2, 1, 0 순으로 있을 때
// priority가 4인 것의 가장 뒤에 있는 거 찾고 해당 인덱스보다 앞에 있는 priority들 다 배열뒤로 붙임.
// 그리고 priority가 4인 것의 Location들 pop해줌. count도 pop된 개수만큼 ++를 해줌.
// 3, 2, 1, 0에 대해서도 반복.
// 이 과정 수행하면서 locations queue에서 pop 된게 문제에서 주어진 Location일 때 count의 개수 Return

class P42587 {
    fun solution(priorities: IntArray, location: Int): Int {
        val sortedPriorityQueue: Queue<Int> = LinkedList<Int>()
        val indexQueue: Queue<Int> = LinkedList<Int>((priorities.indices).toList())
        val pQueue: Queue<Int> = LinkedList<Int>()
        pQueue.addAll(priorities.toList())

        priorities.sortDescending()
        priorities.forEach { item ->
            if (item != sortedPriorityQueue.lastOrNull()) {
                sortedPriorityQueue.add(item)
            }
        }

        var processIndexCount = 0

        while (true) {
            val top: Int = sortedPriorityQueue.remove()
            val topLastIndex = pQueue.findLastIndex(top)

            for (i in pQueue.indices) {
                val priority = pQueue.element()

                if (priority == top) {
                    pQueue.poll()
                    processIndexCount++
                    if (indexQueue.poll() == location) {
                        return processIndexCount
                    }
                } else if (topLastIndex >= i) {
                    pQueue.offer(pQueue.poll())
                    indexQueue.offer(indexQueue.poll())
                }
            }
        }
    }

    private fun Queue<Int>.findLastIndex(item: Int): Int {
        val lList = this as LinkedList<Int>
        for (i in lList.size - 1 downTo 0) {
            if (lList[i] == item) {
                return i
            }
        }
        return -1
    }
}

// Review
