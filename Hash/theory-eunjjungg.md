
### Hash

- 데이터를 다루는 기법 중 하나로 검색과 저장이 빠르게 처리 됨
- key-value 한 쌍으로 이루어지고 key 값이 배열의 인덱스로 변환되어 검색됨
- 시간 복잡도 O(1)

<br/>

### Hash Function

key를 고정된 길이의 hash로 변경해주는 역할을 함. 이 과정을 hashing이라고 함. 

`f(key) = output` 이라고 했을 때 함수인 f가 hash function, output이 hash된 key임. 그리고 key가 저장됨. 예시로 John Smith의 값을 저장하고 싶을 때 

![image](https://user-images.githubusercontent.com/100047095/230868258-782dc4eb-3034-4871-b37e-e2ddba6a95d9.png)

<br/>

### 용어

- key
    - hash function의 input
    - 해시 테이블에 두 개 이상의 같은 key 값은 없음 = 고유함
    - hash function에 의해 고정된 길이의 값으로 변환됨
- hash function
    - key를 고정된 길의의 값으로 변환하는 함수
    - 서로 다른 key가 같은 hash 값으로 변환될 수 있는데 이를 해시충돌이라 부르고 hash 충돌 발생 확률이 낮을수록 좋은 hash function임
- hash table
    - 해시 함수를 사용하여 키를 해시값으로 변환 → 이 해시값을 테이블의 Index로 사용하여 key와 함께 저장하는 구조
    - hash table에서 데이터가 저장되는 곳이 bucket(slot)
    - 위의 그림의 경우 해시 테이블은 아래와 같음
        
        
        | Index(Hash value) | Data |
        | --- | --- |
        | 01 | (Lisa Smith, 521-8976) |
        | 02 | (John Smith, 521-1234) |
        | … | … |
    
    <br/>
    
    ### Hash Table
    
    > 해시 함수를 사용하여 키를 해시값으로 변환 → 이 해시값을 테이블의 Index로 사용하여 key와 함께 저장하는 구조
    > 
    
    개중 키의 전체 개수와 동일한 크기의 버킷을 가진 해시 테이블 == 해시 충돌 문제가 발생하지 않는 테이블을 **Direct-address table**이라고 함. 해시 충돌 문제는 발생하지 않지만 전체 키(universe of key)가 실 사용 키(actual key)보다 훨씬 많은 경우 사용하지 않는 키들을 위한 공간까지 마련해야 하므로 성능 면에서 좋지 않음. 
    
    따라서 성능을 위해 해시 테이블 크기(m)가 실제 사용하는 키 개수(n)보다 작은 해시테이블을 운용해야 함. n/m = **load factor**이라고 함. 이 값은 해시 테이블의 한 버킷에 평균 몇 개의 키가 매핑되는지를 나타내는 지표임. Direct-address table은 이 값이 1 이하이고 이 값이 1보다 크면 해시 충돌이 발생함. 
    
    <br/>
    
    ### 해시 충돌 문제 해결 방식 - chaining
    
    한 버킷 당 들어갈 수 있는 엔트리의 수에 제한을 두지 않아 모든 자료를 해시 테이블에 담는 방식임. key를 해시하여 나온 값을 index로 사용해서 버킷을 찾아가니 이미 다른 키에 의해 적재된 데이터가 있다면 해당 노드에 체인처럼 노드를 추가하여 연결하는 방식으로 값을 추가함 
    
    <br/>
    
    ### 해시 충돌 문제 해결 방식 - ****open addressing****
    
    chaining과 달리 한 버킷 당 들어갈 수 있는 엔트리는 하나 뿐임. 원래는 key 값을 해시해서 나온 output을 index로 사용해서 저장해야 되는데 open addressing 방법에서는 output이 아닌 값에도 저장할 수 있음. 따라서 chaining 방식에 비해서 용량 문제는 없지만 해시 충돌 문제는 여전함 
    
    <br/>
    
    ### Hash in Kotlin
    
    코틀린에서 hash 구현은 `HashMap`을 사용해서 구현함 
    
    공식 문서 : [https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/)
    
    ```kotlin
    fun main() {
        // Hash 생성 HashMap<K, V>()
        var hashSample = HashMap<String, Any>()
    
        // put : 데이터 추가
        hashSample.put("weight", "비밀")
        hashSample.put("height", 200)
        println("hashSample 데이터 : ${hashSample}")
        // hashSample 데이터 : {weight=비밀, height=200}
    
        // replace : api 24부터 가능, 특정 키의 value 변경
        hashSample.replace("weight", 100)
        // get : 특정 키의 value 확인
        println(hashSample.get("weight"))
        // 100
    
        // containsKey : Returns true if the map contains the specified key.
        println(hashSample.containsKey("age")) // false
        println(hashSample.containsKey("weight")) // true
    
        hashSample.put("birth_m", 2)
        hashSample.put("birth_d", 16)
        hashSample.put("job", "baeksoo")
        println(hashSample.toString())
        // {birth_m=2, birth_d=16, weight=100, job=baeksoo, height=200}
        // remove : Removes the specified key and its corresponding value from this map.
        // remove의 리턴 값은 키에 해당하는 value 값이 있다면 그 값이 리턴되고 없다면 null
        hashSample.remove("job")
        println(hashSample.toString())
        // {birth_m=2, birth_d=16, weight=100, height=200}
    
        // clear : Removes all elements from this map.
        hashSample.clear()
        println(hashSample.toString())
        // {}
    }
    ```
    
    레퍼런스
    
    [https://ratsgo.github.io/data structure&algorithm/2017/10/25/hash/](https://ratsgo.github.io/data%20structure&algorithm/2017/10/25/hash/)
    
    [https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/)
    
    [https://kkh0977.tistory.com/648](https://kkh0977.tistory.com/648)
