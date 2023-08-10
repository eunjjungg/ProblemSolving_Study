// 전략
// 1. [name, startTime to min, playTime]으로 input 변환
// 2. startTime 기준으로 ascending order로 sort -> sortedPlan Queue
// 3. sortedPlan에서 원소 하나씩 꺼내서 onGoing에 넣어줌.
// onGoing' st+pt, sortedPlan의 element의 st 비교
// 3-1. st + pt > element' st이면 ongoing' pt = pt - element' st + st로 바꿔주고 이를 remaining stack에 넣어주기, break
// 3-2. st + pt < element' st이면 remainTime 구해주고 그 remainTime > 0인 동안 remaining Stack에서 하나씩 수행해주기
// 3-3. st + pt == element' st이면 3번 반복문 break.
class P176962 {
    fun solution(plans: Array<Array<String>>): Array<String> {
        val answer = mutableListOf<String>()

        val planQueue = plans.map { Plan(it[0], it[1].toStartTime(), it[2].toPlayTime()) }
        val sortedPlans: Queue<Plan> = LinkedList(planQueue.sortedBy { it.st })
        val remaining = Stack<Plan>()

        while (true) {
            val onGoing: Plan = sortedPlans.poll() ?: break
            if (sortedPlans.peek() == null) {
                answer.add(onGoing.name)
                break
            }
            val element: Plan = sortedPlans.element()

            if (onGoing.pt + onGoing.st == element.st) {
                answer.add(onGoing.name)
            } else if (onGoing.pt + onGoing.st > element.st) {
                onGoing.apply { remaining.add(Plan(name, st, pt + st - element.st)) }
            } else if (onGoing.pt + onGoing.st < element.st) {
                answer.add(onGoing.name)
                var remainingTime = element.st - onGoing.pt - onGoing.st

                while (remainingTime > 0) {
                    if (remaining.isEmpty()) {
                        remainingTime = 0
                        break
                    }

                    if (remaining.peek().pt <= remainingTime) {
                        answer.add(remaining.peek().name)
                        remainingTime -= remaining.pop().pt
                    } else {
                        val top = remaining.pop()
                        remaining.add(Plan(top.name, top.st, top.pt - remainingTime))
                        remainingTime = 0
                    }
                }
            }
        }

        while (remaining.isNotEmpty()) {
            answer.add(remaining.pop().name)
        }

        return answer.toTypedArray()
    }

    private fun String.toStartTime(): Int {
        val list = this.split(":")
        return list[0].toInt() * 60 + list[1].toInt()
    }

    private fun String.toPlayTime(): Int {
        return this.toInt()
    }
}

data class Plan(
    val name: String,
    val st: Int,
    var pt: Int
)

fun main() {
    fun String.convertStartTime(): Int {
        val list = this.split(":")
        return list[0].toInt() * 60 + list[1].toInt()
    }

    fun String.convertPlayTime(): Int {
        return this.toInt()
    }

    val b = listOf("b", "11:40", "30")
    val c = listOf("c", "10:40", "30")
    val d = listOf("d", "9:40", "30")
    val a = LinkedList<Plan>()
    a.addAll(
        listOf(
            Plan(b[0], b[1].convertStartTime(), b[2].convertPlayTime()),
            Plan(c[0], c[1].convertStartTime(), c[2].convertPlayTime()),
            Plan(d[0], d[1].convertStartTime(), d[2].convertPlayTime()),
        )
    )
    val sorted = a.sortedBy { it.st }
    println(sorted.toString())
}
