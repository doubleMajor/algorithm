![img.png](IdeaProjects/algorithm/6주차/노상문/leetcode/img.pngjects/algorithm/6주차/노상문/leetcode/img.png)

```java
package algorithm.leetcode.deepestLeavesSum;


//Definition for a binary tree node.


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Solution {
    int deepestLevel = -1; // -1 인 이유 level > deepestLevel이 항상 트루이기 때문에 곧바로 0 레벨임과 루트노드의 값을 갖는다.
    int sum = 0;

    @Test
    void deepestLeavesSum_Test() {
        //[1,2,3,4,5,null,6,7,null,null,null,null,8]
        TreeNode r4 = new TreeNode(8);
        TreeNode l4 = new TreeNode(7);
        TreeNode r2 = new TreeNode(6, null, r4);
        TreeNode l3 = new TreeNode(5);
        TreeNode l2 = new TreeNode(4, l4, null);
        TreeNode r1 = new TreeNode(3, null, r2);
        TreeNode l1 = new TreeNode(2, l2, l3);
        TreeNode root = new TreeNode(1, l1, r1);

        deepestLeavesSum(root);
        Assertions.assertEquals(15, sum);
    }

    public int deepestLeavesSum(TreeNode root) {
        sumLeafNodeVal(root, 0);
        return sum;
    }

    private void sumLeafNodeVal(TreeNode node, int level) {
        if (node == null) { // node가 널이라면 탐색필요 없음. 돌아가
            return;
        }

        if (level == deepestLevel) { // 현재 탐색하고 있는 트리의 노드가 최심층이라면
            sum += node.val;  // sum을 더해죠 !! 호이호이
        } else if (level > deepestLevel) { // 하지만 레베루가 심층보다 높은 층에 있다면?( 최대값을 갱신)
            // 트리모양이 한쪽으로 치우쳐서 한쪽만 더 깊은 경우가 있을 수 있어서임.
            deepestLevel = level; // 최하층이 레벨이야.
            sum = node.val;  // 섬 이것으로 초기화 시켜줘
        }

        sumLeafNodeVal(node.left, level + 1); // 들어갈때마다 레벨 깊이 1씩 붙는거 알지?
        sumLeafNodeVal(node.right, level + 1); // 없으면 이리 들어가는거 알지 ?
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

```