# [level 3] 가장 먼 노드 - 49189 

### 문제

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/49189) 

### 성능 요약

메모리: 105 MB, 시간: 7947.86 ms

### 구분

코딩테스트 연습 > 그래프

### 채점결과

Empty

### 문제 설명

<p>n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.</p>

<p>노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.</p>

<h5>제한사항</h5>

<ul>
<li>노드의 개수 n은 2 이상 20,000 이하입니다.</li>
<li>간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.</li>
<li>vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.</li>
</ul>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>n</th>
<th>vertex</th>
<th>return</th>
</tr>
</thead>
        <tbody><tr>
<td>6</td>
<td>[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]</td>
<td>3</td>
</tr>
</tbody>
      </table>
<h5>입출력 예 설명</h5>

<p>예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.</p>

<p><img src="https://grepp-programmers.s3.amazonaws.com/files/ybm/fadbae38bb/dec85ab5-0273-47b3-ba73-fc0b5f6be28a.png" title="" alt="image.png"></p>


> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges

## 문제 풀이

```java
public int solution(int n, int[][] edge) {
    //같은 depth의 노드들을 담을 Queue
    Queue<Integer> queue = new LinkedList<>();
    //방문처리 할 변수(사이즈는 노드의 개수만큼 만들어 준다.)
    boolean[] visited = new boolean[n];
    //문제는 1번 노드에서 가장 먼 depth의 노드 개수를 구하는 문제이므로 1depth인 1을 등록
    queue.offer(1);
    visited[0] = true;
    int length = edge.length;
    int result = 0;
    while(true) {
        int count = 0;
        //현재 depth의 노드들을 담을 Queue 
        Queue<Integer> innerQueue = new LinkedList<>();
        while(!queue.isEmpty()) {
            //현재 depth의 노드들을 담는다.
            innerQueue.offer(queue.poll());
        }
        while(!innerQueue.isEmpty()) {
            int node = innerQueue.poll();
            for(int i = 0; i < length; i++) {
                //무방향 그래프이므로 현재 노드의 값은 배열의 0번이 될 수도 있고 1번이 될 수도 있다.
                if(edge[i][0] == node || edge[i][1] == node) {
                    int index = 0;
                    //배열의 0번이 노드랑 같을 경우 배열의 1번을 방문한다.
                    if(edge[i][0] == node && !visited[(index = edge[i][1] - 1)]) {
                        queue.offer(edge[i][1]);
                        count++;
                    }
                    //배열의 1번이 노드랑 같을 경우 배열의 0번을 방문한다.
                    if(edge[i][1] == node && !visited[(index = edge[i][0] - 1)]) {
                        queue.offer(edge[i][0]);
                        count++;
                    }
                    visited[index]= true;
                }
            }
        }
        //만약 새로 방문할 노드가 없을 경우 탈출한다.
        if(queue.isEmpty()) break;
        result = count;
    }
    return result;
}
```

## 결과
![결과](test_result.png)