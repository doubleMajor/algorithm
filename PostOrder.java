package TreeTraversal;

import base.Node;

/**
 * 후위 순회
 * 왼쪽 자식, 오른쪽 자식, 자신 순으로 방문
 */
public class PostOrder {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);

        postOrder(root);
    }

    public static void postOrder(Node root) {
        Node left = root.getLeft();
        Node right = root.getRight();

        if (left != null) {
            postOrder(left);
        }

        if (right != null) {
            postOrder(right);
        }

        System.out.println(root.getData());
    }
}
