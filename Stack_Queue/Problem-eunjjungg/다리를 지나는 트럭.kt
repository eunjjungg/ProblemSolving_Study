// Strategy
// startQ => truck_weights 다 넣어줌
// goingWeightQ => 다리 위의 차의 무게들
// goingLengthQ => 다리 위의 차의 남은 거리들
// leftWeight : init은 weight 값으로 해주고 다리에 트럭이 올라가면 해당 무게만큼 빼주고 나가면 더해줌
// leftLength : init은 bridge_length로 해주고 다리에 트럭이 올라가면 -1, 나가면 +1 해줌
// startQ의 element가 leftWeight 이하고, leftLength > 0이라면 다리 위에 올림.
// 못 올리면 onGoingQ의 차 한 대 다 보내야 됨. goingWeightQ.peek() 해주고, length + 1 - goingLengthQ.poll() = 간 거리 x
// answer + x 해주면 x초 흐른거 계산 되고 goingLengthQ의 모든 원소에 대해서도 + x 해줌. 
// 그리고 차 올라올수 있는지 확인하고 못하면 또 위에 반복 이렇게 해서 startQ empty, goingQ empty 될 때까지 반복

class P42583 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        return answer
    }
}

// Review
// 리뷰에 대한 설명 (다음 주차에 작성)
