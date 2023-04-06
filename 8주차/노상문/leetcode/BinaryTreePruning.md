![img.png](img.png)

```java
package algorithm.leetcode.binaryTreePruning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreePruning {

    @Test
    void binaryTreePruning_Test() {
        Solution solution = new Solution();

        // Construct the input tree.
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        // Call the pruneTree method.
        TreeNode prunedRoot = solution.pruneTree(root);

        // Check the pruned tree structure.
        assertNotNull(prunedRoot);
        assertEquals(1, prunedRoot.val);
        assertNull(prunedRoot.left);
        assertNotNull(prunedRoot.right);
        assertEquals(0, prunedRoot.right.val);
        assertNull(prunedRoot.right.left);
        assertNotNull(prunedRoot.right.right);
        assertEquals(1, prunedRoot.right.right.val);
    }
}


class Solution {
public TreeNode pruneTree(TreeNode root) {
// * 먼저, 현재 노드(root)가 null인지 확인합니다. 만약 null이면 더 이상 순회할 하위 노드가 없으므로 null을 반환
if (root == null) {
return null;
}
//현재 노드의 왼쪽 서브트리를 재귀적으로 호출하며 탐색
// 이 호출에서 반환되는 값을 현재 노드의 왼쪽 자식.
// 이렇게 하면 1을 포함하지 않는 왼쪽 서브트리가 제거.
//1을 포함하지 않는 왼쪽 서브트리가 제거
root.left = pruneTree(root.left);

        //현재 노드의 오른쪽 서브트리를 재귀적으로 호출하며 탐색
        root.right = pruneTree(root.right);
        //이제 현재 노드의 값이 0이고 왼쪽 자식과 오른쪽 자식이 모두 null인지 확인 하시고!,
        // 만약 이 조건이 참이면, 현재 노드는 1을 포함하지 않는 서브트리의 루트이므로 현재 노드를 제거하기 위해 null을 바ㅣ반환한다
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}

class TreeNode {
int val;
TreeNode left;
TreeNode right;
TreeNode() {}
TreeNode(int val) { this.val = val; }
TreeNode(int val, TreeNode left, TreeNode right) {
this.val = val;
this.left = left;
this.right = right;
}
}
```