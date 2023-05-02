import kotlin.math.*

// Strategy
// 제1사분면에서 x, y축 제외하고 포함되는 점 + x축 포함
// r1^2 ≤ x^2 + y^2 ≤ r2^2 되는 양수 x, y를 찾으면 됨
// 단 0 ≤ x ≤ r2, 0 ≤ y ≤ r2
// 구하는 방법 x값 기준으로 계산.
// tempValue = 0
// [0, r2]까지 x값 기준으로 반복문 돌림. (폐구간)
// start = r1 ^ 2 - x ^ 2 (만약 0보다 작으면 0)
// end = r2 ^ 2 - x ^ 2
// [start, end]를 만족하는 y^2의 모든 y 값 찾을 때마다 tempValue++
// 총 n개인데 x, y축 둘 다 포함되어 있으므로 r2 - r1 + 1
// ans = (tempValue - r2 + r1 - 1) * 4

class P181187 {
    fun solution(r1: Int, r2: Int): Long {
        var countQ1: Long = 0
        val rr1: Long = r1.toDouble().pow(2).toLong()
        val rr2: Long = r2.toDouble().pow(2).toLong()

        for (x in 0..r2) {
            val xx: Long = x.toDouble().pow(2).toLong()
            val start: Long = if (rr1 - xx > 0) {
                rr1 - xx
            } else {
                0
            }
            val end: Long = rr2 - xx

            countQ1 += (sqrt(end.toDouble()).toLong() - ceil(sqrt(start.toDouble())).toLong() + 1)
        }

        return (countQ1 - r2 + r1 - 1) * 4
    }
}

// Review
// 리뷰에 대한 설명
// rr1 <= xx + yy <= rr2
// rr1 - xx <= yy <= rr2 - xx
// sqrt(rr1 - xx) <= y <= sqrt(rr2 - xx)
// 타입을 조심해서 사용하자
