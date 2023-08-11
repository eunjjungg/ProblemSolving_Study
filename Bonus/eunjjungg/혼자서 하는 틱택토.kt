// strategy
// 1. 배열을 먼저 풀어서 flat하게 생성 -> str
// 2. str의 0~8까지 map 후 각 자리가 O, X, .이냐에 따라 o,x,blankList에 index 삽입
// 3. 불가능한 경우의 수 체크 
// 3-1. 0 <= oSize - xSize <= 1 return 0
// 3-2. oWin && oSize != xSize + 1 return 0
// 3-3. xWin && oSize != xSize return 0
// 3-4. 위에 속하지 않는다면 정상 틱택토이므로 return 1
class P160585 {
    fun solution(board: Array<String>): Int {
        var str = ""
        board.map {
            str = "$str$it"
        }
        val oList = mutableListOf<Int>()
        val xList = mutableListOf<Int>()
        val blankList = mutableListOf<Int>()

        str.forEachIndexed { index, char ->
            when (char) {
                '.' -> blankList.add(index)
                'O' -> oList.add(index)
                'X' -> xList.add(index)
            }
        }

        val diff = oList.size - xList.size

        if (diff !in 0..1) {
            return 0
        }

        val oIsWin = oList.isWin()
        val xIsWin = xList.isWin()

        if (oIsWin && diff != 1) {
            return 0
        }
        if (xIsWin && diff != 0) {
            return 0
        }

        return 1
    }

    fun List<Int>.isWin(): Boolean {
        return containsAll(listOf(0, 4, 8)) ||
                containsAll(listOf(0, 1, 2)) ||
                containsAll(listOf(3, 4, 5)) ||
                containsAll(listOf(6, 7, 8)) ||
                containsAll(listOf(2, 4, 6)) ||
                containsAll(listOf(0, 3, 6)) ||
                containsAll(listOf(1, 4, 7)) ||
                containsAll(listOf(2, 5, 8))
    }
}
