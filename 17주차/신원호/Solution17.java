import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;

        if (target > nums[length-1]) {
            return length;
        }

        for (int i = 0; i <= length - 1; i++) {
            int num = nums[i];

            if (target == num) {
                return i;
            } else if (target > num && target < nums[i + 1]) {
                return i + 1;
            }
        }

        return 0;
    }
}

class Solution {
    public int climbStairs(int n) {
        return dfs(0, n);
    }

    private int dfs(int curr, int n) {
        if (curr > n) {
            return 0;
        }

        if (curr == n) {
            return 1;
        }

        int oneStep = dfs(curr + 1, n);
        int twoSteps = dfs(curr + 2, n);

        return oneStep + twoSteps;
    }
}

import java.util.ArrayList;
        import java.util.List;
        import java.util.Stack;

public class TreeNode {
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

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> answerArray = new ArrayList<>();
        if (root == null) {
            return List.of();
        }
        if (root.left == null && root.right == null) {

            return List.of(root.val);
        }


        Stack<TreeNode> treeNodeStack = new Stack<>();

        treeNodeStack.add(root);

        while (!treeNodeStack.isEmpty()) {
            TreeNode currentNode = treeNodeStack.pop();

            if (currentNode.left != null) {
                treeNodeStack.add(currentNode);
                treeNodeStack.add(currentNode.left);
                currentNode.left = null;
                continue;
            }

            answerArray.add(currentNode.val);

            if (currentNode.right != null) {
                treeNodeStack.add(currentNode.right);
            }
        }

        return answerArray;
    }
}