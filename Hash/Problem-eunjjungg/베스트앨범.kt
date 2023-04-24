// Strategy
// 1. 장르 뭐부터 실을건지에 대해서는 Hash로 정리
//    genre에 따라 play 추가 후 높은 순으로 genre 값을 배열에 넣기
// 2. 1에서 정렬한 배열 값으로 foreach 돌리기 -> genre가 값이 됨
//    genres, plays 배열에 genre가 일치하는 거에 대해 검색
//    각 genre에 해당하는 곡 수가 1곡이면 그냥 그 인덱스 추가
//    각 genre에 해당하는 곡 수가 1곡이 아니면 max_p, max_i 두고
//    max_p에는 최대 값인 plays 수를, max_i에는 max_p에 해당하는 index를 둠
//    그렇게 최대를 구하고 answer 배열에 값 추가
//    단, max_p와 같다면 max_i는 이전 값으로 유지
//    answer 배열에 들어간 값 제외하고 max_p, max_i 다시 구하기
//    두 번째 최대를 구하고 answer 배열에 값 추가

class P42579 {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        return answer.toIntArray()
    }
}

// Review
