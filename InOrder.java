package TreeTraversal;

import base.Node;

/**
 * 트리를 순회할때, 왼쪽 자식을 먼저 탐색하고, 자신을 탐색하고, 오른쪽 자식을 탐색하는 방법
 *
 */
public class InOrder {

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

        inOrder(root);
    }

    public static void inOrder(Node root) {
        Node left = root.getLeft();
        Node right = root.getRight();

        if (left != null) {
            inOrder(left);
        }

        System.out.println(root.getData());

        if (right != null) {
            inOrder(right);
        }

    }
}
