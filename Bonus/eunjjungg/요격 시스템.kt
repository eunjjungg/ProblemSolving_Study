import kotlin.math.max
import kotlin.math.min

// Strategy
// 1. [a, b]에서 b 기준으로 내림차순 정렬
// 2. 정렬된 배열에서 n, n+1 데이터에 대해 둘이 겹치는 범위가 있는지 비교
//    [a, b], [c, d]에서 겹치려면 a < d && b > c
// 3-1. 겹치는 범위가 없다면 ans + 1 후 n+1, n+2 데이터에 대해 2번 작업 수행
// 3-2. 겹치는 범위가 있다면 b - a가 더 작은 것 기준으로 삼아서 n, n + 1 데이터 중 작은 것과 n + 2에 대해 2번 작업 수행
//      만약 b - a의 값이 둘이 같다면 b가 더 큰 값인 원소를 기준으로 삼음
// 4. 2에서  n + 1 데이터가 없다면 ans + 1 후 종료

class P181188 {
    fun solution(targets: Array<IntArray>): Int {
        var answer: Int = 0
        var currentInterval: IntArray? = null

        if (targets.size == 1)
            return 1

        targets.sortByDescending { it[1] }

        for (index in 0..targets.size - 2) {
            currentInterval = if (currentInterval == null) {
                isOverlap(targets[index], targets[index + 1])
            } else {
                isOverlap(currentInterval, targets[index + 1])
            }

            if (currentInterval == null) {
                answer++
            }

            if (index == targets.size - 2) {
                answer++
            }

        }

        return answer
    }

    private fun isOverlap(first: IntArray, second: IntArray) : IntArray? {
        if (first[0] < second[1] && first[1] > second[0]) {
            val overlapStart = max(first[0], second[0])
            val overlapEnd = min(first[1], second[1])
            return intArrayOf(overlapStart, overlapEnd)
        }

        return null
    }
}

// Review
// 리뷰에 대한 설명 (풀이 후 작성)
// 전략대로 풀었는데 계속 틀려서 이유를 알아보니 isOverlap()의 사용이 잘못되었음.
// 나는 겹치는 부분이 있을 때 좀 더 좁은 범위이면서 좀 더 한쪽으로 편향된 범위의 개구간을 선택해야만 한다고
// 생각했음. 근데 겹치는 부분이 있을 때 두 좌표를 비교하면서 시작점의 큰 값, 종료점의 작은 값을 토대로
// 새로운 좌표를 만들어서 내려주면 되는 일이었음... 
