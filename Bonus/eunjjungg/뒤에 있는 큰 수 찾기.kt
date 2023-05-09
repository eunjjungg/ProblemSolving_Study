// Strategy
// 가장 쉬운건 이중포문 -> 그러나 효율 안 좋음
// 배열 맨 뒤부터 stack에 넣고 하나씩 꺼내서 남은 원소에 대해 비교하며
// 자기 자신보다 뒤면서 큰 값이 있다면 answer에 넣어주고 없다면 -1을 넣어줌
class P154539 {
    fun solution(numbers: IntArray): IntArray {
        var answer: IntArray = intArrayOf()
        
//        val stackNum = Stack<Int>()
//        for (i in numbers.size - 1 downTo 0) {
//            stackNum.push(numbers[i])
//        }
//
//        var pivot: Int = stackNum.pop()
//        while (stackNum.isNotEmpty()) {
//            val answerList = stackNum.filter {
//                it > pivot
//            }
//            if (answerList.isEmpty()) {
//                answer.add(-1)
//            } else {
//                answer.add(answerList.last())
//            }
//            pivot = stackNum.pop()
//
//        }
//
//        answer.add(-1)
        
        return answer
    }
}

// Review
// 리뷰에 대한 설명 (다음 주차에 작성)
