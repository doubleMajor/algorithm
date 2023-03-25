![img.png](img.png)

~~~java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    private int targetVal = 0;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        targetVal = target.val;

        return search(cloned);
    }

    public TreeNode search(TreeNode current) {

        if (current == null || current.val == targetVal) {
            return current;
        }

        TreeNode node = this.search(current.left);
        return node != null ? node : this.search(current.right);
    }
}
~~~