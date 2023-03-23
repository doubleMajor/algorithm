package problem.DeepestLeavesSum;

import base.TreeNode;

/**
 * 트리의 루트가 주어지면 가장 깊은 레벨의 노드들의 합을 구하라
 *
 * 풀이
 * 각 노드의 레벨이 필요하고 이 중에 가장 깊은 레벨을 구해야 한다.
 * dfs를 할 때마다 깊이를 주고.. 최대 깊이를 업데이트한다.
 * 최대 깊이면 sum에 더해주고 만약에 현재 최대깊이보다 크면 그 값이 sum이 된다.
 *
 * 접근 1.
 * 처음에는 dfs를 한 번 해서 최대 깊이를 구한 다음에 다시 dfs를 해서 최대 깊이에 해당하는 노드들의 합을 구했다.
 * 코드를 만들고 보니 최대 깊이를 구하는 과정에서 각 레벨의 노드의 합을 최대로 놓고, 더 깊은 레벨이 있으면 그 노드의 값으로
 * 업데이트 하면 되겠다는 생각이 들었다.
 */
class Solution {
    int sum = 0;
    int deepestLevel = 0;

    public int deepestLeavesSum(TreeNode root) {
        getDeepestLeaveSum(root, deepestLevel);

        return sum;
    }

    private void getDeepestLeaveSum(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (deepestLevel < level) {
            deepestLevel = level;
            sum = root.val;
        } else if (deepestLevel == level) {
            sum += root.val;
        }

        getDeepestLeaveSum(root.left, level + 1);
        getDeepestLeaveSum(root.right, level + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(8);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;


    }
}
