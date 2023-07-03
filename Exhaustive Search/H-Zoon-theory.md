완전 탐색 (Brute Force) 알고리즘은 문제를 해결하기 위한 모든 가능한 경우를 다 테스트해 보는 방식의 알고리즘이다. 가장 단순하고 직관적인 알고리즘 방식이며, 가능한 모든 방법을 체계적으로 고려하여 정답을 찾아내는 방법이다.

하지만, 이 방식의 가장 큰 단점은 그 시간 복잡도다. 입력 크기가 증가할수록 빠르게 처리 시간이 증가하기 때문에, 보통 작은 크기의 문제를 해결할 때 사용한다.

완전 탐색을 사용하는 대표적인 예는 순열, 조합, 부분 집합 생성 등이 있다.

1. **순차 검색(Sequential Search):** 리스트 내의 특정 값을 찾기 위해 처음부터 끝까지 순차적으로 검색하는 방법. 원하는 값을 찾을 때까지 모든 원소를 확인하므로 시간 복잡도는 O(n)이다.

```kotlin
fun sequentialSearch(list: List<Int>, target: Int): Int {
    for ((index, value) in list.withIndex()) {
        if (value == target) {
            return index
        }
    }
    return -1
}
```

2. **브루트 포스(Brute Force):** 가능한 모든 경우의 수를 차례대로 검사해 가며 문제의 해를 찾는 방법. 알고리즘의 성능은 문제의 크기에 따라 선형적으로 증가한다. 이 방법은 문자열 매칭에서 많이 사용된다.

```kotlin
fun bruteForceSearch(text: String, pattern: String): Int {
    val n = text.length
    val m = pattern.length
    for (i in 0 until n - m + 1) {
        var j = 0
        while (j < m) {
            if (text[i + j] != pattern[j]) break
            j++
        }
        if (j == m) return i
    }
    return -1
}
```

3. **비트 마스크(Bitmask):** 이진수 표현을 활용해 문제를 해결하는 방식. 배열의 각 원소를 선택할지 말지를 0과 1로 표현하는 등의 방식으로 문제의 해를 찾는다. 부분 집합이나 조합을 찾을 때 유용하다.

```kotlin
fun printSubsets(input: List<Int>) {
    val n = input.size
    for (i in 0 until (1 shl n)) {
        val subset = mutableListOf<Int>()
        for (j in 0 until n) {
            if ((i and (1 shl j)) > 0) {
                subset.add(input[j])
            }
        }
        println(subset)
    }
}
```

4. **백트래킹(Backtracking):** 가능한 모든 경로를 탐색하되, 이미 검사한 경로는 다시 검사하지 않도록 하는 방법. 불필요한 경로를 건너뛰는 방식으로 브루트 포스 방식을 최적화한다. 문제의 해결책이 존재하지 않을 때는 이전 단계로 돌아가서 다른 경로를 탐색한다.

```kotlin
fun permute(input: MutableList<Int>, output: MutableList<Int>, used: MutableList<Boolean>) {
    if (output.size == input.size) {
        println(output)
        return
    }
    for (i in input.indices) {
        if (used[i]) continue
        used[i] = true
        output.add(input[i])
        permute(input, output, used)
        output.removeAt(output.size - 1)
        used[i] = false
    }
}
```

5. **재귀 호출(Recursion):** 함수가 자신을 다시 호출하는 방식을 이용해 문제를 해결하는 방법. 복잡한 문제를 작은 문제로 분할해 해결한다. 분할 정복 알고리즘에서 주로 사용된다.

```kotlin
fun factorial(n: Int): Int {
    if (n == 1) return n
    else return n * factorial(n - 1)
}
```

6. **순열(Permutation):** 주어진 원소들로 만들 수 있는 모든 순서의 조합을 찾는 방법.

```kotlin
fun permute(input: List<Int>, start: Int) {
    if (start == input.size) {
        println(input)
    }
    for (i in start until input.size) {
        Collections.swap(input, start, i)
        permute(input, start + 1)
        Collections.swap(input, start, i)
    }
}
```

7. **조합(Combination):** 주어진 원소들로 만들 수 있는 모든 선택의 조합을 찾는 방법. 선택의 순서는 고려하지 않는다.

```kotlin
fun combinations(input: List<Int>, n: Int, start: Int, output: MutableList<Int>) {
    if (n == 0) {
        println(output)
        return
    }
    for (i in start until input.size) {
        output.add(input[i])
        combinations(input, n - 1, i + 1, output)
        output.removeAt(output.size - 1)
    }
}
```
