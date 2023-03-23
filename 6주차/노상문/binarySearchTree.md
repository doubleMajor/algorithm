### 이진 트리 구현에서는:

- 레벨 순서로 노드를 추가.  
  - 왼쪽에서 오른쪽으로, 위에서 아래로 노드를 추가.
  이진 트리의 구조는 값에 따라 정렬되지 않음.
- 이진 탐색 트리 구현에서는:

  - 삽입될 값에 따라 노드의 위치가 결정됩니다. 작은 값은 왼쪽 서브트리에, 큰 값은 오른쪽 서브트리에 위치하게 됩니다.
  이진 탐색 트리의 구조는 값에 따라 정렬되어 있어, 탐색과 같은 연산에 이점이 있습니다.

```java
class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}

class BinarySearchTree {
    Node root;

    void insert(int value) {
        root = insertRecursive(root, value);
    }

    Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) { // 값에 따라 정렬된 순서로 노드를 삽입합니다.
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }

        return current;
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
    }
}

```