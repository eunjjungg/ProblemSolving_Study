import java.util.*

// Strategy
// 일단 맨 초기 작업은 시작해야 함.
// 그리고 작업이 소요되는 시간이 지났다고 가정했을 때 시작 가능한 jobs 중에 소요 시간 가장 적은 것 선택
// 해당 작업이 끝났을 때 시작 가능한 것 중 또 소요 시간 가장 적은 것 선택
// repeat
class Solution {
    fun solution(jobs: Array<IntArray>): Int {
        val startTime = 0
        val duration = 1
        var answer = 0
        var currentTime = 0
//        val hByStartTime = PriorityQueue<IntArray> { o1, o2 ->
//            o1.first().compareTo(o2.first())
//        }
//        val hByDuration = PriorityQueue<IntArray>{ o1, o2 ->
//            o1[1].compareTo(o2[1])
//        }

        val hByStartTime = PriorityQueue<IntArray>(compareBy({it[0]},{it[1]}))
        val hByDuration = PriorityQueue<IntArray>(compareBy({it[1]},{it[0]}))

        hByStartTime.addAll(jobs)
        var nowDoing = hByStartTime.poll()
        currentTime = nowDoing[startTime]

        while (hByStartTime.isNotEmpty() || hByDuration.isNotEmpty()) {
            if (nowDoing[startTime] > currentTime) {
                currentTime = nowDoing[startTime]
            }

            answer += nowDoing[duration] + (currentTime - nowDoing[startTime])
            currentTime += nowDoing[duration]

            while (hByStartTime.peek() != null && hByStartTime.peek()[startTime] <= currentTime) {
                hByDuration.add(hByStartTime.poll())
            }

            nowDoing = if (hByDuration.peek() != null) {
                hByDuration.poll()
            } else if (hByStartTime.peek() != null){
                hByStartTime.poll()
            } else {
                break
            }
        }

        if (nowDoing[startTime] > currentTime) {
            currentTime = nowDoing[startTime]
        }
        answer += nowDoing[duration] + (currentTime - nowDoing[startTime])


        return answer/jobs.size
    }
}

// Review
// 8, 18번만 계속 틀림..
// 찾아보니 startTime이 동일한 경우 duration 기준으로 정렬을 해야했음.
// 이 문제에서 계속 틀리고 있었음.
// 주석 처리한 것에서 그 바로 아래 형태로 변경함.
