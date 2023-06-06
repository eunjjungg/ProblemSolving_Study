# **정렬**

**정렬**은 데이터를 특정한 기준에 따라서 순서대로 나열하는 것이다.

정렬 알고리즘은 다음에 포스팅할 이진 탐색의 전처리 과정이라고 할 수 있다. 많은 정렬 알고리즘들 중 선택 정렬, 삽입 정렬, 퀵 정렬, 계수 정렬에 대해 알아보자. 이 정렬 알고리즘들을 공부하면서 알고리즘의 효율성의 중요성을 자연스럽게 깨닫게 될 수 있다.

## **선택 정렬**

선선택 정렬(Selection Sort)은 주어진 배열에서 가장 작은 값을 선택하여 배열의 왼쪽부터 차례대로 정렬된 위치에 배치하는 방식으로 정렬한다. 선택 정렬은 매번 가장 작은 값을 선택하므로 정렬 과정에서 최솟값이 왼쪽으로 이동해가며 배열이 정렬되는 모습을 보인다.

```kotlin
fun selectionSort(arr: IntArray) {
    val n = arr.size
    for (i in 0 until n - 1) {
        var minIndex = i
        // 현재 위치부터 배열 끝까지 순회하며 최솟값의 인덱스를 찾음
        for (j in i + 1 until n) {
            if (arr[j] < arr[minIndex])
                minIndex = j
        }
        // 현재 위치와 최솟값의 인덱스를 교환
        val temp = arr[minIndex]
        arr[minIndex] = arr[i]
        arr[i] = temp
    }
}
```

**선택 정렬의 시간 복잡도**

선택 정렬의 연산 횟수는 N + (N-1) + (N-2) + ... + 2

따라서 이의 근사치는 N x (N+1) / 2 라고 할 수 있다. 이의 빅오 표기법으로는 O(N2)

## **삽입 정렬**

삽입 정렬은 데이터를 하나씩 확인하면서, 각 데이터를 적절한 위치에 삽입하는 알고리즘이다. 따라서 이는 데이터가 거의 정렬이 되어 있을 경우 더욱 효율적이다.

첫번째의 데이터는 정렬이 되어있다고 보고 두번째의 데이터부터 정렬이 되어있는 데이터들 사이에 적절히 삽입하면서 (선택정렬과는 달리) 한 턴에 정렬이 완료되는 알고리즘이다.

```kotlin
fun insertionSort(arr: IntArray) {
    val n = arr.size
    for (i in 1 until n) {
        val key = arr[i]
        var j = i - 1
        // key와 비교하여 삽입할 위치를 찾음
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        // 삽입할 위치를 찾아 요소를 뒤로 이동하고 key를 삽입
        arr[j + 1] = key
    }
}
```

**삽입 정렬의 시간 복잡도**

선택 정렬과 마찬가지로 반복문이 2번 중첩되어 사용되었으므로, 삽입 정렬의 시간 복잡도는 O(N2)이다. 여기서 기억해두어야 할 점은 삽입 정렬은 거의 정렬이 되어 있는 데이터의 경우, 매우 빠르게 동작한다는 점이다. 이 경우에는 O(N)의 시간복잡도를 가질 수도 있게 된다.

## **퀵 정렬**

퀵 정렬(Quick Sort)은 분할 정복(Divide and Conquer) 전략을 사용하여 정렬을 수행한다. 배열에서 하나의 요소를 피벗(pivot)으로 선택한 후, 피벗을 기준으로 작은 값은 왼쪽으로, 큰 값은 오른쪽으로 분할하여 정렬한다. 이러한 분할과 정렬을 재귀적으로 반복하여 전체 배열을 정렬한다.

```kotlin
fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(arr, low, high)
        // 분할하여 재귀적으로 정렬
        quickSort(arr, low, pivotIndex - 1)
        quickSort(arr, pivotIndex + 1, high)
    }
}

fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1
    for (j in low until high) {
        if (arr[j] < pivot) {
            i++
            // 현재 요소를 피벗보다 작은 그룹의 끝으로 이동
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    // 피벗을 기준으로 분할하여 피벗의 최종 위치를 반환
    val temp = arr[i + 1]
    arr[i + 1] = arr[high]
    arr[high] = temp
    return i + 1
}
```

**퀵 정렬의 시간 복잡도**

앞에서 배웠던 선택 정렬과 삽입 정렬의 시간 복잡도는 O(N2)이었다. 이에 반해 퀵 정렬의 평균 시간 복잡도는 O(NlogN)이다. 그러나 이때 주의할 점은 최악의 경우에 대한 시간 복잡도는 O(N2)라는 점이다. 무작위로 입력된 데이터의 경우 퀵 정렬은 효율적이지만, 이미 데이터가 정렬되어 있는 경우에는 매우 느리게 동작된다.

## **계수 정렬**

계수 정렬 알고리즘은 특정한 조건이 부합하는 경우에만 사용할 수 있지만, 매우 빠른 정렬 알고리즘이다.

계수 정렬은 '데이터의 크기 범위가 제한되어 정수 형태로 표현할 수 있는 경우'에만 사용할 수 있다. 즉, 데이터의 범위가 무한한 실수형 데이터인 경우 계수 정렬을 사용하기 어렵다. 일반적으로 가장 큰 데이터와 가장 작은 데이터의 차이가 1,000,000을 넘지 않을 때 효과적으로 사용할 수 있다.

계수 정렬이 이러한 조건을 가지는 이유는, 계수 정렬 이용 시 '모든 범위를 담을 수 있는 크기의 리스트(배열)를 선언'해야 하기 때문이다. 즉, 가장 큰 데이터와 가장 작은 데이터의 차이가 N 이라면 총 N+1개의 데이터가 들어갈 수 있는 리스트를 초기화해야 한다.

계수 정렬은 이전의 설명했던 정렬 알고리즘들처럼 직접 데이터의 값들을 비교한 후, 위치를 변경하며 정렬하는 방식이 아니다. 계수 정렬은 별도의 리스트를 선언하고 그 안에 데이터들의 정보를 저장하는 식으로 정렬된다.

```
fun countingSort(arr: IntArray) {
    val max = arr.maxOrNull() ?: return
    val min = arr.minOrNull() ?: return
    val range = max - min + 1

    // 각 요소의 개수를 세는 배열 생성
    val count = IntArray(range) { 0 }

    // 각 요소의 개수 세기
    for (num in arr) {
        val index = num - min
        count[index]++
    }

    // 누적 개수 배열 생성
    for (i in 1 until range) {
        count[i] += count[i - 1]
    }

    // 정렬된 결과를 저장할 배열 생성
    val sortedArray = IntArray(arr.size)

    // 원본 배열을 역순으로 순회하면서 정렬
    for (i in arr.size - 1 downTo 0) {
        val num = arr[i]
        val index = num - min
        val sortedIndex = count[index] - 1
        sortedArray[sortedIndex] = num
        count[index]--
    }

    // 정렬된 배열을 원본 배열에 복사
    sortedArray.copyInto(arr)
}
```

**계수 정렬의 시간 복잡도**

데이터가 양의 정수인 상황에서 데이터의 개수를 N, 데이터 중 최대값의 크기를 K라 하면 계수 정렬의 시간 복잡도는 O(N+K)이다. 따라서 데이터의 범위만 한정되어 있다면 매우 빠르게 동작한다. 다만 계수정렬은 특정 범위내에서 반복되는 숫자가 많은 데이터의 경우에 효율적인 알고리즘임

## 코틀린 활용한 정렬

## 기본 정렬

리스트를 오름차순 또는 내림차순으로 정렬하기 위해서는**`sorted()`**와 **`sortedDescending()`** 함수를 사용.

```kotlin
kotlinCopy code
val numbers = mutableListOf(5, 2, 3, 1, 4)
numbers.sort()  // 오름차순: [1, 2, 3, 4, 5]
numbers.sortDescending()  // 내림차순: [5, 4, 3, 2, 1]

```

## 특정 기준 정렬

**`sortedBy()`** 함수와 **`sortedByDescending()`** 함수를 이용해 특정 기준으로 정렬할 수 있음. 

예를 들어, 클래스의 특정 필드를 기준으로 정렬하고 싶을 때 사용 가능.

```kotlin
kotlinCopy code
data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Charlie", 30))
val sortedByAge = people.sortedBy { it.age }  // 나이 오름차순으로 정렬

```

## 이진탐색

정렬된 리스트에서 특정 원소를 빠르게 찾을 때 사용. 코틀린에서는 **`binarySearch()`** 함수를 이용.

```kotlin
kotlinCopy code
val list = listOf(2, 4, 6, 8, 10)
val index = list.binarySearch(6)  // 6의 인덱스를 찾음: 2

```
