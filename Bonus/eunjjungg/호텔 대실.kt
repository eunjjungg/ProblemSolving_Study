// 전략
// 입실 시 퇴실 시간을 넣기
// 입실 시 입실 시간 기준으로 배열의 퇴실 시간이 자기보다 적은 값 중 하나라도 있으면 입실 시키기
class P155651 {
    fun solution(book_time: Array<Array<String>>): Int {
        var emptyRoomSize = 0
        var notEmptyRoomList = mutableListOf<Int>()

        val list: List<Pair<Int, Int>> = book_time.map {
            Pair(it[0].convertToMin(), it[1].convertToMin() + 10)
        }.run { sortedBy { it.first } }

        list.map {
            if (emptyRoomSize > 0) {
                emptyRoomSize--
                notEmptyRoomList.add(it.second)
            } else {
                val beforeSize = notEmptyRoomList.size
                notEmptyRoomList = notEmptyRoomList.filter { exitTime ->
                    exitTime > it.first
                }.toMutableList()
                emptyRoomSize += beforeSize - notEmptyRoomList.size

                if (emptyRoomSize > 0) {
                    emptyRoomSize--
                }
                notEmptyRoomList.add(it.second)
            }
        }

        return notEmptyRoomList.size + emptyRoomSize
    }
}
