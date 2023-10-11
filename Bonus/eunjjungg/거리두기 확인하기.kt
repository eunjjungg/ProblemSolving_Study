class Solution {
    val std = 'P'
    val ptt = 'X'
    val desk = 'O'
    
    fun solution(places: Array<Array<String>>): IntArray {
        val answer = mutableListOf<Int>()
        places.map { place ->
            if(isValidPlace(place)) {
                answer.add(1)
            } else {
                answer.add(0)
            }
        }
        
        return answer.toIntArray()

    }
    
    fun isValidPlace(place: Array<String>): Boolean {
        var idx = 0
        var rowIdx = 0
        place.map { row ->
            idx = 0
            row.forEach { char -> 
                if(char == std) {
                    if(!isValidSeat(idx, rowIdx, row, place)) {
                        return false
                    }
                }
                idx++
            }
            rowIdx++
        }
        
        return true
    }
    
    private fun isValidSeat(
        idx: Int, 
        rowIdx: Int,
        row: String, 
        place: Array<String>,
    ): Boolean {
        // 좌측 확인
        if(idx - 1 >= 0 && row[idx - 1] == std) { return false }
        // 우측 확인
        if(idx + 1 <= 4 && row[idx + 1] == std) { return false }
        // 위 확인
        if(rowIdx - 1 >= 0 && place[rowIdx - 1][idx] == std) { return false }
        // 아래 확인
        if(rowIdx + 1 <= 4 && place[rowIdx + 1][idx] == std) { return false }
        // 가로줄 -2 확인
        if(idx - 2 >= 0 && row[idx - 2] == std && row[idx - 1] != ptt) { return false }
        // 가로줄 +2 확인
        if(idx + 2 <= 4 && row[idx + 2] == std && row[idx + 1] != ptt) { return false }
        // 세로줄 -2 확인
        if(rowIdx - 2 >= 0 && place[rowIdx - 2][idx] == std && place[rowIdx - 1][idx] != ptt) { return false }
        // 세로줄 +2 확인
        if(rowIdx + 2 <= 4 && place[rowIdx + 2][idx] == std && place[rowIdx + 1][idx] != ptt) { return false }
        // 왼쪽 상 대각선 확인 
        if(rowIdx - 1 >= 0 && idx - 1 >= 0 && place[rowIdx - 1][idx - 1] == std && 
                !(row[idx - 1] == ptt && place[rowIdx - 1][idx] == ptt)) { return false }
        // 왼쪽 하
        if(rowIdx + 1 <= 4 && idx - 1 >= 0 && place[rowIdx + 1][idx - 1] == std && 
                !(row[idx - 1] == ptt && place[rowIdx + 1][idx] == ptt)) { return false }
        // 오른쪽 상
        if(rowIdx - 1 >= 0 && idx + 1 <= 4 && place[rowIdx - 1][idx + 1] == std && 
                !(row[idx + 1] == ptt && place[rowIdx - 1][idx] == ptt)) { return false }
        // 오른쪽 하
        if(rowIdx + 1 <= 4 && idx + 1 <= 4 && place[rowIdx + 1][idx + 1] == std && 
                !(row[idx + 1] == ptt && place[rowIdx + 1][idx] == ptt)) { return false }
        
        return true
    }
}
