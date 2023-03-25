package TreeTraversal;

import base.Node;

/**
 * 깊이 우선 탐색
 * 루트를 시작으로 자신을 탐색하고, 자신의 왼쪽 자식이 없을때까지 반복, 오른쪽 자식을 탐색
 */
public class DFS {
    public static void dfs(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.getData() + " ");
        dfs(root.getLeft());
        dfs(root.getRight());
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        Node node7 = new Node(8);

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node3.setLeft(node7);

        dfs(root);
    }
}
