package programmers

// test case 16, 17 시간 초과 
class P150369 {
    private val list = mutableListOf<Data>()
    private var isNothingToDelivery = false
    private var isNothingToPickUp = false

    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0

        deliveries.mapIndexed { index: Int, i: Int ->
            if (i != 0 || pickups[index] != 0) {
                list.add(Data(i, pickups[index], index))
            }
        }
        list.sortByDescending { it.index }

        while (list.isNotEmpty()) {
            if (list.first().delivery > 0 || list.first().pickUp > 0) {
                answer += (list.first().index + 1) * 2
                list.delivery(cap)
                list.pickUp(cap)
            } else {
                list.removeAt(0)
            }
        }

        return answer
    }

    private fun MutableList<Data>.delivery(capacity: Int) {
        if (isNothingToDelivery) {
            return
        }

        var remaining = capacity
        val removeIndexList = mutableListOf<Int>()
        this.mapIndexed { index: Int, data: Data ->
            if (remaining == 0) {
                return
            }

            if (data.delivery > 0) {
                if (data.delivery > remaining) {
                    data.delivery -= remaining
                    remaining = 0
                } else {
                    remaining -= data.delivery
                    data.delivery = 0
                    if (data.pickUp == 0) {
                        removeIndexList.add(index)
                    }
                }
            }
        }

        var rmOffset = 0
        removeIndexList.forEach {
            this.removeAt(it + rmOffset--)
        }

        if (capacity == remaining) {
            isNothingToDelivery = true
        }
    }

    private fun MutableList<Data>.pickUp(capacity: Int) {
        if (isNothingToPickUp) {
            return
        }

        var remaining = capacity
        val removeIndexList = mutableListOf<Int>()

        this.mapIndexed { index: Int, data: Data ->
            if (remaining == 0) {
                return
            }

            if (data.pickUp > 0) {
                if (data.pickUp > remaining) {
                    data.pickUp -= remaining
                    remaining = 0
                } else {
                    remaining -= data.pickUp
                    data.pickUp = 0
                    if (data.delivery == 0) {
                        removeIndexList.add(index)
                    }
                }
            }
        }

        var rmOffset = 0
        removeIndexList.forEach {
            this.removeAt(it + rmOffset--)
        }

        if (capacity == remaining) {
            isNothingToPickUp = true
        }
    }

    data class Data(
        var delivery: Int,
        var pickUp: Int,
        val index: Int
    )
}
