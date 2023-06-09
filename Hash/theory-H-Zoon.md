# 금주 스터디 링크
https://school.programmers.co.kr/learn/courses/30/parts/12077


# 해시함수의 기본 계념


## 해시는 다양한 특징을 가지지만 (암호화, 해시 함수 알고리즘, 충돌 방지 등..) 본 정리에서는 자료구조적 특징을 집중적으로 설명

* 해시는 데이터를 관리/ 유지하기 위한 자료구조.

* 해시는 이론적으로 데이터의 삽입과 삭제에 있어서 𝑂(1)의 시간 복잡도를 가진다

* 가장 큰 특징 -> 리소스를 포기하고 속도를 취한 자료구조 방식


데이터 --> 해시함수 --> 해시테이블 (인덱스, 값)

위 과정을 그림으로 나타내면

Data(key)

↓

[hash function]

↓

key → hash value

↓

[hash Table]
-(index)--key--value-



## 해시함수의 구조

* 키(key) : 고유한 값. 해시 함수의 input이 된다. 다양한 길이의 값이 될 수 있다. 이 상태로 최종 저장소에 저장이 되면 다양한 길이 만큼의 저장소를 구성해 두어야 하기 때문에 해시 함수로 값을 바꾸어 저장이 되어야 공간의 효율성을 추구할 수 있다.

* 값(value) : 저장소(bucket, slot)에 최종적으로 저장되는 값으로 키와 매칭되어 저장, 삭제, 검색, 접근이 가능해야 한다.

* 해시 함수(Hash Function) : 키(key)를 해시(hash)로 바꿔주는 역할을 한다. 다양한 길이를 가지고 있는 키(key)를 일정한 길이를 가지는 해시(hash)로 변경하여 저장소를 효율적으로 운영할 수 있도록 도와준다. 다만, 서로 다른 키(key)가 같은 해시(hash)가 되는 경우를 해시 충돌(Hash Collision)이라고 하는데, 해시 충돌을 일으키는 확률을 최대한 줄이는 함수를 만드는 것이 중요하다.

* 해시(Hash) : 해시 함수(Hash Function)의 결과물이며, 저장소(bucket, slot)에서 값(value)과 매칭되어 저장된다.


![image](https://user-images.githubusercontent.com/43941511/230598434-36c1e323-09a3-45f8-8bf9-b1d60b0d41ef.png)






## 왜 해시는 빠르고 이를 어떻게 활용해야 하는가?

### 해시가 빠른 이유

해시 함수는 입력값(키)을 받아서 고정된 길이의 출력값(해시 값)을 반환. 

따라서, 해시 함수에 새로운 데이터의 키를 입력하면, 이 키에 대한 고정된 길이의 해시 값이 계산.

해시 값은 해시 테이블에서 데이터가 저장되는 위치를 결정하는 중요한 역할을 함. 

일반적으로 해시 테이블은 배열 형태로 구현되며, 해시 함수의 결과값에 따라 배열의 인덱스를 결정. 

예를 들어, 해시 함수의 결과값이 0이면, 배열의 0번 인덱스에 데이터가 저장되고, 결과값이 1이면 배열의 1번 인덱스에 데이터가 저장.



### 코드에서 어떻게 활용할 것인가..

#### In Python
파이썬에서는 Dictionary 라는 자료구조를 통해 해시를 제공. 그리고 Dictionary는 dict클래스에 구현

1. 리스트를 쓸 수 없을 때 

리스트는 숫자 인덱스를 이용하여 원소에 접근하는데 즉 list[1]은 가능하지만 list['a']는 불가능합니다.

인덱스 값을 숫자가 아닌 다른 값 '문자열, 튜플'을 사용하려고 할 때 딕셔너리를 사용하면 좋습니다.


2. 빠른 접근  / 탐색이 필요할 때 

위에서 설명한대로 빠른 탐색이 가능.


3. 집계가 필요할 때

주로 원소의 개수를 구하는 문제가 나온다면 Collections 모듈의 Counter 클래스를 이용.

* 파이썬 3.7 이상부터 딕셔너리는 원소가 들어온 순서를 보장합니다. 반면, 3.7 미만은 순서를 보장하지 않습니다.


## 초기화(Init)

{} 를 사용하거나 dict 함수를 호출하여 빈 딕셔너리 선언 가능.

```python
# 빈딕셔너리 생성
dict1 = {} # {}
dict2 = dict() # {}
```

key-value 쌍을 가지는 dictionary도 선언 가능

```python
Dog = {
				'이름': '바둑이',
				'weight': 4
			}
...
{'이름':'바둑이', 'weight':'4'}
```

dictionary를 value로 가지는 dictionary 선언은 다음과 같다.

```python
Animals = {
    'Dog': {
        'name': '동동이',
        'age': '5'
    },
    'Cat': {
        'name': '야옹이',
        'weight': 3
    }
}

'''
 { 'Dog': { 'name': '동동이', 'age': '5'},
   'Cat': {'name': '야옹이','weight': 3 }}
```

## 원소 가져오기 (Get)

파이선 Dictionary에서 원소를 가저오는 방법은 두가지

1. []
2. get 메소드

get 메소드는 get(key.x)로 사용. ⇒ dict 안에 key가 없는 경우 x 리턴

주로 딕셔너리를 카운트(집계)하는 경우에 유용하게 사용 가능

```python
# [] 기호 사용해 원소 가져오기
dict = {'a':1, 'b':2, 'c':3}
dict['a'] #return 1

# get 메소드를 아용해 원소 가져오기 1
# 딕셔너리에 해당 key가 없을때 Key Error 를 내는 대신, 특정한 값을 가져온다.
dict = {'a':1, 'b':2, 'c':3}
dict.get('d',0) #dict 내부에'd'가 없음으로 return 0

# get 메소드를 아용해 원소 가져오기 2
# 물론, 딕셔너리에 해당 key가 있는 경우 대응하는 value를 가져온다.
dict = {'a':1, 'b':2, 'c':3}
dict.get('a',0) #dict 내부에'a'가 있음으로 return 1
```

## 값 삽입하기 (set)

딕셔너리에 값을 집어넣거나, 값을 업데이트 할 때 [] 사용.

```python
# 값 집어넣기
dict = {'a':1, 'b':2, 'c':3}
dict['d'] = 4
dict #{'a':1, 'b':2, 'c':3, 'd':4}

# 값 수정하기1
dict = {'a':1, 'b':2, 'c':3}
dict['c'] = 4
dict #{'a':1, 'b':2, 'c':4}

# 값 수정하기2(값 더하기)
dict = {'a':1, 'b':2, 'c':3}
dict['c'] += 20
dict #{'a':1, 'b':2, 'c':23}
```

## 삭제 (delete)

딕셔너리에서 특정 key값을 지울 때에 다음과 같은 방법을 사용 가능.

**1. del dict_obj[key]**

del은 키워드로써, 만약 딕셔너리에 key가 없다면 keyError가 발생.

**2. pop(key[,default])**

pop은 메소드로써, pop메소드는 key 값에 해당하는 value를 리턴합니다. key가 없다면 두번째 파라미터인 default를 리턴.

만약 default 설정하지 않았을 시엔 keyError가 발생.

```python
# del 이용하기 - 키가 있는 경우
dict = {'a':1, 'b':2, 'c':3}
del dict['a']

dict #{'b':2, 'c':3}

# del 이용하기 - 키가 없는 경우 raise KeyError
dict = {'a':1, 'b':2, 'c':3}
del dict['d']

#ERROR 발생!

# pop 이용하기 - 키가 있는 경우 대응하는 value 리턴
dict = {'a':1, 'b':2, 'c':3}
dict.pop('a', 5)

dict #pop=1 {'b':2, 'c':3}

# pop 이용하기 - 키가 없는 경우 대응하는 default 리턴
dict = {'a':1, 'b':2, 'c':3}
dict.pop('d', 5)

dict #pop=5 {'a':1, 'b':2, 'c':3}
```

## 반복 (**Iterate**)

딕셔너리를 for문을 이용하여 조회할 때 두가지 방법이 존재.

**1. key로만 순회하기**

**2. key, value 동시 순회하기 (items() 사용)**

```python
# key로만 순회
dict = {'a':1, 'b':2, 'c':3}
for key in dict
	print(key)
# 이 경우 value를 찾고 싶으면 dict[key] 와 같이 접근을 따로 해주어야.
...
a
b
c
...

# key-value 동시 순회
dict = {'a':1, 'b':2, 'c':3}
for key, value in dict.items():
	print(key, value)

...
a 1
b 2
c 3
...
```

**1. 특정 key가 딕셔너리에 있는지 없는지 조회할 때 - in 사용하기**

```python
dict = {'a':1, 'b':2, 'c':3}
print("a" in dict) # true
print("a" not in dict) #false
```

**2. key 또는 value만 뽑아내는 방법**

```python
#key 만 : keys()
dict = {'a':1, 'b':2, 'c':3}
dict.keys()

#value만 : values()
dict = {'a':1, 'b':2, 'c':3}
dict.keys()

#key - value 모두 : items()
dict = {'a':1, 'b':2, 'c':3}
dict.items()
```





