// Strategy
// 옷들 배열에 반복문 돌려서 hash에 각 옷 종류마다 몇 벌 있는지 계산
// 순서 없이 경우의 수 따지는 거기 때문에 각 옷 종류마다 안 입는 경우도 넣어서 + 1 해줌
// 그리고 옷 종류의 값으로 곱하기를 해주면 모든 종류의 옷을 안 입는 경우도 포함됨
// 따라서 최종 값 - 1을 해줌.

class P42578 {
    fun solution(clothes: Array<Array<String>>): Int {
        val hashMap = HashMap<String, Int>()

        clothes.forEach { it ->
            hashMap.put(it[1], hashMap.get(it[1])?.plus(1) ?: 1)
        }

        var answer = 1

        hashMap.forEach { it ->
            answer *= (it.value + 1)
        }

        return answer - 1
    }
}

// Review
