// greedy
// 첫 배달까지는 이해 감
// 두 번째 배달 -> dCount, pCount에는 배달 빅업 한 횟수보다 더 적어서 담거에서 보충해야될 배달, 회수 개수가 들어있음.
// 근데 여기서 배달할 것만 있다면? 배달만 완료하고 pCount는 더 늘어남. 근데 이전에 갔다면 빼줘야되지 않나? -> 안 빼줘도 됨.
// 왜냐하면 여기를 들린다는 건 배달, 수거 둘 중 하나의 개수가 첫 번째 배달 끝나고 이월된 각 개수보다 많다는 뜻. 그거 아니라면 그냥 빼주기만 함.
// 미쳤다 ㅠ
class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer = 0L
        var dCount = 0
        var pCount = 0
        for (i in n - 1 downTo 0) {
            if (deliveries[i] != 0 || pickups[i] != 0) {
                var visits = 0
                while (dCount < deliveries[i] || pCount < pickups[i]) {
                    visits++
                    dCount += cap
                    pCount += cap
                }
                dCount -= deliveries[i]
                pCount -= pickups[i]
                answer += (i + 1) * visits * 2
            }
        }
        return answer
    }
}
