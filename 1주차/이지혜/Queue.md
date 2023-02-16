# 큐(Queue)

### 큐(Queue)의 특징

![Untitled](%E1%84%8F%E1%85%B2(Queue)%20aa56f8cf020d4259a939061d530499f6/Untitled.png)

- 선입선출(First-in, First-out) 형태의 자료구조
- 큐의 맨뒤(rear)는 데이터 추가 연산만 수행(Enqueue)
- 큐의 맨앞(front)은 데이터 삭제연산만 수행(Dnqueue)
- 그래프의 넓이 우선 탐색(BFS)에서 사용

### 큐(Queue)의 사용법

- 큐(Queue)의 선언

```java
import java.util.LinkedList; //import
import java.util.Queue; //import
Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언, linkedlist 이용
Queue<String> queue = new LinkedList<>(); //String형 queue 선언, linkedlist 이용
```

자바에서 큐는 LinkedList를 활용하여 생성하기 때문에 Queue와 LinkedList가 다 import되어 있어야 사용이 가능

- 큐(Queue)의 추가 및 삭제

```java
Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언
queue.offer(1);     // queue에 값 1 추가
queue.offer(2);     // queue에 값 2 추가
queue.offer(3);     // queue에 값 3 추가
queue.poll();       // queue에 첫번째 값을 반환하고 제거 비어있다면 null
queue.remove();     // queue에 첫번째 값 제거
queue.clear();      // queue 초기화
```