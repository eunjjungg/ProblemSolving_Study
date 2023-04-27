![image](https://user-images.githubusercontent.com/100047095/234846172-c820af2b-5158-4ba8-8fcf-8aefb109147f.png)

<br/>

### Stack

- FILO : First In Last Out으로 선입후출의 형태임.
- Top은 가장 최근에 들어온 자료를 가리키는 형태.
- Push : Top을 통해 스택에 삽입하는 연산.
- Pop : Top을 통해 스택에서 제거하는 연산.
- 삽입, 삭제의 시간 복잡도는 O(1). 왜냐하면 Stack의 최상단만 접근하기 때문.

<br/>

### Queue

- FIFO : First In First Out으로 선입선출의 형태임.
- 삭제 연산이 수행되는 곳을 Front, 삽입 연산은 enQueue.
- 삽입 연산이 수행되는 곳을 Rear, 삭제 연산은 deQueue.
- 삽입, 삭제의 시간 복잡도는 O(1).

<br/>

### Deque

- Deque - Double Ended Queue의 줄임말.
- Queue가 한쪽으로는 삽입만 하고 한쪽으로는 삭제만 하는 구조였다면 Deque(덱… 너만의 듀얼을 해!)은 양쪽에서 삽입 + 삭제가 가능한 자료구조.
- First, Last를 사용해서 Queue, Stack처럼 사용할 수 있음.
    - Deque을 Stack처럼 사용하려면 삽입에는 Last, 삭제에는 Last를 사용하거나 반대로 삽입 삭제 모두에 First를 사용하면 됨.
    - Deque을 Queue처럼 사용하려면 삽입에는 Last, 삭제에는 First를 사용하거나 삽입에 First, 삭제에 Last를 사용하면 됨.

<br/>

---

<br/>

### Kotlin - Stack

```kotlin
fun main() {
    val stack = Stack<Int>()
    stack.push(0)
    stack.push(1)
    stack.push(2)
    stack.push(3)

    var popElement = stack.pop()
    println("stack : $stack") // stack : [0, 1, 2]
    println("popElement : $popElement") // popElement : 3

    var peekElement = stack.peek()
    println("stack : $stack") // stack : [0, 1, 2]
    println("peekElement : $peekElement") // peekElement : 2

    var searchIndex = stack.search(1)
    println("stack : $stack") // stack : [0, 1, 2]
    println("searchIndex : $searchIndex") // searchIndex : 2
}
```

| method | 기능 | param | return | throw |
| --- | --- | --- | --- | --- |
| push | 삽입 | item | item |  |
| pop | 삭제 |  | stack의 최상단 항목 | stack이 비어있다면 java.util.EmptyStackException |
| peek | 제거 없이 스택의 최상단 항목 확인 |  | stack의 최상단 항목 | stack이 비어있다면 java.util.EmptyStackException |
| search | 찾기 | Object O | param이 없다면 -1을, param이 있다면 스택의 최상단의 위치를 1로 해서 아래로 갈수록 위치를 +1해주며 위치 반환해줌.  |  |

<br/>

### Kotlin - Queue

```kotlin
// Queue 사용법

// Java의 라이브러리 사용 (Queue, LinkedList import)
import java.util.*

fun main() {
    // 할당은 LinkedList로 해주어야 함. 
    var queue: Queue<String> = LinkedList()
    queue.add("first")
    queue.add("second")
    queue.add("third")
		queue.offer("fourth")

    var popElement = queue.remove()
    println("queue : ${queue}") // queue : [second, third, fourth]
    println("popElement : ${popElement}") // popElement : first

		popElement = queue.poll()
    println("queue : ${queue}") // queue : [third, fourth]
    println("popElement : ${popElement}") // popElement : second

		var peekElement = queue.peek()
    var element = queue.element()
    println("peekElement : ${peekElement}") // peekElement : third
    println("element : ${element}") // element : third
}
```

| method | 기능 | param | return | throw |
| --- | --- | --- | --- | --- |
| add | enqueue | e – the element to add | true | 발생 |
| offer | enqueue | e – the element to add | queue에 추가되면 true, 아니면 false | 발생 |
| poll | dequeue |  | queue의 head |  |
| remove | dequeue |  | queue의 head. queue가 비어있다면 null. | queue가 비어있다면 java.util.NoSuchElementException |
| peek | 큐의 front 확인 |  | queue의 head. queue가 비어있다면 null. |  |
| element | 큐의 front 확인 |  | queue의 head. | queue가 비어있다면 java.util.NoSuchElementException |

<br/>

### Kotlin - Deque

```kotlin
import java.util.ArrayDeque

fun main() {
    val dqAsStack = ArrayDeque<Int>()
    dqAsStack.addLast(0)
    dqAsStack.addLast(1)
    dqAsStack.addLast(2)
    println(dqAsStack.removeLast()) // 2
    println(dqAsStack.removeLast()) // 1
    println(dqAsStack.removeLast()) // 0

    dqAsStack.clear()
    dqAsStack.addFirst(4)
    dqAsStack.addFirst(5)
    dqAsStack.addFirst(6)
    println(dqAsStack.removeFirst()) // 6
    println(dqAsStack.removeFirst()) // 5
    println(dqAsStack.removeFirst()) // 4

    val dqAsQueue = ArrayDeque<Int>()
    dqAsQueue.addLast(0)
    dqAsQueue.addLast(1)
    dqAsQueue.addLast(2)
    println(dqAsQueue.removeFirst()) // 0
    println(dqAsQueue.removeFirst()) // 1
    println(dqAsQueue.removeFirst()) // 2

    dqAsQueue.clear()
    dqAsQueue.addFirst(4)
    dqAsQueue.addFirst(5)
    dqAsQueue.addFirst(6)
    println(dqAsQueue.removeLast()) // 4
    println(dqAsQueue.removeLast()) // 5
    println(dqAsQueue.removeLast()) // 6
}
```

<br/>

### 레퍼런스

- [https://soopeach.tistory.com/229](https://soopeach.tistory.com/229)
- [https://hanyeop.tistory.com/115](https://hanyeop.tistory.com/115)
- [https://medium.com/depayse/kotlin-data-structure-collections-4-stack-queue-deque-4c383efebee9](https://medium.com/depayse/kotlin-data-structure-collections-4-stack-queue-deque-4c383efebee9)
- [https://notepad96.tistory.com/103](https://notepad96.tistory.com/103)
