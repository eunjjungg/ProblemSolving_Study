// Strategy
// 1. [a, b]에서 b 기준으로 내림차순 정렬
// 2. 정렬된 배열에서 n, n+1 데이터에 대해 둘이 겹치는 범위가 있는지 비교
// 3-1. 겹치는 범위가 없다면 ans + 1 후 n+1, n+2 데이터에 대해 2번 작업 수행
// 3-2. 겹치는 범위가 있다면 b - a가 더 작은 것 기준으로 삼아서 n, n + 1 데이터 중 작은 것과 n + 2에 대해 2번 작업 수행 
//      만약 b - a의 값이 둘이 같다면 b가 더 큰 값인 원소를 기준으로 삼음
// 4. 2에서  n + 1 데이터가 없다면 ans + 1 후 종료

class P181188 {
    fun solution(targets: Array<IntArray>): Int {
        var answer: Int = 0
        return answer
    }
}

// Review
// 리뷰에 대한 설명 (풀이 후 작성)
