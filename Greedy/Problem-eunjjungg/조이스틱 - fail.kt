// 2, 5, 9, 13, 18, 23, 25 fail
// greedy algorithm으로 접근하면 안 되는 문제
class P42860 {
    fun solution(name: String): Int {
        var answer = 0
        var current = 0
        val q = mutableListOf<Char>()
        val goal: List<Char> = name.map {
            q.add('A')
            it
        }

        if (q.first() != goal.first()) {
            answer += goal.first() - q.first()
            q[0] = goal.first()
        }

        fun findNextPosition(): Int {
            for (offset in 1 until goal.size) {
                val right = if (offset + current > goal.size - 1) {
                    (offset + current) % goal.size
                } else {
                    offset + current
                }
                val left = if (current - offset < 0) {
                    q.size + current - offset
                } else {
                    current - offset
                }

                if (goal[right] != q[right] && goal[left] != q[left]) {
                    for (i in offset + 1 until goal.size) {
                        val tmpR = if (i + current > goal.size - 1) {
                            (i + current) % goal.size
                        } else {
                            i + current
                        }
                        val tmpL = if (current - i < 0) {
                            q.size + current - i
                        } else {
                            current - i
                        }

                        if (goal[tmpL] != 'A' && goal[tmpR] == 'A') {
                            return left
                        }
                        if (goal[tmpR] != 'A' && goal[tmpL] == 'A') {
                            return right
                        }
                    }
                    return right
                }

                if (goal[right] != q[right]) {
                    return right
                }

                if (goal[left] != q[left]) {
                    return left
                }
            }

            return  -1
        }

        fun getCharOffset(goal: Char) = min(goal - 'A', 'Z' - goal + 1)


        while (true) {
            val next = findNextPosition()
            if (next == -1) {
                break
            }

            val range = if (next > current) {
                min(next - current, current + q.size - next)
            } else {
                min(current - next, next + q.size - current)
            }
            println("range: $range // next: $next // current: $current")
            answer += range
            answer += getCharOffset(goal[next])
            q[next] = goal[next]
            current = next
        }

        return answer
    }
}
