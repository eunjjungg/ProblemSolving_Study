# Stack, Queue

# Stack
<p align="center">
    <img src = "https://velog.velcdn.com/images%2Fnnnyeong%2Fpost%2F9a57f718-bdc2-4375-9760-f5c8ce12fb0f%2Fimage.png">
</p>

### 특징

한쪽 끝에서만 자료의 삽입, 삭제가 가능한 구조

따라서 LIFO (Last In Firest Out, 후입선출) 방식으로 동작한다.

이때 가장 위쪽에(최근에) 삽입된 자료의 위치를 Top 이라고 한다.

→ 데이터의 접근은 Top 요소에 대해서만 가능하고 그 외 위치에서의 접근 (삽입, 수정, 삭제) 은 불가능하다.

가장 먼저 추가된 항목 (stack의 가장 아래쪽에 위치한 데이터)는 base 라고 한다.

이때 비어있는 stack에 대해서 데이터 접근을 시도하면 stack underflow가,

스텍의 크기가 비어있을 때 (가용 크기가 없는 경우) stack overflow 가 발생한다.

### 시간복잡도

stack의 시간복잡도는 O(1)

→ 데이터의 접근은 항상 top 위치에서만 이루어지기 때문.

### 장단점

데이터의 접근이 빠름 (위에 서술한 시간복잡도 참조)

하지만 stack의 전체 데이터에 탐색하려면 모든 데이터를 꺼내면서 진행해야 한다.

### 활용하기

1. 재귀 알고리즘
2. DFS 알고리즘
3. 괄호 검사, 후위 연산법, 문자열 역순 출력하기.

## Queue

<p align="center">
    <img src = "https://velog.velcdn.com/images%2Fnnnyeong%2Fpost%2F3244e9fd-82e5-4c52-bef4-dee97640e246%2Fimage.png">
</p>

### 특징

양쪽 끝에서 데이터의 삽입과 삭제가 각각 이루어진다

이를 FIFO (First In First Out, 선입선출) 구조라고 한다.

데이터가 삽입되는 쪽을 rear(혹은 tail), 

데이터가 제거되는 쪽은 front(혹은 head) 라고 한다.

스텍과 마찬가지로 데이터를 추가, 삭제하는 경우에는 queue의 상태(empty, full) 을 파악하고 진행하여야 한다.

### 시간복잡도

queue의 시간복잡도는 O(1)

→ 데이터의 삽입은 rear, 데이터의 제거는 front 에서만 이루어 지기 때문이다.

### 장단점

stack와 동일하다

### 활용하기

데이터를 입력된 순서로 처리해야 하는 경우

BFS 알고리즘

프로세스, 대기 순서 관리하기

## **Deque**

<p align="center">
    <img src = "https://velog.velcdn.com/images%2Fnnnyeong%2Fpost%2Fc412c1f6-9cf2-4fe2-b1c6-b166e2a58c99%2Fimage.png">
</p>

### 특징

Double- Ended Queue 의 줄임말.

한쪽 방향에서만(rear, tail) 삽입, 다른 방향에서만(front, head) 삭제가 가능한 큐와 달리,

양쪽에서 데이터의 삽입과 삭제가 가능한 큐를 의미한다.

또한 중간에 데이터가 삽입될 때 다른 요소들을 앞, 뒤로 밀 수있다. 

### 시간 복잡도

삽입 삭제 연산은 마찬가지로 **O(1)** 의 시간 복잡도를 가진다.

또한 스택/큐와 달리 index 를 통해 요소들에 탐색이 가능하므로 이 역시 **O(1)** 의 시간 복잡도를 가진다.

### 장단점

데이터의 삽입 삭제가 빠르고 앞, 뒤에서 삽입 삭제가 모두 가능하다

가변적 크기

index 를 통해 임의의 원소에 바로 접근이 가능하고, 새로운 원소 삽입 시, 메모리를 재할당하고 복사하지 않고 새로운 단위의 메모리 블록을 할당하여 삽입한다.

중간에서의 삽입 삭제가 어렵고, 스택과 큐에 비해 비교적 구현이 어렵다.

### 활용

데이터를 앞, 뒤쪽에서 모두 삽입 삭제하는 과정이 필요한 경우

데이터의 크기가 가변적일 때

| 자료형 / 특징 |  데이터 입/출 순서 | 시간복잡도 | 장점 | 단점 |
| --- | --- | --- | --- | --- |
| Stack | FILO | O(1) | 데이터의 접근이 빠름  | 전체 데이터에 탐색하려면 모든 데이터를 꺼내면서 진행. |
| Queue | FIFO | - | - | - |
| Deque | 양방향 | - | 양방향 데이터 접근, 가변적 크기 | 중간에서의 삽입 삭제 어려움, 스택, 큐에 비해 구현 어려움. |

---

### 파이썬 stack 추상 자료형

- `Stack()`: 비어 있는 스택 생성. 생성작의 역할.
- `push(item)`: 새로운 항목을 탑(top)으로 추가
- `pop()`: 탑 항목 삭제. 삭제된 항목 반환.
- `peek()`: 탑 항목 반환. 하지만 삭제하진 않음.
- `is_empty()`: 스택이 비었는 여부 판단. 부울값 반환.
- `size()`: 항목 개수 반환.

| stack 연산 | stack 항목 | 설명 | return |
| --- | --- | --- | --- |
| s = Stack() | [] | 스택 초기화 |  |
| s.is_empty() | [] | 스택이 비어있는지? | true |
| s.push(3) | [4] | 새로운 항목 top으로 추가 |  |
| s.push(jun) | [4, ‘jun’] | - |  |
| s.peek() | [4, ‘jun’] | 탑 항목을 반환하지만 삭제는 X | ‘jun’ |
| s.push(true) | [4, ‘jun’, true] | ‘true’ 값 top으로 추가 |  |
| s.size() | [4, ’jun’, true] | stack의 테이터 개수 반환 | 3 |
| s.is_empty() | [4, ’jun’, true] | 스택이 비어있는지 확인 | false |
| s.push(8.4) | [4, ’jun’, true, 8.4] | 8.4를 스택의 top 항목으로 |  |
| s.pop() | [4, ’jun’, true] | top 항목을 반환하고 stack에서 삭제 | 8.4 |
| s.pop() | [4, ’jun’] | - | True |
| s.size() | [4, ’jun’] | stack의 사이즈 | 2 |

```python
s = Stack()

print(s.is_empty())
s.push(4)
s.push("dog")
print(s.peek())
s.push(True)
print(s.size())
print(s.is_empty())
s.push(8.4)
print(s.pop())
print(s.pop())
print(s.size())
```

```python
True
dog
3
False
8.4
True
2
```

### 파이썬 Queue 추상 자료형

- `Queue()`: 비어 있는 큐 생성. 생성작의 역할.
- `enqueue(item)`: 새로운 항목을 꼬리(rear, tail)에 추가. 반환값 없음.
- `dequeue()`: 머리(head, front) 항목 삭제. 삭제된 항목 반환.
- `is_empty()`: 큐가 비었는지 여부 판단. 부울값 반환.
- `size()`: 항목 개수 반환.

| Queue 연산 | Queue 항목 | 설명 | return |
| --- | --- | --- | --- |
| q = Queue() | [] | 큐 초기화 |  |
| q.is_empty() | [] | 큐가 비어있는지? | true |
| q.enqueue(4) | [4] | 새로운 항목 꼬리로 추가 |  |
| q.enqueue("jun") | [’jun’, 4] | - |  |
| q.enqueue(true) | [true, ’jun’, 4] | - |  |
| q.size() | [true, ’jun’, 4] | 큐의 테이터 개수 반환 | 3 |
| q.is_empty() | [true, ’jun’, 4] | 큐가 비어있는지 확인 | false |
| q.enqueue(8.4) | [8.4, true, ’jun’, 4] | 새로운 항목 꼬리로 추가 |  |
| q.dequeue() | [8.4, true, ’jun’] | 머리 항목 삭제 후 삭제된 값 반환 | 4 |
| q.dequeue() | [8.4, true] | - | ‘jun’ |
| q.size() | [8.4, true] | 큐의 사이즈 반환 | 2 |
|  | 꼬리///머리 |  |  |

```python
q = Queue()

print(q.is_empty())
q.enqueue(4)
q.enqueue("dog")
q.enqueue(True)
print(q)
print(q.size())
print(q.is_empty())
q.enqueue(8.4)
q.dequeue()
q.dequeue()
print(q.size())
print(q)
```

```python
True
<<[True, 'jun', 4]>>
3
False
2
<<[8.4, True]>>
```

### 파이썬 Deque 추상 자료형

- `Deque()`: 비어 있는 덱 생성.
- `add_front(item)`: 머리에 새로운 항목 추가. 반환값 없음.
- `add_rear(item)`: 꼬리에 새로운 항목 추가. 반환값 없음.
- `remove_front()`: 머리 항목 삭제. 삭제된 항목 반환.
- `remove_rear()`: 꼬리 항목 삭제. 삭제된 항목 반환.
- `is_empty()`: 덱이 비었는지 여부 판단. 부울값 반환.
- `size()`: 항목 개수 반환

| 덱 연산 | 덱 항목 | 설명 | 반환값 |
| --- | --- | --- | --- |
| d = Deque() | [] | 덱 초기화 |  |
| d.is_empty() | [] | 덱이 비어있는지 확인 | True |
| d.add_rear(4) | [4] | 덱의 꼬리에 값 삽입 |  |
| d.add_rear("dog") | [’dog’, 4] | 덱의 머리에 값 삽입 |  |
| d.add_front("cat") | [’dog’, 4, 'cat'] | 덱의 머리에 값 삽입 |  |
| d.add_front(True) | [’dog’, 4, 'cat', True] | - |  |
| d.size() | [’dog’, 4, 'cat', True] | 덱의 사이즈 반환 | 4 |
| d.is_empty() | [’dog’, 4, 'cat', True] | 덱이 비어있는지 확인 | False |
| d.add_rear(8.4) | [8.4, ‘dog’, 4, 'cat', True] | 덱의 꼬리에 값 삽입 |  |
| d.remove_rear() | [’dog’, 4, 'cat', True] | 덱의 머리값 삭제 후 반환 | 8.4 |
| d.remove_front() | [’dog’, 4, 'cat'] | 덱의 머리값 삭제 후 반환 | True |

```python
d=Deque()
print(d.is_empty())
d.add_rear(4)
d.add_rear('dog')
d.add_front('cat')
d.add_front(True)
print(d)
print(d.size())
print(d.is_empty())
d.add_rear(8.4)
print(d.remove_rear())
print(d.remove_front())
print(d)
```

### Deque example ‘palindrome’

```python
True
<~['dog', 4, 'cat', True]~>
4
False
8.4
True
<~['dog', 4, 'cat']~>
```

```python
def pal_checker(a_string):
    char_deque = Deque()
    
    # 덱 객체 생성
    for ch in a_string:
        char_deque.add_rear(ch)

    # 머리와 꼬리 항목 비교
    while char_deque.size() > 1:
        first = char_deque.remove_front()
        last = char_deque.remove_rear()
        if first != last:
            return False

    return True
```

**레퍼런스**

[https://velog.io/@nnnyeong/자료구조-스택-Stack-큐-Queue-덱-Deque](https://velog.io/@nnnyeong/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EC%8A%A4%ED%83%9D-Stack-%ED%81%90-Queue-%EB%8D%B1-Deque)[https://jud00.tistory.com/entry/자료구조-스택Stack과-큐Queue에-대해서-알아보자](https://jud00.tistory.com/entry/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EC%8A%A4%ED%83%9DStack%EA%B3%BC-%ED%81%90Queue%EC%97%90-%EB%8C%80%ED%95%B4%EC%84%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90)

[https://codingalzi.github.io/algopy/queues.html](https://codingalzi.github.io/algopy/queues.html)
