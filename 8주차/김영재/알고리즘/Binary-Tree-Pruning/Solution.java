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

    /*
      //스포
      //스포
      //스포
      //스포
      //스포
      //스포
      //스포
      //스포
      //스포
      public TreeNode pruneTree(TreeNode root) {
            if (root == null) {
              return null;
            }
            root.left = pruneTree(root.left);
            root.right = pruneTree(root.right);
            if (root.left == null && root.right == null && root.val == 0) {
              return null;
            }
            return root;
          }
     */
}