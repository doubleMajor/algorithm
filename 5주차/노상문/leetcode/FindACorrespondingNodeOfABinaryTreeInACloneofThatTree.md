![img.png](img.png)

```java
package algorithm.leetcode.findACorrespondingNodeOfABinaryTreeInACloneofThatTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

/**
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 * 두 개의 이진 트리 원본 및 복제가 주어지고 원본 트리의 노드 대상에 대한 참조가 제공됩니다.
 * The cloned tree is a copy of the original tree.
 * 복제된 트리는 원본 트리의 복사본입니다.
 * Return a reference to the same node in the cloned tree.
 * 복제된 트리에서 동일한 노드에 대한 참조를 반환합니다.
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 * 두 개의 트리 또는 대상 노드를 변경할 수 없으며 응답은 복제된 트리의 노드에 대한 참조여야 합니다.
 */
public class FindACorrespondingNodeOfABinaryTreeInACloneofThatTree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     * <p>
     * <p>
     * 모든 예에서 원본 트리와 복제된 트리가 표시됩니다. 대상 노드는 원래 트리의 녹색 노드입니다. 답은 복제된 트리의 노란색 노드입니다.
     */

    @Test
    void solution_test() {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(7);
        TreeNode l1 = new TreeNode(4);
        TreeNode r1 = new TreeNode(3);

        root.left = l1;
        root.right = r1;

        TreeNode l2 = new TreeNode(0);
        TreeNode r2 = new TreeNode(6);

        l1.left = l2;
        l1.right = r2;

        TreeNode r3 = new TreeNode(19);
        r1.right = r3;

        // 복제 이진트리 만들기
        TreeNode clonedRoot = new TreeNode(7);
        TreeNode clonedL1 = new TreeNode(4);
        TreeNode clonedR1 = new TreeNode(3);

        clonedRoot.left = clonedL1;
        clonedRoot.right = clonedR1;

        TreeNode clonedL2 = new TreeNode(0);
        TreeNode clonedR2 = new TreeNode(6);

        clonedL1.left = clonedL2;
        clonedL1.right = clonedR2;

        TreeNode clonedR3 = new TreeNode(19);
        clonedR1.right = clonedR3;

        // 대상 노드 찾기
        TreeNode target = r1;

        TreeNode answer = solution.getTargetCopy(root,clonedRoot,target);

        Assertions.assertEquals(target.val, answer.val);
}

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        if (original == null){
            return null;
        }

        if (original == target){
            return cloned;
        }

        TreeNode left = this.getTargetCopy(original.left,cloned.left,target);
        TreeNode right = this.getTargetCopy(original.right,cloned.right,target);

        if (left != null){
            return left;
        }else {
            return right;
        }
    }
}

 
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
}

```