문제 1. https://leetcode.com/problems/deepest-leaves-sum/

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int bottom = 0;
    int sum=0;
    public int deepestLeavesSum(TreeNode root) {
        bottomSum(root, 0);
        return sum;
    }
    //바닥 sum
    private void bottomSum(TreeNode root, int height) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            if(bottom < height) { // 바닥이 높이보다 작으면 최하위 변경
                bottom = height;
                sum = root.val;
            }else if(bottom == height) {
                sum += root.val;
            }
        }
        if(root.left != null) {
            bottomSum(root.left, height + 1);
        }
        if(root.right != null) {
           bottomSum(root.right, height + 1);
        }
    }
}
```
![img.png](img.png)