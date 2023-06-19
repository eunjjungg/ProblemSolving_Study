import java.util.*

// Strategy
// 딱히.. 전략이랄게 없습니다..
// 1. 오퍼레이터 파싱
// 2. minHeap, maxHeap 두 개 생성 후 
// 3. D -1의 경우 minHeap에서 poll하고 그 리턴값으로 maxHeap에서도 제거
// 4. D 1의 경우 반대로 maxHeap에서 poll하고 그 리턴값으로 minHeap에서도 제거
// 5. operation에 대하여 반복 다 끝나면 minHeap 비어있는지 체크 후 Min, maxHeap에서 각각 poll()
// (만약 원소가 하나라면 그것이 최대 최소이기 때문에 동일하게 poll()해주면 됨.) 

class P42628 {
    fun solution(operations: Array<String>): IntArray {
        val minHeap: PriorityQueue<Int> = PriorityQueue()
        val maxHeap: PriorityQueue<Int> = PriorityQueue { o1, o2 -> o2.compareTo(o1) }

        operations.forEach { operation ->
            if (operation.startsWith(Operator.INSERT)) {
                minHeap.add(operation.removePrefix("I ").toInt())
            } else if (operation == Operator.DELETE_MAX) {
                val max = maxHeap.poll()
                if (max != null) {
                    minHeap.remove(max)
                }
            } else {
                val min = minHeap.poll()
                if (min != null) {
                    maxHeap.remove(min)
                }
            }
        }

        if (minHeap.isNotEmpty()) {
            val min = minHeap.poll()
            val max = maxHeap.poll()
            return intArrayOf(min, max)
        }

        return intArrayOf(0, 0)
    }

    object Operator {
        const val INSERT = "I"
        const val DELETE_MAX = "D 1"
        const val DELETE_MIN = "D -1"
    }
}

// Review
// 동일하게 풀었는데 찾아보니 removePrefix 대신 " "로 파싱을 해주는 방법도 있었습니다. 
