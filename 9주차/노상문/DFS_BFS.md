# 탐색

## BFS / DFS 정리

### BFS
- BFS는 Breadth-First Search(너비 우선 탐색)의 약자.
- 그래프나 트리와 같은 자료구조에서 꼭짓점(vertex)을 방문하는 알고리즘 중 하나이다.
- BFS는 시작 꼭짓점에서 가까운 순서대로 꼭짓점을 방문하며,  
인접한 꼭짓점들을 모두 방문한 후에 다음 레벨의 꼭짓점들을 방문하는 방식으로 진행된다.
  - 시작 꼭짓점: 탐색을 시작할 노드.
  - 인접한 꼭짓점: 시작 꼭짓점과 연결된 직접적인 이웃 노드들.
  - 레벨: 시작 꼭짓점으로부터 떨어진 거리(간선의 수)에 따라 노드들이 구분되는 층.
  - BFS 알고리즘은 시작 꼭짓점에서 가장 가까운 노드부터 방문을 시작.
  - 이후 인접한 꼭짓점들을 모두 방문한 후, 다음 레벨의 꼭짓점들로 넘어간다.
  - 이 과정은 모든 노드를 방문할 때까지 반복된다.
- BFS는 주로 큐(Queue)를 사용하여 구현되며, 그래프나 트리에서 최단 경로를 찾거나 모든 노드를 효과적으로 방문하는 데 사용.
- 주어진 노드에서 시작하여 동일한 레벨의 인접 노드들을 먼저 방문하고, 그 다음 레벨의 노드들을 방문하는 방식으로 진행되기 때문에 "너비 우선"이라고 한다.

```
  A
 / \
B   C
|   |
D   E
```

BFS 알고리즘을 사용하여 A에서 시작한다면, 다음과 같은 순서로 노드들을 방문하게 됩니다.

1. A (시작 꼭짓점)
2. B, C (A와 인접한 꼭짓점들)
3. D, E (B, C와 인접한 꼭짓점들, 즉 다음 레벨의 꼭짓점들)  
따라서, BFS 알고리즘을 사용하여 노드들을 방문하는 순서는 A → B → C → D → E가 됩니다.

### BFS 예제
```markdown
    A
   / \
  B   C
 / \   \
D   E   F
```
```java
import java.util.*;

// 이진 트리의 노드를 나타내는 클래스
class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    // 생성자
    TreeNode(char val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BFSExample {
    public static void main(String[] args) {
        // 이진 트리 생성
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.left.right = new TreeNode('E');
        root.right.right = new TreeNode('F');

        // 이진 트리에 BFS
        bfs(root);
    }

    // BFS 알고리즘을 구현하는 메소드
    public static void bfs(TreeNode root) {
        // 노드들을 저장할 큐 생성
        Queue<TreeNode> queue = new LinkedList<>();
        // 루트 노드를 큐에 삽입
        queue.add(root);

        // 큐가 비어있지 않은 동안 반복
        while (!queue.isEmpty()) {
            // 큐에서 노드를 하나 꺼내옴
            TreeNode node = queue.poll();
            // 꺼낸 노드의 값을 출력
            System.out.print(node.val + " ");

            // 꺼낸 노드의 왼쪽 자식이 존재하면 큐에 삽입
            if (node.left != null) {
                queue.add(node.left);
            }
            // 꺼낸 노드의 오른쪽 자식이 존재하면 큐에 삽입
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}

```

```java
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeBFSTest {

    @Test
    public void testBFS() {
        // 트리 구조 생성
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // 이진 트리 BFS 순회 결과 확인
        BinaryTreeBFS bfs = new BinaryTreeBFS();
        List<Integer> bfsResult = bfs.bfs(root);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertEquals(expected, bfsResult);
    }
}
```

### DFS
- DFS (Depth-First Search, 깊이 우선 탐색)는 그래프나 트리와 같은 자료 구조를 탐색할 때 사용되는 알고리즘.
- DFS는 현재 꼭짓점에서 인접한 꼭짓점 중 방문하지 않은 꼭짓점을 찾아 깊이를 우선적으로 탐색하며, 더 이상 인접한 꼭짓점이 없을 때 이전 꼭짓점으로 돌아가 다른 방향을 탐색하는 방식으로 진행된다.
- DFS는 재귀적인 방법을 사용할 수도 있고, 스택을 사용하여 반복적인 방법으로도 구현가능.
- 이 알고리즘은 모든 꼭짓점을 방문할 때까지 깊이를 우선하여 탐색하기 때문에, 깊이가 깊은 구조에서는 BFS보다 더 빠르게 목표 꼭짓점에 도달할 수 있다.  
하지만, 최단 경로를 찾는 데에는 적합하지 않다.

```
        A
       / \
      B   C
     / \
    D   E
```
A → B → D → E → C

### 전위 순회
```java
class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;
    TreeNode(char val) { this.val = val; }
}

public class DFSExample {
    public static void main(String[] args) {
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.left.right = new TreeNode('E');
        root.right.right = new TreeNode('F');

        System.out.print("Pre-order DFS: ");
        preOrderDFS(root);
    }

    public static void preOrderDFS(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.val + " ");
        preOrderDFS(node.left);
        preOrderDFS(node.right);
    }
}

```