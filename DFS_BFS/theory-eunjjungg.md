## BFS & DFS

> 그래프의 전체 노드를 순회하는 완전 탐색 알고리즘 기법 중 하나임.
> 

<br/>

### DFS

- DFS(Depth First Search)
- 깊이 우선 탐색
- 깊이 우선 탐색이라는 말에서 알 수 있듯이 `root → 첫 번째 leaf의 최종 말단 노드까지 탐색 → 두 번째 leaf의 최종 말단 노드까지 탐색` 이런 방식으로 순회하는 알고리즘임.
- 재귀 + 반복문을 사용해서 구현
- 스택을 사용해서 구현

스택을 이용해 어떻게 구현? 

→ https://youtu.be/_hxFgg7TLZQ

![image](https://github.com/eunjjungg/TIL/assets/100047095/fe5f96c1-dc1d-4e57-9b50-4dec3f6824ba)
일단 원리는 요거임.

스택에서 노드를 하나 꺼내고 → 그 노드의 차일드 노드들을 다시 스택으로 넣음 → 꺼낸 노드를 출력

| print | stack |
| --- | --- |
| - | 0 |
| 0 | 1 |
| 1 | 2, 3 |
| 3 | 2, 4, 5 |
| 5 | 2, 4, 6, 7 |
| 7 | 2, 4, 6 |
| 6 | 2, 4, 8 |
| 8 | 2, 4 |
| 4 | 2 |
| 2 | - |

<br/>

### DFS 구현

- 일단 input 값을 적절하게 트리 형식으로 만드는게 중요함.
- 이후에 input의 사이즈 만큼의 visited 배열을 만듦.

```kotlin
fun dfs(index: Int) {
    visited[index] = true
    // 이 노드에서 해주어야 할 처리 수행

    for (nextNodeIndex in arr[index]) {
        if (!visited[nextNodeIndex]) {
            dfs(nextNodeIndex)
        }
    }
}
```

<br/>

### BFS

- BFS(Breadth First Search)
- 너비 우선 탐색
- `root → leaf 모두 탐색 → 다음 레벨의 leaf 모두 탐색 → … → 최하단 레벨의 leaf 모두 탐색` 이런 방식으로 순회하는 알고리즘.
- Queue(LinkedList) + 반복문을 사용해서 구현

큐를 이용해 어떻게 구현?

→ https://youtu.be/_hxFgg7TLZQ

![image](https://github.com/eunjjungg/TIL/assets/100047095/9cb9456e-e450-46a7-948a-0bdf2c569f01)

일단 원리는 요거임.

큐에서 노드를 하나 꺼내고 → 그 노드의 차일드 노드들을 다시 큐로 넣음 → 꺼낸 노드를 출력

| print | queue |
| --- | --- |
| - | 0 |
| 0 | 1 |
| 1 | 2, 3 |
| 2 | 3, 4 |
| 3 | 4, 5 |
| 4 | 5 |
| 5 | 6, 7 |
| 6 | 7, 8 |
| 7 | 8 |
| 8 | - |

<br/>

### BFS Code

- input의 사이즈 만큼의 visited 배열을 만듦.

```kotlin
val queue = LinkedList<Int>()
fun bfs(start: Int) {
    queue.add(start)
    visited[start] = true

    while (queue.isNotEmpty()) {
        val head = queue.poll() 
        
				// 이 노드에서 해주어야 할 작업 처리 

        for (next in edges[head]) {
            if (!visited[next]) {
                visited[next] = true
                queue.add(next)
            }
        }
    }
}
```

<br/>

### Ref

- https://toonraon.tistory.com/44
- https://youtu.be/_hxFgg7TLZQ
