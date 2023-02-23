- 스택과 큐는 일종의 규칙이다.
- ADT라 부르기도 함. 자료구조의 행동양식만 정의된 것.

## 스택

> ![img.png](stack.png)

- 한 쪽 끝에서만 자료를 넣고 뺄 수 있는 LIFO(Last In First Out) 형식의 자료 구조
- 가장 먼저 쌓아올린 것이 가장 나중에 빠져나간다.


### 예시

- 맨위에 요소만 읽거나 삭제가 가능. 
- 브라우저의 뒤로가기 버튼(히스토리의 가장 최근값을 빼옴)

### 스택의 기능

```
Stack stack = new Stack();
stack.pop();
stack.push(item);
stack.peek();
stack.size();
stack.isEmpty();
stack.empty(); 
```

- pop(): 스택에서 가장 위에 있는 항목을 제거한다.
- push(item): item 하나를 스택의 가장 윗 부분에 추가한다.
- peek(): 스택의 가장 위에 있는 항목을 반환한다.
- size(): 스택의 사이즈를 반환한다. 
- isEmpty(): 스택이 비어 있을 때에 true를 반환한다.
- empty() : 스택을 초기화