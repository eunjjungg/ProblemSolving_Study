// Strategy
// 각 점수별로 해시 만들기
// a ~ b / c ~ d
// c번 이상 인용된
// 0~c-1까지 인용된 개수 ->
// 0->0, 1->1 3->2
// key : key 번 인용
// value : key 번 인용된 아이템의 총 개수
class P42747 {
    fun solution(citations: IntArray): Int {
        var answer = 0

        // 과학자가 발표한 논문 n편
        val arr = citations.sortedArrayDescending()
        val n = arr.size
        for (index in 1..arr.size) {
            if (index > arr[index - 1]) {
                return index - 1
            } else if (index == arr.size) {
                return arr.size
            }
        }

        return answer
    }
}

// Review
// 문제 자체가 설명이 잘못 됐음..
