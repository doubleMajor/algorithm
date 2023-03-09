import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 *
 * The cloned tree is a copy of the original tree.
 *
 * Return a reference to the same node in the cloned tree.
 *
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 *
 * The number of nodes in the tree is in the range [1, 104].
 * The values of the nodes of the tree are unique.
 * target node is a node from the original tree and is not null.
 *
 *
 * Follow up: Could you solve the problem if repeated values on the tree are allowed?
 */
public class Solution {

    /**
     * 큐에 cloned를 담아서 bfs를 하면서 target과 같은 값을 찾는다. 하위 12 / 58를 이김
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        if (original == null || target == original) {
            return cloned;
        }

        Queue<TreeNode> treeQ = new ArrayDeque<>();

        treeQ.add(cloned);

        while(!treeQ.isEmpty()) {
            TreeNode poll = treeQ.poll();

            if (target.val == poll.val) {
                return poll;
            }

            if (poll.left != null) {
                treeQ.add(poll.left);
            }

            if (poll.right != null) {
                treeQ.add(poll.right);
            }
        }

        return null;
    }

    /**
     * 깊이 우선 탐색으로 target과 같은 값을 찾는다. 하위 100 / 18을 이김
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        // 루프 탈출
        if (original == null || original == target) {
            return cloned;
        }

        TreeNode res = getTargetCopy2(original.left, cloned.left, target);

        if (res != null) {
            return res;
        }

        return getTargetCopy2(original.right, cloned.right, target);
    }
}
