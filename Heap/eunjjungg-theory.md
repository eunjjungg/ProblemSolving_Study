# Heap

- Complete Binary Tree 기반 구조.
    - CBT? 마지막 level을 제외하고 나머지 level에 노드들이 가득 차있고, 마지막 Level의 노드는 왼쪽부터 순서대로 채워지는 형태.
- 최댓값, 최솟값을 찾아내는 연산을 빠르게 수행하게 하기 위해 고안된 자료구조.
- 최소 힙, 최대 힙이 있으며, 어떤 힙이냐에 따라 일정 규칙을 지킨 트리임.
    - 최소 힙 : 각 node의 값은 모든 자신의 자식들 중 가장 **작아야** 하며, 각 자식 node들에 대해서도 같은 규칙이 적용되어야 함.
    - 최대 힙 : 각 node의 값은 모든 자신의 자식들 중 가장 **커야** 하며, 각 자식 node들에 대해서도 같은 규칙이 적용되어야 함.
- 형제 노드끼리는 대소 규칙이 없고 Tree의 왼쪽부터 채워짐. → 이러한 특징을 가졌기에 Heap을 반정렬 상태라고 함.
- Heap내에서 중복 값도 허용함.
- Heap은 Linked List 또는 배열로 구현됨. 그러나 주로 배열로 구현되는데 이유는 Linked List로 구현하면 더 유연한 Tree는 될 수 있지만 자식 노드의 참조값을 저장하기 위해 더 많은 저장공간을 소모하게 됨.

<br/>

## Heap을 배열로 나타내기

- 배열의 0번째 index는 통상적으로 사용하지 않음. 따라서, Root node의 index는 1이 됨.
- 왼쪽에서 오른쪽 순서로 index를 부여함.
- 생성되는 규칙
    - 왼쪽 자식 노드의 index = 부모 노드의 index * 2
    - 오른쪽 자식 노드의 index = 부모 노드의 index * 2 + 1
    - 부모 노드의 index = 자식 노드의 index / 2

<br/>

## Heap에서의 삽입

- 최대 힙의 경우
    - heap은 CBT 기반이므로 마지막 노드의 위치에 새 element를 넣음.
    - 자신의 부모 node와 비교하며 자신의 값이 더 클 경우 위치를 교체함. (위로 올라가게 됨. depth 변경)
    - 자신의 부모 node 보다 작아질 때까지 위 작업을 반복함.
- 최소 힙의 경우
    - eap은 CBT 기반이므로 마지막 노드의 위치에 새 element를 넣음.
    - 자신의 부모 node와 비교하며 자신의 값이 더 작을 경우 위치를 교체함. (위로 올라가게 됨. depth 변경)
    - 자신의 부모 node 보다 커질 때까지 위 작업을 반복함.

<br/>

## Heap에서의 삭제

- 최대 힙에서 최댓값 제거
    - 최댓값을 나타내는 root node의 값을 삭제함.
    - 마지막 node의 값을 root node로 옮기고 마지막 node 제거.
    - root node의 두 자식 node 중 더 큰 값과 root node의 위치를 교체함.
    - 교체한 원래 root node의 값이었던 값이 전체 힙의 규칙(부모 노드는 자식 노드보다 큼)에 맞도록 반복함.
- 최소 힙에서 최솟값 제거
    - 최솟값을 나타내는 root node의 값을 삭제함.
    - 마지막 node의 값을 root node로 옮기고 마지막 node 제거.
    - root node의 두 자식 node 중 더 작은 값과 root node의 위치를 교체함.
    - 교체한 원래 root node의 값이었던 값이 전체 힙의 규칙(부모 노드는 자식 노드보다 작음)에 맞도록 반복함.

<br/>

## Kotlin? → Priority Queue

kotlin에서는 heap을 제공하지 않음. 대신 Priority Queue를 이용해서 구현할 수 있음. Priority Queue의 경우 우선순위가 높은 요소가 가장 먼저 나오게 됨. (엄밀히 말하면 Priority Queue가 heap으로 구성된거라 함.)

```kotlin
fun main() {
    val heap: PriorityQueue<Int> = PriorityQueue()

    // heap 추가
    heap.offer(1)
    heap.offer(3)
    heap.offer(4)
    heap.offer(2)
    heap.offer(5)

    println(heap.toString()) // [1, 2, 4, 3, 5] 최소 heap으로 정렬이 됨.

    // heap 제거 후 return
    println(heap.poll() ?: "없다면 null 반환") // 1

    // heap의 맨 앞 원소 확인
    println(heap.peek() ?: "없다면 null 반환") // 2
    println(heap.element()) // 4 없어도 null 반환하지 않고 NoSuchElement throw

    // heap 초기화
    heap.clear()
    println(heap.toString()) // []

    val maxHeap: PriorityQueue<Int> = PriorityQueue { o1, o2 ->
        o2.compareTo(o1)
    }

    // heap 추가
    maxHeap.offer(1)
    maxHeap.offer(3)
    maxHeap.offer(4)
    maxHeap.offer(2)
    maxHeap.offer(5)

    println(maxHeap.toString()) // [5, 4, 3, 1, 2] 최대 heap으로 정렬이 됨.
}
```

아래 사진은 위 코드에서 사용한 최소힙, 최대힙 정렬 과정임.

![image](https://github.com/eunjjungg/ProblemSolving_Study/assets/100047095/fd3b61f3-5968-42a0-97cc-2939899d3494)
