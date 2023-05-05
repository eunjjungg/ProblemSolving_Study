import java.util.LinkedList
import java.util.Queue
import kotlin.math.ceil

// Strategy
// 100 - progress[i] = x
// ceil(x / speed[i]) = n
// n을 queue에 추가
// queue의 element를 pivot으로 정하고 pop() 해준 뒤
// 다음 element가 pivot 이하일 때 pop() 해주고 count++를 해서
// ans 배열에 넣어줌. 이후 count 초기화

class P42586 {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val daysQueue: Queue<Int> = LinkedList()
        val answer: Queue<Int> = LinkedList()
        progresses.forEachIndexed { index, progress ->
            val day = ceil((100 - progress).toFloat() / speeds[index]).toInt()
            daysQueue.offer(day)
        }

        var pivot = daysQueue.element()
        var count = 0
        while (daysQueue.isNotEmpty()) {
            if (pivot >= daysQueue.element()) {
                count++
                daysQueue.poll()
            } else {
                answer.offer(count)
                count = 0
                pivot = daysQueue.element()
            }
        }

        if (count != 0) {
            answer.offer(count)
        }

        return answer.toIntArray()
    }
}

// Review
// 풀이대로 풀었음. 근데 이제 다른 사람 풀이 보니까 map 함수를 정말 잘 썼음.
// 난 map 함수 제대로 못 쓰는 것 같음.. 공부해야지...
