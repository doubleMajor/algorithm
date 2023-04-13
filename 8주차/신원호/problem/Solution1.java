/**
 * Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * A subtree of a node node is node plus every node that is a descendant of node.
 *
 * 이진트리의 루트가 주어질 때, 1을 포함하고 있지 않은 모든 서브트리가 제거 된 똑같은 트리를 반환하라
 *
 * 접근 1. 후위순회를 하며 자신이 0이고 자식이 없는 경우에 삭제한다.
 *        후위순회를 하는 이유는 자신을 가장 마지막에 확인해야 자식이 없는것을 확인 할 수 있기 때문이다.
 */
public class Solution1 {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}
