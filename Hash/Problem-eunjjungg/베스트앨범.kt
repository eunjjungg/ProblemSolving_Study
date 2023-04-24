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
        var hash = HashMap<String, Int>()

        genres.forEachIndexed { index, genre ->
            hash.put(genre, hash.get(genre)?.plus(plays[index]) ?: plays[index])
        }
        val sortedGenres = hash.toList().sortedByDescending { it.second } // hash가 많이 들은 장르 순으로 정렬됨

        sortedGenres.forEach { genreMap ->
            var max_i = -1
            var max_p = -1

            genres.forEachIndexed { index, genre ->
                if (genre == genreMap.first && index != max_i && max_p < plays[index]) {
                    max_i = index
                    max_p = plays[index]
                }
            }
            answer.add(max_i)

            max_p = -1
            genres.forEachIndexed { index, genre ->
                if (genre == genreMap.first && index != answer.last() && max_p <= plays[index]) {
                    if (max_p == plays[index] && index < max_i) {
                        max_i = index
                    } else if (max_p != plays[index]) {
                        max_i = index
                    }
                    max_p = plays[index]
                }
            }
            if (max_i != answer.last()) {
                answer.add(max_i)
            }
        }


        return answer.toIntArray()
    }
}

// Review
// 코드는 복잡하지만 맞게 푼 것 같은데 계속 오류가 났다.
// 왜 그랬냐면 map, hashmap을 그냥 sortedMap 메소드 사용을 하면
// key 값을 기준으로 정렬이 됨. 하지만 난 value 기준으로 정렬을 해야 하므로
// list로 변환 후 sortedByDescending { it.second }로 해주어야 함.
