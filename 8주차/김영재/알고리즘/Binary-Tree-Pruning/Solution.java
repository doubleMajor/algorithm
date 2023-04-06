class Solution {
    public TreeNode pruneTree(TreeNode root) {
        postOrder(root);
        if(root.val == 0 && root.left == null && root.right == null)
            root = null;
        return root;
    }

    private void postOrder(TreeNode cur) {
        if(cur == null) return;
        postOrder(cur.left);
        postOrder(cur.right);
        if(isPrune(cur.left)) cur.left = null;
        if(isPrune(cur.right)) cur.right = null;
    }
    private boolean isPrune(TreeNode root) {
        return root != null && root.val == 0 && (root.left == null || root.left.val == 0) && (root.right == null || root.right.val == 0);
    }
}