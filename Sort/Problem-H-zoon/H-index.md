문제링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42746

```kotlin

//strategy
// h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다
// 이게 대체 뭔소립니까?
// 다른분 설명보고 겨우 이해했습니다.
// 현재 논문의 인용 횟수와 논문의 순위 비교하여. 인용 횟수가 순위와 같거나 작아지는 경우가 정답.
// 결국 순위대로 정렬하여 순회한 후 인덱스와 인용 횟수가 같거나 작은 부분이 h-index

class Solution {
    fun solution(citations: IntArray): Int {
    val citationsSort = citations.sortedArrayDescending()
    for (i in citationsSort.indices) {
        if (citationsSort[i] <= i) {
            return i
        }
    }
    return citationsSort.size
    }
}

// review
// 흠.. 푼 시간보다 이해할려 한 시간이 더 길었음..
```
