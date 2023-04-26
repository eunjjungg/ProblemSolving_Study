class P12931 {
    fun solution(n: Int): Int {
        val nToString = n.toString()
        var answer = 0
        for (i in nToString) {
            answer += i.digitToInt()
        }

        return answer
    }
}
