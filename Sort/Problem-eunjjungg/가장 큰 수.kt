class P42746 {
    val o1IsLargerThanO2 = 1
    val o1IsSmallerThanO2 = -1
    val customComparator = Comparator { o1String: String, o2String: String ->


        if (o1String.length != o2String.length) {
            ("$o1String$o2String".toLong() - "$o2String$o1String".toLong()).toInt()

            // if (o1String.length < o2String.length) {
            //     if (o2String[o1String.length] > o1String.first()) {
            //         o1IsSmallerThanO2
            //     } else if (o2String[o1String.length] < o1String.first()) {
            //         o1IsLargerThanO2
            //     } else {
            //         (o1String + o2String).compareTo(o2String + o1String)
            //     }
            // } else {
            //     if (o1String[o2String.length] > o1String.first()) {
            //         o1IsLargerThanO2
            //     } else if (o1String[o2String.length] < o1String.first()) {
            //         o1IsSmallerThanO2
            //     } else {
            //         (o1String + o2String).compareTo(o2String + o1String)
            //     }
            // }

        } else {
            (o1String.toLong() - o2String.toLong()).toInt()
        }
    }

    fun solution(numbers: IntArray): String {
        var answer = ""

        val stringArr = mutableListOf<String>()
        numbers.forEach {
            stringArr.add(it.toString())
        }

        val ans = stringArr.sortedWith(customComparator).reversed()

        if (ans.first() == "0") {
            return "0"
        }

        ans.forEach {
            answer += it
        }

        return answer
    }
}

// Review
// 이상한 짓 하고 있었음. 
// Comparator 내부에서 분기 처리해줘서 케이스 상세하는게 왜때문인지 모르겠는데 그게 더 연산이 덜 드는 줄 알았음..
// 정답 다 와놓고 이상한 짓함..
