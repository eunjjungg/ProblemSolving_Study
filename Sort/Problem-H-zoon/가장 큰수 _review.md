사용한 반례
```kotlin
    val numbers1 = intArrayOf(6,10,2)
    val numbers2 = intArrayOf(3,30,34,5,9)
    val numbers3 = intArrayOf(9,998)
    val numbers4 = intArrayOf(0,0,0,0)
    val numbers5 = intArrayOf(12,1213)
    val numbers6 = intArrayOf(979, 97, 978, 81, 818, 817) //9799797881881817
    val numbers7 = intArrayOf(67, 676, 677)
    val numbers8 = intArrayOf(0,0,0,1000)
    val numbers9 = intArrayOf(0,0,1000,0)
    val numbers10 = intArrayOf(1000,0,0,0)
    val numbers11 = intArrayOf(1,0,0,0)
    val numbers12 = intArrayOf(1,11,110,1110)
    val numbers13 = intArrayOf(1, 10, 100, 1000, 818, 81, 898, 89, 0, 0)
```
여기서 문제 해결에 핵심 반례는 4,12,13 이였던 것 같다.
4번은 0에 대한 처리 반례로 만약 0000을 반환한다면 실패 (아마 11번 케이스가 이 부분인듯)
12, 13번은 순서에 관한 부분으로 처음 내가 접근한 방식인 숫자의 첫 부분만 비교하는 방식이면 답이 틀리게 나옴
예를 들면 13번은 
8988981881100100011000 로 나오지만  맞는 답은

8989881881110100100000 이 나와야 한다 

첫 시도한 코드는 다음과 같음.
```kotlin

fun solution(numbers: IntArray): String {
    var answer = ""
    numbers.sortDescending()
    val arr = arrayOfNulls<IntArray>(numbers.size)

    for((index, item) in numbers.withIndex()){
        val a = item.toString()
        arr[index] = intArrayOf(index, a[0].digitToInt())
    }

    arr.sortWith(kotlin.Comparator { o1, o2 ->
        o2!![1] - o1!![1]
    })

    var i = 0

    while(i < arr.size){
        if (i<arr.size-1 && arr[i]!![1] != 0) {
            if (arr[i]!![1] != arr[i + 1]!![1]){
                answer += numbers[arr[i]!![0]]
            } else {
                val a = numbers[arr[i]!![0]].toString()
                val b = numbers[arr[i + 1]!![0]].toString()
                if ((a + b).toInt() > (b + a).toInt()) {
                    answer += numbers[arr[i]!![0]]
                } else {
                    answer += numbers[arr[i + 1]!![0]]
                    answer += numbers[arr[i]!![0]]
                    i++
                }
            }
        } else if(i>1) {
            answer += numbers[arr[i]!![0]]
        }
        i++
    }

    if(answer[0] == '0'){
        answer = "0"
    }
    return answer
}

```

처음 주어진 배열을 정렬한 후(전체 배열중 두 쌍만 순서대로 비교하기 때문에 앞자지만 때서 비교하는 상황중 앞자리가 똑같으면 큰 수를 먼저 위치시키기 위해)
앞자리만 가지고 정렬. 해당 정렬의 인댁스값으로 원본 배열에서 수 비교
따라서 위의 값 같은 상황에서 비교가 재대로 안댐, (전체 수를 비교했어야 함.)

결국 해당 숫자를 문자로 치환한 후 정렬 알고리즘을 대입해 해결.
버블정렬 삽입정렬은 시간초가로 문제 해결에 실패, 합병정렬을 이용하여 문제 해결

그리고 내가 본 가장 인상적인 코드.


```kotlin
class Solution {
    fun solution(numbers: IntArray): String {
        var answer = ""        
        numbers.sortedWith(Comparator({num1: Int, num2: Int -> "$num2$num1".compareTo("$num1$num2")})).forEach { answer += it }
        if ("(0*)".toRegex().replace(answer, "").isEmpty()) {
            answer = "0"
        }
        return answer
    }
}
```

흠..Comparator 왜 이런식으로 이용하지 못한걸까..
결국 문자로 append하여 비교하는 과정은 같은데..
허무하다 코틀린 Comparator 에 대한 공부가 필요할듯
