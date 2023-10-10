class Solution {
    
    // 이모티콘 플러스 최대한 늘리려면? 
    // -> 할인율을 최대한 많은 사람들거 맞추면서 
    // -> 이모티콘 구매 비용의 합이 최대한 많은 사람들의 기준 이상이어야 함.
    // 10, 20, 30, 40 중 이모티콘 플러스 가입자가 같은게 두 개 이상이라면?
    // 더 많은 사람들한테 비용을 받아야 하므로 낮은 할인율 채택
    
    // 일단 다 10퍼센트 할인으로 시작해서 이모플 가입자, 판매액 정리
    // 하나씩 10퍼씩 올려가며 보는데 이모플 가입자가 적어지거나 이모플 그대론데 판매액 적어지면 빠꾸
    // 마지막까지 돌리고 최종 이모플 가입자, 판매액 배열에 담아서 제출
    // 차이나는 이모플 가입자, 판매액 구하는 법? (10 -> 20퍼일때)
    // 이모플 가입자: 11~
    // 5, 9, 10, 11, 15, 20, 21, 23
    // 10퍼일때 10 이하면 다 삼 - 5, 9, 10
    // 20퍼일 때 20 이하면 다 삼 - 5, 9, 10, 11, 15, 20
    // 10으로 내렸을 때 11~19 
    
    // 전혀 그리디를 쓰면 안 되는 문제인데 왜 나는 그리디를 써야 된다고 생각했을까,,
    // 그리디 조건 생각하고 풀어야 됨. 
    var people = 0
    var sell = 0
    var sum = 0
    
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {        
        
        fun checkUser(emotPercentage: List<Int>) {
            var tmpSell = 0
            var tmpPeople = 0
            var indSum = 0

            users.map { user ->
                indSum = 0
                emotPercentage.forEachIndexed { index, percentage ->
                    if (user[0] <= emotPercentage[index]) {
                        indSum += (emoticons[index] * (100 - percentage) / 100f).toInt()
                    }
                }
                if (indSum >= user[1]) { tmpPeople++ } else { tmpSell += indSum }
            }
        
            if(tmpPeople > people || (tmpPeople == people && tmpSell > sell)) {
                people = tmpPeople
                sell = tmpSell
            }
        }
        
        val discount = listOf(10, 20, 30, 40)
        
        fun recursive(depth: Int, per: List<Int>) {
            if(depth == 0) {
                checkUser(per)
                return
            } else {
                discount.forEach {
                    val percentage = per.toMutableList()
                    percentage.add(it)
                    recursive(depth - 1, percentage)
                }
            }
        }
        
        recursive(emoticons.size, listOf())
        
        return intArrayOf(people, sell)
    }
}
